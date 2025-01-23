package com.dama.gui._configurations.game;

public class GameSetting {
    private boolean canChangeTurn;
    private boolean canShowMovablePiece;
    private boolean canShowValidMoves;
    private boolean canShowCaptures;
    private boolean canShowLatestMove;
    
    GameSetting() {
        canChangeTurn = false;
        canShowMovablePiece = false;
        canShowValidMoves = true;
        canShowCaptures = true;
        canShowLatestMove = false;
    }
    
    void allowChangingTurn(final boolean bool) {
        this.canChangeTurn = bool;
    }
    
    void allowShowingMovablePiece(final boolean bool) {
        this.canShowMovablePiece = bool;
    }
    
    void allowShowingMoves(final boolean bool) {
        this.canShowValidMoves = bool;
    }
    
    void allowShowingCapturables(final boolean bool) {
        this.canShowCaptures = bool;
    }
    
    void allowShowingLatestMove(final boolean bool) {
        this.canShowLatestMove = bool;
    }
    
    public boolean canChangeTurn() {
        return canChangeTurn;
    }

    public boolean canShowMovablePiece() {
        return canShowMovablePiece;
    }

    public boolean canShowValidMoves() {
        return canShowValidMoves;
    }

    public boolean canShowCaptures() {
        return canShowCaptures;
    }

    public boolean canShowLatestMove() {
        return canShowLatestMove;
    }
}
