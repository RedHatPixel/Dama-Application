package com.dama.engine.players;

import com.dama.engine.dependencies.Alliance;
import com.dama.engine.board.Board;
import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.MoveTransition;
import com.dama.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class Player {

    // Calculate to look for possible capture
    private static Collection<Move> calculateAttackMoves(final Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (move.getType().canAttack())
                attackMoves.add(move);
        }
        return attackMoves;
    }
    
    // Define Variables
    protected final Board board;
    protected final Collection<Move> legalMoves;
    protected final boolean isForceCapture;
    
    // Constructor: Build the Player Configuration
    protected Player (final Board board, final Collection<Move> legalMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.isForceCapture = !calculateAttackMoves(getLegalMoves()).isEmpty();
    }
    
    /**
     * Check if the move was within player legal moves
     * @param move Move
     * @return Boolean
     */
    public boolean isLegalMove(Move move) {
        return this.legalMoves.contains(move);
    }
    
    /**
     * Check if mandatory capture is needed
     * @return Boolean 
     */
    public boolean isForceCapture() {
        return this.isForceCapture;
    }
    
    /**
     * Check if there is no legal moves
     * @return Boolean
     */
    public boolean isStalemate() {
        return this.legalMoves.isEmpty();
    }
    
    /**
     * Check if there is no available pieces
     * @return Boolean
     */
    public boolean isLooser() {
        return this.getActivePieces().isEmpty();
    }
    
    /**
     * Get All possible move of the player
     * @return Collection of Move
     */
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }
    
    /**
     * Create an transitional board with the given move
     * USED: to predict players intentional move 
     * @param move Move
     * @return Board
     */
    public MoveTransition makeMove(final Move move) {
        
        // Check if the move was Nothing or Null
        if (move == null || move.getType().isNaN())
            return new MoveTransition(this.board, move, MoveTransition.MoveStatus.NULL_MOVE);
        
        // Check if the move was within player legal moves
        if (!this.isLegalMove(move))
            return new MoveTransition(this.board, move, MoveTransition.MoveStatus.ILLEGAL_MOVE);
        
        // Check if the Player must attack
        if (!move.getType().canAttack() && this.isForceCapture())
            return new MoveTransition(this.board, move, MoveTransition.MoveStatus.FORCE_CAPTURING_MOVE);

        // Check if the Player must attack again
        if (this.board.getLatestMove().getType().canAttackAgain() && !this.board.calculateSameMovedPiece(move))
            return new MoveTransition(this.board, move, MoveTransition.MoveStatus.NON_COMPLETED_MOVE);
        
        // Finalize the move after Checking
        final Board transitionBoard = move.execute();
        return new MoveTransition(transitionBoard, move, MoveTransition.MoveStatus.DONE);
    }

    /**
     * Get the Active Pieces of this Player
     * @return Collection of Piece piece 
     */
    public abstract Collection<Piece> getActivePieces();
    
    /**
     * Get the Alliance Color of this Player [White, Black]
     * @return Alliance
     */
    public abstract Alliance getAlliance();
    
    /**
     * Get the Opponent Player class
     * @return Player
     */
    public abstract Player getOpponent();
}
