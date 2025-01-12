package com.dama.gui;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;
import com.dama.engine.board.Board;
import com.dama.gui.game_panel.*;
import com.dama.engine.dependencies.*;
import com.dama.sound.SoundManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public final class TilePanelSystem implements MouseListener {

    private final TilePanel tilePanel;
    
    // Conditions
    private boolean pressed;
    private boolean hover;
    
    // Pressed Timer
    private final int DRAG_DELAY = 100;
    private Timer dragTimer;
    
    
    // Constructor:
    public TilePanelSystem(final TilePanel tilePanel) {
        this.tilePanel = tilePanel;
        this.pressed = false;
        this.dragTimer = null;
    }
    
    /**
     * Check if the tile is hovering or not
     * @return Boolean
     */
    public boolean hover() {
        return this.hover;
    }
    
    /**
     * Check if the tile was clicked or not
     * @return Boolean
     */
    public boolean pressed() {
        return this.pressed;
    }

    // Helper Methods: to easily reduce code repetitions
    private Table getTable() {
        return this.tilePanel.getBoardPanel().getTable();
    }
    private BoardPanel getBoardPanel() {
        return this.tilePanel.getBoardPanel();
    }
    private Board getGameBoard() {
        return this.getTable().getGameBoard();
    }
    private DragGlassPane getDragGlassPane() {
        return this.getBoardPanel().getDragGlassPane();
    }
    private Position getCoordinate() {
        return this.tilePanel.getCoordinate();
    }
    
    // Configurations: Select the starting point of the player move
    private void setStartingPoint() {
        if (getGameBoard().getTile(getCoordinate()).getPiece().getAlliance() !=
            getGameBoard().getCurrentPlayer().getAlliance())
            return;
            
        getTable().setSourceTile(getGameBoard().getTile(getCoordinate())); // Set Source tile
        getTable().setSelectedPiece(getTable().getSourceTile().getPiece()); // Set Selected Piece

        // Check if Tile is not Occupied -> Piece == null
        if (!getTable().getSourceTile().isOccupied()) {
            getTable().setSourceTile(null);
        }
        else {
            SwingUtilities.invokeLater(() -> {
                getBoardPanel().drawBoard(getGameBoard());
                getBoardPanel().drawGuidance(getTable().getSelectedPiece(), getGameBoard());
            });
        }
    }
    
    // Configurations: Select the landing point of the player move
    private void setDestinationPoint() {
        if (getTable().getDestinationTile() == null)
            getTable().setDestinationTile(getGameBoard().getTile(getCoordinate()));
        
        // Check if Destination tile is Occupied, Re Select that Piece
        if (getTable().getDestinationTile().isOccupied()) {
            getTable().setSourceTile(getTable().getDestinationTile());
            getTable().setSelectedPiece(getTable().getDestinationTile().getPiece());
            getTable().setDestinationTile(null);
            SwingUtilities.invokeLater(() -> {
                getBoardPanel().drawGuidance(getTable().getSelectedPiece(), getGameBoard());
            });
        }
    }
    
    // Configurations: Create the move of the selected tiles
    private void createMove() {
        final Move move = Move.MoveFactory.createMove(
                getGameBoard(), 
                getTable().getSourceTile().getCoordinate(), 
                getTable().getDestinationTile().getCoordinate());
        final MoveTransition transition = getGameBoard().getCurrentPlayer().makeMove(move);
        
        // Check if the move was valid
        if (transition.getMoveStatus().isDone()) {
            TableManager.setNewBoard(getTable(), transition.getTransitionBoard());
            SoundManager.createRandomPieceSound();
            validateMove();
            
            // Re-select the new possible moves
            if (getGameBoard().getLatestMove().getType().canAttackAgain()) {
                getTable().setSourceTile(getGameBoard().getTile(getCoordinate()));
                getTable().setSelectedPiece(getTable().getSourceTile().getPiece());
                getTable().setDestinationTile(null);
                SwingUtilities.invokeLater(() -> {
                    getBoardPanel().drawGuidance(getTable().getSelectedPiece(), getGameBoard());
                });
                return;
            }
        }
        
        // After a move, Reset all selected tiles
        resetSelections();
    }
    
    // Configurations: Check the result after a move
    private void validateMove() {
        
        // Check if current player has no pieces or if current player has no moves
        if (getGameBoard().getCurrentPlayer().isLooser() || getGameBoard().getCurrentPlayer().isStalemate()) {
            
            TableManager.setWinner(getTable(), true);
        }
        // Check if game was not progressing
        else if (getGameBoard().calculate50LatestMove(getTable().getGamePlay()).isEmpty()) {
            
            TableManager.setStalemate(getTable());
        }
        // Flip board game play
        else if (GameInfo.canChangeTurn()) {
            
            if (!getGameBoard().getLatestMove().getType().canAttackAgain()) {
                getBoardPanel().setDirection(getBoardPanel().getCurrentDirection().opposite());
                TableManager.reversePlayerPanel(getTable());
            }
        }
    }
    
    // Reset All the selected move of the player
    private void resetSelections() {
        getTable().setSourceTile(null);
        getTable().setDestinationTile(null);
        getTable().setSelectedPiece(null);
        SwingUtilities.invokeLater(() -> {
            getBoardPanel().drawBoard(getGameBoard());
            getBoardPanel().highlightLatestMove(getGameBoard());
            getBoardPanel().drawMovable(getGameBoard());
        });
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
        // User right click a MouseButton
        if (isRightMouseButton(e)) {
            resetSelections();
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (isLeftMouseButton(e)) {
            pressed = true;
            tilePanel.setBackgroundRespectiveColor();
            
            // Dragging Function: Create a starting point
            if (getGameBoard().getTile(getCoordinate()).isOccupied()) {
                dragTimer = new Timer(DRAG_DELAY, (_event) -> {
                    
                   // Create a Dragging Piece illusion
                    if (getTable().getSourceTile() != null && getTable().getSourceTile().getCoordinate().equals(getCoordinate())) {
                        getDragGlassPane().setDragging(true);
                        getDragGlassPane().setDraggedIcon((ImageIcon) tilePanel.getPieceIcon());
                        getDragGlassPane().setPointLocation(
                                SwingUtilities.convertPoint(tilePanel, 
                                        e.getPoint(), getDragGlassPane()));
                        tilePanel.removeTilePieceIcon();
                    }
                });
                dragTimer.setRepeats(false);
                dragTimer.start();
                
                setStartingPoint();
            }
            // Click Function: Create a destination point
            else if (getTable().getSourceTile() != null) {
                setDestinationPoint();
            }
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (isLeftMouseButton(e)) {
            pressed = false;
            tilePanel.setBackgroundRespectiveColor();
            
            // Stop the timer 
            if (dragTimer != null && dragTimer.isRunning())
                dragTimer.stop();

            // Click Function: Create a move after selecting everything
            if (getTable().getSourceTile() != null && getTable().getDestinationTile() != null) {
                
                // Get the mouse target
                final Point point = SwingUtilities.convertPoint(tilePanel, e.getPoint(), getBoardPanel());
                final TilePanel targetTile = getBoardPanel().getTilePanelAt(point);
                
                if (targetTile == null || !targetTile.getCoordinate().equals(getTable().getDestinationTile().getCoordinate())) {
                    resetSelections();
                    return;
                }
                
                createMove();
            }
            // Dropping Function: Create a destination point
            else if (getDragGlassPane().isDragging()) {
                getDragGlassPane().setDragging(false);
                getDragGlassPane().setDraggedIcon(null);
                getDragGlassPane().repaint();
                
                // Get the mouse target
                final Point point = SwingUtilities.convertPoint(tilePanel, e.getPoint(), getBoardPanel());
                final TilePanel targetTile = getBoardPanel().getTilePanelAt(point);

                // Determine the dropping tile coordinate
                if (targetTile != null && getTable().getSourceTile() != null) {
                    
                    // Click Function: Identify a clicking method
                    if (targetTile.getCoordinate().equals(getTable().getSourceTile().getCoordinate())) {
                        SwingUtilities.invokeLater(() -> {
                            getBoardPanel().drawBoard(getGameBoard());
                        });
                        setStartingPoint();
                        return;
                    }
                    
                    // Dropping Piece: Initiate a drop movement
                    getTable().setDestinationTile(getTable().getGameBoard().getTile(targetTile.getCoordinate()));
                    if (getTable().getDestinationTile() != null)
                        createMove();
                }
                else {
                    
                    // No targetTile spotted, Reset all selections
                    resetSelections();
                }
            }
        }
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        hover = true;
        tilePanel.setBackgroundRespectiveColor();
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        hover = false;
        tilePanel.setBackgroundRespectiveColor();
    }
}
