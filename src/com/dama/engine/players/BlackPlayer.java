package com.dama.engine.players;

import com.dama.engine.dependencies.Alliance;
import com.dama.engine.board.Board;
import com.dama.engine.dependencies.Move;
import com.dama.engine.pieces.Piece;
import java.util.Collection;

public final class BlackPlayer extends Player {

    // Constructor: Create a Black Type of Player class
    public BlackPlayer(final Board board, final Collection<Move> legalMoves) {
        super(board, legalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }
}
