package com.dama.gui;

import com.dama.engine.board.Board;
import com.dama.engine.board.Tile;
import com.dama.engine.pieces.Piece;

public final class Table {
    
    // Define Variables
    private final BoardPanel boardPanel;
    private Board gameBoard;
    
    // Players Selection -> Tiles, Piece  -> same package only
    Tile sourceTile;
    Tile destinationTile;
    Piece selectedPiece;
    
    public Table() {
        this.gameBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel(this);
        this.boardPanel.drawBoard(gameBoard);
        this.boardPanel.setDirection(BoardPanel.Direction.NORMAL);
    }
    
    /**
     * Get the game engine of the Game
     * USED: to retrieve this to the control system [BoardPanel]  -> same package only
     * @return Board
     */
    Board getGameBoard() {
        return this.gameBoard;
    }
    
    /**
     * Set the game engine of the Game
     * USED: to set a new line of boards by Board.builder()  -> same package only
     * @param board Board
     */
    void setGameBoard(final Board board) {
        this.gameBoard = board;
    }
    
    /**
     * Get the game GUI of the Game
     * USED: to retrieve this to the GUI system [Frame, Panels, etc...]
     * @return BoardPanel
     */
    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }
}
