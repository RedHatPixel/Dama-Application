package com.dama.engine.board;

import com.dama.engine.dependencies.*;
import com.dama.engine.pieces.*;
import com.dama.engine.players.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    
    private final Map<Position, Tile> gameBoard; // Tiles Format [64 -> 8, 8]
    
    private final Collection<Piece> whitePieces; // Collection of White Pieces
    private final Collection<Piece> blackPieces; // Collection of Black Pieces
    
    private final WhitePlayer whitePlayer;  // Player White Account
    private final BlackPlayer blackPlayer;  // Player Black Account
    private final Player currentPlayer; // Current Player
    
    private final Move latestMove;  // Move after a new turn
    private final Piece latestMovedPiece;  // Piece after a player move
    
    // Constructor: Build a configured GameBoard
    private Board(final Builder builder) {
        this.gameBoard = BoardManager.createGameBoard(builder);
        this.whitePieces = BoardManager.calculcateActivePiece(this.gameBoard, Alliance.WHITE);
        this.blackPieces = BoardManager.calculcateActivePiece(this.gameBoard, Alliance.BLACK);
        
        final Collection<Move> whiteLegalMoves = BoardManager.calculateLegalMoves((Board) this, whitePieces);
        final Collection<Move> blackLegalMoves = BoardManager.calculateLegalMoves((Board) this, blackPieces);
        
        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves);
        this.blackPlayer = new BlackPlayer(this, blackLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
        
        this.latestMove = builder.latestMove;
        this.latestMovedPiece = builder.movedPiece;
    }
    
    /**
     * Return the Tile from its perspective coordinate
     * @param position Position
     * @return Tile
     */ 
    public Tile getTile(final Position position) {
        if (!BoardUtils.isCoordinateWithinBounds(position)) {
            System.err.println("The Position location are outside the board");
            return null;
        }
        return gameBoard.get(position);
    }
    
    /**
     * Get the White Player
     * @return Player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }
    
    /**
     * Get the Black Player
     * @return Player
     */
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }
    
    /**
     * Get the Current Player [Playing]
     * @return Player
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    /**
     * Get the List of every White Pieces
     * @return Collection of Pieces [List]
     */
    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }
    
    /**
     * Get the List of every Black Pieces
     * @return Collection of Pieces [List]
     */
    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }
    
    /**
     * Get the latest move upon the board
     * @return Move 
     */
    public Move getLatestMove() {
        return this.latestMove;
    }
    
    /**
     * Get the latest moved piece upon the board
     * @return Piece 
     */
    public Piece getLatestMovedPiece() {
        return this.latestMovedPiece;
    }
    
    /**
     * Get All Players legal moves in the game board
     * @return Collection of Moves [List]
     */
    public Collection<Move> getAllLegalMoves() {
        final Collection<Move> allLegalMoves = new ArrayList<>(this.whitePlayer.getLegalMoves());
        allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
        return Collections.unmodifiableCollection(allLegalMoves);
    }
    
    /**
     * Check if the move has the same moving piece in the latest move
     * @param currentMove Move
     * @return Boolean
     */
    public boolean calculateSameMovedPiece(final Move currentMove) {
        return currentMove.getMovedPiece().equals(this.latestMovedPiece);
    }
    
    /**
     * Create a Standard Board Layout
     * @return Board
     */
    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        
        // Set the Top Layout    
        for (int row = 0; row < BoardUtils.TOP_PIECES.length; row++) {
            for (int col = 0; col < BoardUtils.TOP_PIECES[row].length; col++) {
                if (!BoardUtils.TOP_PIECES[row][col]) continue;
                if (!BoardUtils.TILES_PATTERN[row][col]) continue;
                builder.setPiece(new Pawn(new Position(row, col), Alliance.BLACK));
            }
        }
        
        // Set the Bottom Layout
        for (int row = 0; row < BoardUtils.BOTTOM_PIECES.length; row++) {
            for (int col = 0; col < BoardUtils.BOTTOM_PIECES[row].length; col++) {
                if (!BoardUtils.BOTTOM_PIECES[row][col]) continue;
                if (!BoardUtils.TILES_PATTERN[row][col]) continue;
                builder.setPiece(new Pawn(new Position(row, col), Alliance.WHITE));
            }
        }
        
        // Set the First To Move: Warning -> White FIRST
        builder.setMoveMaker(Alliance.WHITE);
        
        // Set the Updated Move into null -> No Movement yet
        builder.setLatestMove(Move.NULL_MOVE);
        builder.setMovedPiece(null);
        
        // Return the Build-in Board
        return builder.build();
    }
    
    // Builder Class: Used to specify the Board Layout
    public static class Builder {
        
        final Map<Position, Piece> boardConfig;
        Alliance nextMoveMaker;
        Move latestMove;
        Piece movedPiece;
        
        /**
         * Create a new builder class
         * USED: To create a configured board and specify pieces, turn and moves
         */
        public Builder() {
            this.boardConfig = new HashMap<>();
        }
        
        /**
         * Set a new piece in the board
         * @param piece Piece
         * @return Builder
         */
        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPosition(), piece);
            return this;
        }
        
        /**
         * Set the player to move [WhitePlayer, BlackPlayer]
         * @param alliance Alliance
         * @return Builder
         */
        public Builder setMoveMaker(final Alliance alliance) {
            this.nextMoveMaker = alliance;
            return this;
        }
        
        /**
         * Set the latest moved piece in the board -> what the player just moved
         * @param movedPiece Piece
         * @return Builder
         */
        public Builder setMovedPiece(final Piece movedPiece) {
            this.movedPiece = movedPiece;
            return this;
        }
        
        /**
         * Set the latest move the player made in the board
         * @param latestMove Move
         * @return Builder
         */
        public Builder setLatestMove(final Move latestMove) {
            this.latestMove = latestMove;
            return this;
        }
        
        /**
         * Create the Board
         * @return Board
         */
        public Board build() {
            return new Board(this);
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("-----Board-----\n");
        for (int row = 0; row < BoardUtils.NUM_TILES[0]; row++) {
            for (int col = 0; col < BoardUtils.NUM_TILES[1]; col++) {
                Position index = new Position(row, col);
                final Tile tile = this.gameBoard.get(index);
                String tileText = tile.isOccupied() ? tile.getPiece().getType().isKing() ? 
                        tile.getPiece().getAlliance().isWhite() ? "x" : "X" :
                        tile.getPiece().getAlliance().isWhite() ? "o" : "O" : "-"; 
                builder.append(tileText).append(" ");
                if (col + 1 == BoardUtils.NUM_TILES[1])
                    builder.append("\n");
            }
        }
        builder.append("---------------\n");
        return builder.toString();
    }
}