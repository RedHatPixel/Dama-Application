package com.dama.engine.dependencies;

import com.dama.engine.board.Board;
import com.dama.engine.pieces.Piece;
import java.util.List;

public abstract class Move {
    
    // Static Variables
    public static final Move NULL_MOVE = new NullMove();
    
    // Define Variables
    protected final Board board;
    protected final Piece movedPiece;
    protected final Position landingCoordinate;
    
    // Constructor: Parent abstract class
    private Move(final Board board, final Piece movedPiece, final Position coordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.landingCoordinate = coordinate;
    }
    
    /**
     * Get the landing coordinate of the Move
     * @return Position 
     */
    public final Position getLandingCoordinate() {
        return this.landingCoordinate;
    }
    
    /**
     * Get the moving piece
     * @return Moved Piece 
     */
    public final Piece getMovedPiece() {
        return this.movedPiece;
    }
    
    /**
     * Get the Piece current position ( before moving to landing position) 
     * @return Position 
     */
    public final Position getCurrentCoordinate() {
        if (this.movedPiece == null)
            return this.landingCoordinate;
        return this.movedPiece.getPosition();
    }

    /**
     * Execute the movement in a new copy of board
     * @return Board 
     */
    public Board execute() {
        final Board.Builder builder = new Board.Builder();
        // Display Current Player Pieces
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        // Display Opponent Pieces
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        // Moved the Piece into its new coordinate
        final Piece piece = this.movedPiece.movePiece(this);
        builder.setPiece(piece);
        // Set the Moved Piece
        builder.setMovedPiece(piece);
        // Apply the new move maker [TURN]
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
        // Return the Used move
        builder.setLatestMove(this);
        return builder.build();
    }
    
    // Simple Move subclass
    public static final class SimpleMove extends Move {
    
        public SimpleMove(final Board board, final Piece movedPiece, final Position coordinate) {
            super(board, movedPiece, coordinate);
        }
        
        @Override
        public Type getType() {
            return Type.SIMPLE_MOVE;
        }
    }
    
    // Attack Move subclass
    public static class AttackMove extends Move {
    
        private final Piece attackedPiece;

        public AttackMove(final Board board, final Piece movedPiece, final Position coordinate, final Piece attackedPiece) {
            super(board, movedPiece, coordinate);
            this.attackedPiece = attackedPiece;
        }
        
        /**
         * Get the Piece that legally can be attacked
         * @return Piece
         */
        public Piece getAtackedPiece() {
            return this.attackedPiece;
        }
        
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            // Display Current Player Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            // Display Opponet Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                if (!this.attackedPiece.equals(piece))
                    builder.setPiece(piece);
            }
            // Moved the Piece into its correct coordinate
            final  Piece piece = this.movedPiece.movePiece(this);
            builder.setPiece(piece);
            // Set the Moved Piece
            builder.setMovedPiece(piece);
            // Apply the new move maker [TURN]
            builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
            // Return the Used move
            builder.setLatestMove(this);
            return builder.build();
        }
        
        @Override
        public int hashCode() {
            return 31 * this.attackedPiece.hashCode() + super.hashCode();
        }
        
        @Override
        public boolean equals(final Object other) {
            if (this == other)  return true;
            if (!(other instanceof AttackMove)) return false;
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && this.getAtackedPiece().equals(otherAttackMove.getAtackedPiece());
        }
        
        @Override
        public Type getType() {
            return Type.ATTACK_MOVE;
        }
    }
    
    // Multiple Attack Move subclass
    public static class MultipleAttackMove extends Move {
        
        private final List<Piece> attackedPieces;
        
        public MultipleAttackMove(final Board board, final Piece movedPiece, final Position coordinate, final List<Piece> attackedPieces) {
            super(board, movedPiece, coordinate);
            this.attackedPieces = attackedPieces;
        }
        
        /**
         * Get the list of pieces that legally can be attacked
         * @return List of Pieces
         */
        public List<Piece> getAttackedPieces() {
            return this.attackedPieces;
        }
        
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            // Display Current Player Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            // Display Opponet Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                if (!this.attackedPieces.contains(piece))
                    builder.setPiece(piece);
            }
            // Moved the Piece into its correct coordinate
            final Piece piece = this.movedPiece.movePiece(this);
            builder.setPiece(piece);
            // Set the Moved Piece
            builder.setMovedPiece(piece);
            // Apply the new move maker [TURN]
            builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
            // Return the Used move
            builder.setLatestMove(this);
            return builder.build();
        }
        
        @Override
        public int hashCode() {
            return 31 * this.attackedPieces.hashCode() + super.hashCode();
        }
        
        @Override
        public boolean equals(final Object other) {
            if (this == other)  return true;
            if (!(other instanceof MultipleAttackMove)) return false;
            final MultipleAttackMove otherAttackMove = (MultipleAttackMove) other;
            return super.equals(otherAttackMove) && this.getAttackedPieces().equals(otherAttackMove.getAttackedPieces());
        }
        
        @Override
        public Type getType() {
            return Type.MULTIPLE_ATTACK_MOVE;
        }
    }
    
    // ChainAttackMove subclass of AttackMove subclass
    public static final class ChainAttackMove extends AttackMove {
        public ChainAttackMove(final Board board, final Piece movedPiece, final Position coordinate, final Piece attackedPiece) {
            super(board, movedPiece, coordinate, attackedPiece);
        }
        
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            // Display Current Player Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            // Display Opponet Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                if (!this.getAtackedPiece().equals(piece))
                    builder.setPiece(piece);
            }
            // Moved the Piece into its correct coordinate
            final Piece piece = this.movedPiece.movePiece(this);
            builder.setPiece(piece);
            // Set the Moved Piece
            builder.setMovedPiece(piece);
            // Apply the same move maker
            builder.setMoveMaker(this.board.getCurrentPlayer().getAlliance());
            // Return the Used move
            builder.setLatestMove(this);
            return builder.build();
        }
        
        @Override
        public boolean equals(final Object other) {
            if (this == other) return true;
            if (!(other instanceof ChainAttackMove)) return false;
            final ChainAttackMove otherMove = (ChainAttackMove) other;
            return super.equals(otherMove);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
        
        @Override
        public Type getType() {
            return Type.CHAIN_ATTACK_MOVE;
        }
    }
    
    // ChainMultipleAttackMove subclass of MultipleAttackMove subclass
    public static final class ChainMultipleAttackMove extends MultipleAttackMove {

        public ChainMultipleAttackMove(final Board board,final Piece movedPiece,final Position coordinate, final List<Piece> attackedPieces) {
            super(board, movedPiece, coordinate, attackedPieces);
        }
        
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            // Display Current Player Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            // Display Opponet Pieces
            for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
                if (!this.getAttackedPieces().contains(piece))
                    builder.setPiece(piece);
            }
            // Moved the Piece into its correct coordinate
            final Piece piece = this.movedPiece.movePiece(this);
            builder.setPiece(piece);
            // Set the Moved Piece
            builder.setMovedPiece(piece);
            // Apply the same move maker
            builder.setMoveMaker(this.board.getCurrentPlayer().getAlliance());
            // Set the Used move
            builder.setLatestMove(this);
            return builder.build();
        }
        
        @Override
        public boolean equals(final Object other) {
            if (this == other) return true;
            if (!(other instanceof ChainMultipleAttackMove)) return false;
            final ChainMultipleAttackMove otherMove = (ChainMultipleAttackMove) other;
            return super.equals(otherMove);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
        
        @Override
        public Type getType() {
            return Type.CHAIN_MULTIPLE_ATTACK_MOVE;
        }
    }
    
    // Null Move subclass
    public static final class NullMove extends Move {
    
        public NullMove() {
            super(null, null, new Position(-1, -1));
        }
        
        @Override
        public Board execute() { 
            throw new RuntimeException("NullMove: Cannot execute a null move");
        }

        @Override
        public Type getType() {
            return Type.NULL_MOVE;
        }
    }
     
    // FACTORY CLASS: Create a move and amplify to its respective coordinates
    public static class MoveFactory {
        
        // Constructor: Prevent Instantiation
        private MoveFactory() {
            throw new UnsupportedOperationException("This is a factory class and cannot be instantiated.");
        }
        
        /**
         * Classify if the Move is correct by retrieving all possible legal moves in the board
         * @exception NULL_MOVE: if the move was invalid it returns an error
         * @param board Board, the current board displayed
         * @param currentPos Position, the starting coordinate of a tile
         * @param landingPos Position, the landing coordinate of a tile
         * @return Move -> SimpleMove, AttackMove, MultipleAttackMove and NullMove
         */
        public static Move createMove(final Board board, final Position currentPos, final Position landingPos) {

            for (final Move move : board.getAllLegalMoves()) {
                if (move.getCurrentCoordinate().equals(currentPos) && 
                    move.getLandingCoordinate().equals(landingPos))
                    return move;
            }
            
            return NULL_MOVE;
        }
    }
    
    /**
     * Enum State: Name of the move 
     */
    public enum Type {
        MOVE,
        SIMPLE_MOVE,
        ATTACK_MOVE,
        MULTIPLE_ATTACK_MOVE,
        CHAIN_ATTACK_MOVE,
        CHAIN_MULTIPLE_ATTACK_MOVE,
        NULL_MOVE;
        
        public boolean isNaN() {
            return this == NULL_MOVE;
        }
        
        public boolean canAttack() {
            return this == ATTACK_MOVE || this == MULTIPLE_ATTACK_MOVE ||
                    this == CHAIN_ATTACK_MOVE || this == CHAIN_MULTIPLE_ATTACK_MOVE;
        }
        
        public boolean canAttackOnce() {
            return this == ATTACK_MOVE || this == MULTIPLE_ATTACK_MOVE;
        }
        
        public boolean canAttackAgain() {
            return this == CHAIN_ATTACK_MOVE || this == CHAIN_MULTIPLE_ATTACK_MOVE;
        }
    }
    
    /**
     * Get the Type of the Move -> SimpleMove, AttackMove etc...
     * USED: to give the string name
     * @return Type
     */
    public abstract Type getType();
    
    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (!(this instanceof Move)) return false;
        final Move otherMove = (Move) other;
        return this.getLandingCoordinate() == otherMove.getLandingCoordinate() &&
               this.getMovedPiece().equals(otherMove.getMovedPiece());
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.landingCoordinate.hashCode();
        result = prime * result + this.movedPiece.hashCode();
        return result;
    }
}
