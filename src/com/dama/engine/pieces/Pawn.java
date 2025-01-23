package com.dama.engine.pieces;

import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.Alliance;
import com.dama.engine.dependencies.Position;
import com.dama.engine.board.Board;
import com.dama.engine.board.Tile;
import com.dama.engine.board.BoardUtils;
import java.util.List;

public final class Pawn extends Piece {
    
    // Static Variables
    private final static List<List<int[]>> CANDIDATE_PAWN_MOVE = calculatePossibleMoves(3);

    // Constructor: Create a King Type of piece
    public Pawn(final Position position, final Alliance alliance) {
        super(position, alliance, CANDIDATE_PAWN_MOVE);
    }
    
    @Override
    public Piece movePiece(final Move move) {
        
        // PROMOTION: Handle Pawn -> King promotion from Board Limited Size
        if ((move.getLandingCoordinate().x() == 0 && move.getMovedPiece().getAlliance().isWhite()) ||
            (move.getLandingCoordinate().x() == BoardUtils.NUM_TILES[0] - 1 && move.getMovedPiece().getAlliance().isBlack())) {
            
            if (!move.getType().canAttackAgain())
                return new King(move.getLandingCoordinate(), move.getMovedPiece().getAlliance());
        }
        
        return new Pawn(move.getLandingCoordinate(), move.getMovedPiece().getAlliance());
    }

    @Override
    public boolean canMove(final Board board, final Position startPos, final Position endPos) {
        
        // Get the distance between points
        final int deltaX = endPos.x() - startPos.x();
        final int deltaY = endPos.y() - startPos.y();
        
        // Allow forward movement only
        if (deltaX != this.getAlliance().getDirection()) return false;

        // Allow normal movement when there is no capturable
        if (Math.abs(deltaX) == 1 && Math.abs(deltaY) == 1) {
            
            // Check if the destination tile is empty
            return !board.getTile(endPos).isOccupied();
        }
        
        return false;
    }

    @Override
    public Piece canCapture(final Board board, final Position startPos, final Position endPos) {
        
        // Get the common Integers
        final int startX = startPos.x();
        final int startY = startPos.y();
        
        // Get the distance between points
        final int deltaX = endPos.x() - startX;
        final int deltaY = endPos.y() - startY; 
        
        // Allow capture movement
        if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 2) {
            
            // Get the position between tiles
            final int midX = startX + deltaX / 2;
            final int midY = startY + deltaY / 2;
            final Tile middleTile = board.getTile(new Position(midX, midY));
            
            // Check if the tile is occupied and from another alliance [enemy]
            if (middleTile.isOccupied() && middleTile.getPiece().getAlliance() != this.alliance) {
                
                // Check if the destination tile is empty
                if (!board.getTile(endPos).isOccupied())
                    return middleTile.getPiece();
                
            }
        }
       
        return null;
    }
    
    @Override
    public Type getType() {
        return Type.PAWN;
    }
}