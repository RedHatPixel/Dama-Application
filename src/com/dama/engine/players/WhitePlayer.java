package com.dama.engine.players;

import com.dama.engine.dependencies.Alliance;
import com.dama.engine.board.Board;
import com.dama.engine.dependencies.Move;
import com.dama.engine.pieces.Piece;
import java.util.Collection;

public class WhitePlayer extends Player {
    
    // Constructor: Create a White Type of Player class
    public WhitePlayer(final Board board, final Collection<Move> legalMoves) {
        super(board, legalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }
}
