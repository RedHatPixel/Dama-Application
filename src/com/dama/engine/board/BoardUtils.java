package com.dama.engine.board;

import com.dama.engine.dependencies.Position;

public final class BoardUtils {
    
    // Constructor: Prevent Instantiation
    private BoardUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Static Variables
    public static final int[] NUM_TILES = { 8, 8 };
    public static final boolean[][] TILES_PATTERN = createPattern();
    public static final boolean[][] BOTTOM_PIECES = createPiecesPattern(Location.BOTTOM);
    public static final boolean[][] TOP_PIECES = createPiecesPattern(Location.TOP);
    
    // Enum Location of Pieces
    private enum Location {
        TOP {
            
            @Override
            public boolean index(int row , int col) {
                return (row >= 0 && row < 3) && (row + col) % 2 != 0;
            }
        }, 
        BOTTOM {
            
            @Override
            public boolean index(int row , int col) {
                return (row >= NUM_TILES[0] - 3 && row < NUM_TILES[0]) && (row + col) % 2 != 0;
            }
        };
        
        public abstract boolean index(int row , int col);
    }
    
    // Calculate each pieces position
    private static boolean[][] createPiecesPattern(final Location location) {
        final boolean[][] pattern = new boolean[NUM_TILES[0]][NUM_TILES[1]];
        
        // Loop through rows of the board
        for (int row = 0; row < NUM_TILES[0]; row++) {
            
            // Loop through columns of the board
            for (int col = 0; col < NUM_TILES[1]; col++) {
                
                // Insert the designated position of the pieces
                pattern[row][col] = location.index(row, col);
            }
        }
        
        return pattern;
    }
    
    // Calculate the piece correct pattern -> Pieces always in dark tiles
    private static boolean[][] createPattern() {
        final boolean[][] pattern = new boolean[NUM_TILES[0]][NUM_TILES[1]];
                
        // Loop through rows of the board
        for (int row = 0; row < NUM_TILES[0]; row++) {
            
            // Loop through columns of the board
            for (int col = 0; col < NUM_TILES[1]; col++) {
                
                // Dark tiles are those where (row + col) % 2 != 0
                pattern[row][col] = (row + col) % 2 != 0;
            }
        }

        return pattern;
    }
    
    /**
     * Check if Piece are still on the Board Boundaries
     * @param coordinate Position
     * @return Boolean
     */
    public static boolean isCoordinateWithinBounds(final Position coordinate) {
        return coordinate.x() >= 0 && coordinate.x() < NUM_TILES[0] &&
               coordinate.y() >= 0 && coordinate.y() < NUM_TILES[1];
    }
    
    /**
     * Check if the movement are diagonal
     * @param startPos Position
     * @param endPos Position
     * @return Boolean
     */
    public static boolean isCoordinateDiagonal(final Position startPos, final Position endPos) {
        return  Math.abs(startPos.x() - endPos.x()) == Math.abs(startPos.y() - endPos.y());
    }
    
    /**
     * Check if the Piece coordinate are correct
     * @param position Position
     * @return Boolean
     */
    public static boolean isValidCoordinate(final Position position) {
        return TILES_PATTERN[position.x()][position.y()];
    }
}
