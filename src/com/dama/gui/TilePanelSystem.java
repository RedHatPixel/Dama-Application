package com.dama.gui;

import com.dama.engine.board.Board;
import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.MoveTransition;
import com.dama.engine.dependencies.Position;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

final class TilePanelSystem implements MouseListener {

    private final TilePanel tilePanel;
    
    // Conditions
    private boolean pressed;
    private boolean hover;
    
    // Pressed Timer
    private final int DRAG_DELAY = 100;
    private Timer dragTimer;
    
    
    // Constructor
    TilePanelSystem(final TilePanel tilePanel) {
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
        getTable().sourceTile = getGameBoard().getTile(getCoordinate()); // Set Source tile
        getTable().selectedPiece = getTable().sourceTile.getPiece(); // Set Selected Piece

        // Check if Tile is not Occupied -> Piece == null
        if (!getTable().sourceTile.isOccupied()) 
            getTable().sourceTile = null;
        else {
            SwingUtilities.invokeLater(() -> {
                getBoardPanel().highlightMoves(getTable().selectedPiece, getGameBoard());
            });
        }
    }
    
    // Configurations: Select the landing point of the player move
    private void setDestinationPoint() {
        if (getTable().destinationTile == null)
            getTable().destinationTile = getGameBoard().getTile(getCoordinate());
        
        // Check if Destination tile is Occupied, Re Select that Piece
        if (getTable().destinationTile.isOccupied()) {
            getTable().sourceTile = getTable().destinationTile;
            getTable().selectedPiece = getTable().destinationTile.getPiece();
            getTable().destinationTile = null;
            SwingUtilities.invokeLater(() -> {
                getBoardPanel().drawBoard(getGameBoard());
                getBoardPanel().highlightMoves(getTable().selectedPiece, getGameBoard());
            });
        }

    }
    
    // Configurations: Create the move of the selected tiles
    private void createMove() {
        final Move move = Move.MoveFactory.createMove(
                getGameBoard(), 
                getTable().sourceTile.getCoordinate(), 
                getTable().destinationTile.getCoordinate());
        final MoveTransition transition = getGameBoard().getCurrentPlayer().makeMove(move);
        
        // Check if the move was valid
        if (transition.getMoveStatus().isDone()) {
            getTable().setGameBoard(transition.getTransitionBoard());

            validateMove();
            
            // Re-select the new possible moves
            if (getGameBoard().getLatestMove().getType().canAttackAgain()) {
                getTable().sourceTile = getGameBoard().getTile(getCoordinate());
                getTable().selectedPiece = getTable().sourceTile.getPiece();
                getTable().destinationTile = null;

                SwingUtilities.invokeLater(() -> {
                    getBoardPanel().drawBoard(getGameBoard());
                    getBoardPanel().highlightMoves(getTable().selectedPiece, getGameBoard());
                });
                
                return;
            }
        }
        
        // After a move, Reset all selected tiles
        resetSelections();
    }
    
    // Configurations: Check the result after a move
    private void validateMove() {
        
        // Check if current player has no pieces
        if (getGameBoard().getCurrentPlayer().isLooser()) {
            if (getTable().getGameBoard().getCurrentPlayer().getOpponent().getAlliance().isWhite()) {
                getTable().status = Table.Status.WHITE_PLAYER_WIN;
            }
            else {
                getTable().status = Table.Status.BLACK_PLAYER_WIN;
            }
            
            getTable().stopPlayerTimer();
            getBoardPanel().disableBoard();
            
            getDragGlassPane().showGameEnd(getTable().status, getTable());
        }
        // Check if current player has no moves
        else if (getGameBoard().getCurrentPlayer().isStalemate()) {
            getTable().status = Table.Status.STALEMATE;
            
            getTable().stopPlayerTimer();
            getBoardPanel().disableBoard();
            
            getDragGlassPane().showGameEnd(getTable().status, getTable());
        }
        // Flip board game play
        else if (GameInfo.isChangingTurn) {
            getBoardPanel().setDirection(getBoardPanel().getCurrentDirection().opposite());
            getTable().reversePlayer();
        }
    }
    
    // Reset All the selected move of the player
    private void resetSelections() {
        getTable().sourceTile = null;
        getTable().selectedPiece = null;
        getTable().destinationTile = null;
        SwingUtilities.invokeLater(() -> {
            getBoardPanel().drawBoard(getGameBoard());
        });
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // User right click a MouseButton
        if (isRightMouseButton(e)) {
            resetSelections();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isLeftMouseButton(e)) {
            pressed = true;
            tilePanel.setBackgroundRespectiveColor();
            
            // Dragging Function: Create a starting point
            if (getGameBoard().getTile(getCoordinate()).isOccupied()) {
                dragTimer = new Timer(DRAG_DELAY, (_event) -> {
                    
                   // Create a Dragging Piece illusion
                    if (getTable().sourceTile != null && getTable().sourceTile.getCoordinate().equals(getCoordinate())) {
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
            else if (getTable().sourceTile != null) {
                setDestinationPoint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isLeftMouseButton(e)) {
            pressed = false;
            tilePanel.setBackgroundRespectiveColor();
            
            // Stop the timer 
            if (dragTimer != null && dragTimer.isRunning())
                dragTimer.stop();

            // Click Function: Create a move after selecting everything
            if (getTable().sourceTile != null && getTable().destinationTile != null) {
                
                // Get the mouse target
                Point point = SwingUtilities.convertPoint(tilePanel, e.getPoint(), getBoardPanel());
                TilePanel targetTile = getBoardPanel().getTilePanelAt(point);
                
                if (targetTile == null || !targetTile.getCoordinate().equals(getTable().destinationTile.getCoordinate())) {
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
                Point point = SwingUtilities.convertPoint(tilePanel, e.getPoint(), getBoardPanel());
                TilePanel targetTile = getBoardPanel().getTilePanelAt(point);

                // Determine the dropping tile coordinate
                if (targetTile != null && getTable().sourceTile != null) {
                    
                    // Click Function: Identify a clicking method
                    if (targetTile.getCoordinate().equals(getTable().sourceTile.getCoordinate())) {
                        SwingUtilities.invokeLater(() -> {
                            getBoardPanel().drawBoard(getGameBoard());
                        });
                        setStartingPoint();
                        return;
                    }
                    
                    // Dropping Piece: Initiate a drop movement
                    getTable().destinationTile = getTable().getGameBoard().getTile(targetTile.getCoordinate());
                    if (getTable().destinationTile != null)
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
    public void mouseEntered(MouseEvent e) {
        hover = true;
        tilePanel.setBackgroundRespectiveColor();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
        tilePanel.setBackgroundRespectiveColor();
    }
}
