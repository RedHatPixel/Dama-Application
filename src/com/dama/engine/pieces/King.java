package com.dama.engine.pieces;

import com.dama.engine.dependencies.Alliance;
import com.dama.engine.dependencies.Position;
import com.dama.engine.board.Board;
import com.dama.engine.board.BoardUtils;
import com.dama.engine.dependencies.Move;
import com.dama.engine.board.Tile;
import java.util.List;

public final class King extends Piece {
    
    // Static Variables
    private final static List<List<int[]>> CANDIDATE_KING_MOVE = calculatePossibleMoves(BoardUtils.NUM_TILES[0]);

    // Constructor: Create a King Type of piece
    public King(final Position position, final Alliance alliance) {
        super(position, alliance, CANDIDATE_KING_MOVE);
    }
    
    @Override
    public King movePiece(final Move move) {
        return new King(move.getLandingCoordinate(), move.getMovedPiece().getAlliance());
    }

    @Override
    public boolean canMove(final Board board, final Position startPos, final Position endPos) {
        
        // Get the common Integers
        final int startX = startPos.x();
        final int startY = startPos.y();
        final int endX = endPos.x();
        final int endY = endPos.y();
        
        // Get the direction for rows, cols
        final int deltaX = (endX > startX) ? 1 : -1;
        final int deltaY = (endY > startY) ? 1 : -1;

        // Set the current position
        int currentX = startX + deltaX;
        int currentY = startY + deltaY;
        
        // Loop through possible outcome
        while (currentX != endX && currentY != endY) {
            final Tile currentTile = board.getTile(new Position(currentX, currentY));

            // Detect if a piece is block
            if (currentTile.isOccupied()) return false;

            // Change current positiion
            currentX += deltaX;
            currentY += deltaY;
        }
        
        // Check if the destination tile is empty
        return !board.getTile(endPos).isOccupied();
    }

    @Override
    public Piece canCapture(final Board board, final Position startPos, final Position endPos) {
        
        // Get the common Integers
        final int startX = startPos.x();
        final int startY = startPos.y();
        final int endX = endPos.x();
        final int endY = endPos.y();
        
        // Get the direction for rows, cols       
        final int deltaX = (endX > startX) ? 1 : -1;
        final int deltaY = (endY > startY) ? 1 : -1;
        
        // Set the current position
        int currentX = startX + deltaX;
        int currentY = startY + deltaY;
        Piece encounteredPiece = null;
        
        // Loop through possible outcome
        while (currentX != endX && currentY != endY) {
            final Tile currentTile = board.getTile(new Position(currentX, currentY));
            
            if (currentTile.isOccupied()) {
                
                // Already encountered a piece, so this is an invalid move
                if (encounteredPiece != null) return null;
                
                // Check if the piece is an opponent's piece for capture
                if (currentTile.getPiece().getAlliance() == this.alliance) return null;
                
                encounteredPiece = currentTile.getPiece();
            }
            
            // Change current positiion
            currentX += deltaX;
            currentY += deltaY;
        }
        
        if (!board.getTile(endPos).isOccupied())
            return encounteredPiece;
        
        return null;
    }
    
    @Override
    public Type getType() {
        return Type.KING;
    }
}
