package com.dama.gui._configurations.game;

import com.dama.gui._configurations.dependencies.Duration;
import com.dama.gui._configurations.dependencies.Orientation;
import com.dama.gui.gamePanel.TablePanel;

public class GameBuilder {
    
    // Default Variables
    private GameInfo gameInfo;
    private GameSetting gameSetting;
    private boolean playable;
    
    public GameBuilder() {
        this.gameInfo = new GameInfo();
        this.gameSetting = new GameSetting();
    }
    
    private GameBuilder setGameInfo(final GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        return this;
    }
    
    private GameBuilder setGameSetting(final GameSetting gameSetting) {
        this.gameSetting = gameSetting;
        return this;
    } 
    
    public GameBuilder setPlayersName(final String opponentName, final String playerName) {
        gameInfo.setPlayersName(opponentName, playerName);
        return this;
    }
    
    public GameBuilder setTimerDuration(final Duration duration) {
        gameInfo.setTimerDuration(duration);
        return this;
    }
    
    public GameBuilder setOrientation(final Orientation orientation) {
        gameInfo.setOrientation(orientation);
        return this;
    }
    
    public GameBuilder setPlayerScore(int score) {
        gameInfo.setPlayerScore(score);
        return this;
    }
    
    public GameBuilder setOpponentScore(int score) {
        gameInfo.setOpponentScore(score);
        return this;
    }

    public GameBuilder allowChangingTurn(final boolean bool) {
        gameSetting.allowChangingTurn(bool);
        return this;
    }
    
    public GameBuilder allowShowingMovablePiece(final boolean bool) {
        gameSetting.allowShowingMovablePiece(bool);
        return this;
    }
    
    public GameBuilder allowShowingMoves(final boolean bool) {
        gameSetting.allowShowingMoves(bool);
        return this;
    }
    
    public GameBuilder allowShowingCapturables(final boolean bool) {
        gameSetting.allowShowingCapturables(bool);
        return this;
    }
    
    public GameBuilder allowShowingLatestMove(final boolean bool) {
        gameSetting.allowShowingLatestMove(bool);
        return this;
    }
    
    public GameBuilder isPlayable(final boolean bool) {
        this.playable = bool;
        return this;
    }
    
    public boolean isPlayable() {
        return playable;
    }
    
    public GameInfo getGameInfo() {
        return this.gameInfo;
    }
    
    public GameSetting getGameSetting() {
        return this.gameSetting;
    }
    
    public TablePanel build() {
        return new TablePanel(this);
    }
    
    public static TablePanel createDefaultUnplayableTablePanel() {
        return new GameBuilder()
                .isPlayable(false)
                .build();
    }
    
    public static TablePanel createRestaredTablePanel(final TablePanel tablePanel) {
        return new GameBuilder()
                .setGameInfo(tablePanel.getGameInfo())
                .setPlayerScore(tablePanel.getPlayerPanel().getPlayerInfo().getScore())
                .setOpponentScore(tablePanel.getOpponentPanel().getPlayerInfo().getScore())
                .setGameSetting(tablePanel.getGameSetting())
                .isPlayable(true)
                .build();
    }
}
