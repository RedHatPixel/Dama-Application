package com.dama.engine.board;

import com.dama.engine.dependencies.Position;
import com.dama.engine.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final Position coordinate;  // Location
    
    // Collection of EmptyTiles
    private static final Map<Position, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles(); 
    
    // Create a Map of Limited EmptyTiles -> Cannot Modified
    private static Map<Position, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Position, EmptyTile> emptyTileMap = new HashMap<>();
        
        // Loop through rows of the board
        for (int row = 0; row < BoardUtils.NUM_TILES[0]; row++) {
            
            // Loop through columns of the board
            for (int col = 0; col < BoardUtils.NUM_TILES[1]; col++) {
                
                Position pos = new Position(row, col);
                
                // Create an empty tile for a new cache
                emptyTileMap.put(pos, new EmptyTile(pos));
            }
        }
        
        return Collections.unmodifiableMap(emptyTileMap);
    }
    
    /**
     * Create an object [OccupiedTile, EmptyTile]
     * Warning: Storage have a capacity of 64 EmptyTile [8, 8]
     * @param coordinate final int specify tile location
     * @param piece final Piece insert an piece object
     * @return OccupiedTile or EmptyTile
     */
    public static Tile createTile(final Position coordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(coordinate, piece) : EMPTY_TILES_CACHE.get(coordinate);
    }
    
    // Constructor: Parent abstract class
    private Tile(final Position coordinate) {
        this.coordinate = coordinate;
    }
    
    /**
     * Get the tile coordinate
     * @return Position 
     */
    public Position getCoordinate() {
        return this.coordinate;
    }
    
    /**
     * Specify if the tile is occupied by a piece
     * @return Boolean
     */
    public abstract boolean isOccupied();
    
    /**
     * Return the Piece object
     * @return Piece
     */
    public abstract Piece getPiece();
    
    // Empty tile subclass
    private static final class EmptyTile extends Tile {
        
        EmptyTile(final Position coordinate) {
            super(coordinate);
        }
        
        @Override
        public String toString() {
            return super.toString() + " Piece: null";
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }
    
    // Occupied tile subclass
    private static final class OccupiedTile extends Tile {
        
        private final Piece piece;
        
        OccupiedTile(final Position coordinate, final Piece piece) {
            super(coordinate);
            this.piece = piece;
        }
        
        @Override
        public String toString() {
            return super.toString() + " " + piece.toString();
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return piece;
        }
    }
    
    @Override
    public String toString() {
        return "Tile: " + coordinate.toString();
    }
}
