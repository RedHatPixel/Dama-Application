package com.dama.engine.pieces;

import com.dama.engine.dependencies.Alliance;
import com.dama.engine.dependencies.Position;
import com.dama.engine.board.Board;
import com.dama.engine.board.BoardUtils;
import com.dama.engine.dependencies.Move;
import com.dama.engine.dependencies.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Piece {
    
    // Define Variables
    protected final List<List<int[]>> candidateMove;
    protected final Position position;
    protected final Alliance alliance;
    private final int cacheHashCode;
    
    // Static: Calculate Possible Moves of a piece
    static List<List<int[]>> calculatePossibleMoves(final int size) {
        final List<List<int[]>> moves = new ArrayList<>();
        final int[][] directions = {
            { -1, -1 }, // up-left
            { -1,  1 }, // up-right
            {  1, -1 }, // down-left
            {  1,  1 }  // down-right
        };
        for (int[] dir : directions) {
            final List<int[]> direction = new ArrayList<>();
            for (int i = 1; i < size; i++) {
                direction.add(new int[] { dir[0] * i, dir[1] * i });
            }
            moves.add(direction);
        }
        return Collections.unmodifiableList(moves);
    }
    
    // Constructor: Parent abstract class
    protected Piece(final Position position, final Alliance alliance, final List<List<int[]>> candidateMove) {
        this.position = position;
        this.alliance = alliance;
        this.candidateMove = candidateMove;
        this.cacheHashCode = computeHashCode();
    }
    
    // Compute Unique HashCode
    private int computeHashCode() {
        int result = this.getType().hashCode();
        result = 31 * result + alliance.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
    
    /**
     * Calculate each legal move from the piece
     * @param board Board
     * @return List: Move objects
     */
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final List<int[]> directions : candidateMove) { // Directions -> up-left, up-right etc...
            for (final int[] direction : directions) { // Loop -> [1, 1], [2, 2], [3, 3] etc...
                final int landingX = position.x() + direction[0];
                final int landingY = position.y() + direction[1];
                final Position landingPos = new Position(landingX, landingY);
                final Position startingPos = position;

                if (!BoardUtils.isCoordinateWithinBounds(landingPos) || 
                    !BoardUtils.isCoordinateDiagonal(startingPos, landingPos)) break;
                if (!BoardUtils.isValidCoordinate(landingPos)) break;
                
                // canMove method: Check if movement is possible -> Store as SimpleMove
                if (canMove(board, startingPos, landingPos)) {
                    legalMoves.add(new SimpleMove(board, this, landingPos)); 
                }
                else {
                    // canCapture method: Check if landing position can takes other piece
                    final Piece capturedPiece = canCapture(board, startingPos, landingPos);
                    if (capturedPiece != null) {
                        final List<Piece> capturedPieces = new ArrayList<>();
                        capturedPieces.add(capturedPiece);
                        // recursive method: Check for more possible captures
                        List<Move> chainMoves = calculateCaptures(board, landingPos, capturedPieces);
                        if (chainMoves.isEmpty()) {
                            legalMoves.add(new AttackMove(board, this, landingPos, capturedPiece));
                        } else {
                            legalMoves.add(new ChainAttackMove(board, this, landingPos, capturedPiece));
                            legalMoves.addAll(chainMoves);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves); // Immutable List of legal moves
    }

    // Recursive Method: Used to repeatedly check for more canCapture movement
    private List<Move> calculateCaptures(final Board board, final Position startingPos, final List<Piece> capturedPieces) {
        final List<Move> additionalMoves = new ArrayList<>();

        for (final List<int[]> directions : candidateMove) { // Directions -> up-left, up-right etc...
            for (final int[] direction : directions) { // Loop -> [1, 1], [2, 2], [3, 3] etc...
                final int landingX = startingPos.x() + direction[0];
                final int landingY = startingPos.y() + direction[1];
                final Position landingPos = new Position(landingX, landingY);

                if (!BoardUtils.isCoordinateWithinBounds(landingPos) || 
                    !BoardUtils.isCoordinateDiagonal(startingPos, landingPos)) break;
                if (!BoardUtils.isValidCoordinate(landingPos)) break;

                // canCapture method: Check if landing position can takes other piece
                final Piece capturedPiece = canCapture(board, startingPos, landingPos);
                if (capturedPiece != null && !capturedPieces.contains(capturedPiece)) {
                    final List<Piece> newCapturedPieces = new ArrayList<>(capturedPieces);
                    newCapturedPieces.add(capturedPiece);
                    // recursive method: Check for more possible captures
                    List<Move> subsequentCaptures = calculateCaptures(board, landingPos, newCapturedPieces);
                    if (!subsequentCaptures.isEmpty()) {
                        additionalMoves.add(new ChainMultipleAttackMove(board, this, landingPos, newCapturedPieces));
                        additionalMoves.addAll(subsequentCaptures);
                    }
                    else {
                        additionalMoves.add(new MultipleAttackMove(board, this, landingPos, newCapturedPieces));
                    }
                }
            }
        }
        return additionalMoves;
    }

    /** 
     * Return the Piece alliance aka. Color [white, black]
     * @return Alliance
     */
    public Alliance getAlliance() {
        return this.alliance;
    }
    
    /**
     * Get the Piece position
     * @return Position
     */
    public Position getPosition() {
        return this.position;
    }
    
    /**
     * Apply the move function to the piece
     * @param move
     * @return Piece
     */
    public abstract Piece movePiece(final Move move);
    
    /**
     * Check if normal movement was possible
     * @param board Board
     * @param startPos Position
     * @param endPos Position
     * @return Boolean
     */
    public abstract boolean canMove(final Board board, final Position startPos, final Position endPos);
    
    /**
     * Check if capturing movement was possible
     * @param board Board
     * @param startPos Position
     * @param endPos Position
     * @return Piece
     */
    public abstract Piece canCapture(final Board board, final Position startPos, final Position endPos);
    
    /**
     * Specify the Piece Type [Pawn, King etc...]
     * @return Type
     */
    public abstract Type getType();

    /**
     * Enum State: Different type of pieces [Pawn, King etc...]
     */
    public enum Type {
        PAWN("Pawn"),
        KING("King");
        
        private String name;
        
        Type(final String name) {
            this.name = name;
        }
        
        /**
        * Check if Piece Type King class
        * @return Boolean
        */
        public boolean isKing() {
            return this.equals(KING);
        }
        
        @Override
        public String toString() {
            return this.name;
        }
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) return true;
        if (!(this instanceof Piece)) return false;
        final Piece otherPiece = (Piece) other;
        return this.position == otherPiece.getPosition() &&
                this.alliance == otherPiece.getAlliance() &&
                this.getType() == otherPiece.getType();
    }
    
    @Override
    public int hashCode() {
        return this.cacheHashCode;
    }
    
    @Override
    public String toString() {
        return "Piece -> Alliance: " + alliance.toString() + " " + position.toString() + " Type: " + getType().toString();
    }
}