package com.dama.engine.dependencies;

import com.dama.engine.players.*;

public enum Alliance {
    BLACK {
        
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
        
        @Override
        public String toString() {
            return "Black";
        }
    }, 
    WHITE {
        
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
        
        @Override
        public String toString() {
            return "White";
        }
    };
    
    /**
     * Get the Pawn forward movement
     * @return Integer
     */
    public abstract int getDirection();
    
    /**
     * Choose the Player depending on what alliance are
     * @param whitePlayer WhitePlayer
     * @param blackPlayer BlackPlayer
     * @return Player
     */
    public abstract Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
    
    /**
     * Get the Piece Alliance is White
     * @return Boolean
     */
    public boolean isWhite() {
        return this == WHITE;
    };
    
    /**
     * Get the Piece Alliance is Black
     * @return Boolean
     */
    public boolean isBlack() {
        return this == BLACK; 
    };
}
