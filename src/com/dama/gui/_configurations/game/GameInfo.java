package com.dama.gui._configurations.game;

import com.dama.gui._configurations.dependencies.Duration;
import com.dama.gui._configurations.dependencies.Orientation;

public class GameInfo {
    private String opponentName;
    private String playerName;
    private Duration duration;
    private Orientation orientation;
    private int playerScore;
    private int opponentScore;
    
    GameInfo() {
        opponentName = "Opponent";
        playerName = "Player";
        duration = Duration.INFINITE;
        orientation = Orientation.NORMAL;
        playerScore = 0;
        opponentScore = 0;
    }
    
    void setPlayersName(final String opponentName, final String playerName) {
        if (opponentName.isBlank()) this.opponentName = GameUtils.createRandomName();
        else this.opponentName = opponentName;
        if (playerName.isBlank()) this.playerName = GameUtils.createRandomName();
        else this.playerName = playerName;
    }
    
    void setTimerDuration(final Duration duration) {
        this.duration = duration;
    }
    
    void setOrientation(final Orientation orientation) {
        if (orientation.isRandom()) this.orientation = GameUtils.createRandomOrientation();
        else this.orientation = orientation;
    }
    
    void setPlayerScore(final int score) {
        this.playerScore = score;
    }

    void setOpponentScore(final int score) {
        this.opponentScore = score;
    }
    
    public int getPlayerScore() {
        return this.playerScore;
    }
    
    public int getOpponentScore() {
        return this.opponentScore;
    }
    
    public String getOpponentName() {
        return opponentName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Duration getDuration() {
        return duration;
    }

    public Orientation getOrientation() {
        return orientation;
    }
}
