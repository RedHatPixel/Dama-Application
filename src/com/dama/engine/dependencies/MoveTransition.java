package com.dama.engine.dependencies;

import com.dama.engine.board.Board;

public final class MoveTransition {
    
    // Define Variables
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;
    
    // Constructor: Create an after board
    public MoveTransition(final Board board, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = board;
        this.move = move;
        this.moveStatus = moveStatus;
    } 
    
    /**
     * Get the Status after the movement
     * @return MoveStatus
     */
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    /**
     * Get the Transitional board after the movement
     * @return Board
     */
    public Board getTransitionBoard() {
        return this.transitionBoard;
    }
    
    /**
     * Get the specified move
     * @return Move
     */
    public Move getMove() {
        return this.move;
    }
    
    /**
     * Enum State: Result of making the move 
     */
    public static enum MoveStatus {
        DONE,
        FORCE_CAPTURING_MOVE,
        NON_COMPLETED_MOVE,
        ILLEGAL_MOVE,
        NULL_MOVE;
        
        public boolean isDone() {
            return this == DONE;
        }
    }
}
