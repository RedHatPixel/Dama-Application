package com.dama.engine.board;

import com.dama.engine.dependencies.*;
import com.dama.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class BoardManager {
    
    // Constructor: Prevent Instantiation
    private BoardManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Create each Tile in the board and places pieces stored in the builder
    static Map<Position, Tile> createGameBoard(final Board.Builder builder) {
        final Map<Position, Tile> tiles = new HashMap<>();
        
        // Loop through rows of the board
        for (int row = 0; row < BoardUtils.NUM_TILES[0]; row++) {
            
            // Loop through columns of the board
            for (int col = 0; col < BoardUtils.NUM_TILES[1]; col++) {
                
                Position position = new Position(row, col);
                
                // Create a tile in each position and place the specified pieces
                tiles.put(position, Tile.createTile(position, builder.boardConfig.get(position)));
            }
        }
        
        return Collections.unmodifiableMap(tiles);
    }
    
    // Calculate each legal moves of the pieces and return it into a list
    static Collection<Move> calculateLegalMoves(final Board board, final Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        
        // Loop through all collected pieces
        for (final Piece piece : pieces) {
                        
            // Takes all the possible legal moves of this pieces
            legalMoves.addAll(piece.calculateLegalMoves(board));
        }
        
        return Collections.unmodifiableList(legalMoves);
    }
    
    // Calculate active pieces from different alliance [white, black] and store it into a list
    static Collection<Piece> calculcateActivePiece(final Map<Position, Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>(); 
        
        // Iterate over the values of the map (tiles)
        for (final Tile tile : gameBoard.values()) {
            
            // Check if tile is Occupied -> has pieces
            if (tile.isOccupied()) {
                final Piece piece = tile.getPiece();
                
                // Check if it is the same alliance -> white or black
                if (piece.getAlliance() == alliance)
                    activePieces.add(piece);
            }
        }
        
        return Collections.unmodifiableList(activePieces);
    }
}
