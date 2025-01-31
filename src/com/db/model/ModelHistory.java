package com.db.model;

import java.sql.Timestamp;

public class ModelHistory {
    
    private int HistoryID;
    private int AccountID;
    private String gameType;
    private String playerOneName;
    private String playerTwoName;
    private int playerOneScore;
    private int playerTwoScore;
    private String gameStatus;
    private Timestamp gameDate;

    public ModelHistory(final int HistoryID, 
                        final int AccountID, 
                        final String gameType,
                        final String playerOneName, 
                        final String playerTwoName, 
                        final int playerOneScore, 
                        final int playerTwoScore, 
                        final String gameStatus) {
        this.HistoryID = HistoryID;
        this.AccountID = AccountID;
        this.gameType = gameType;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.gameStatus = gameStatus;
    }
    
    public ModelHistory() {}

    public int getHistoryID() {
        return HistoryID;
    }

    public void setHistoryID(final int HistoryID) {
        this.HistoryID = HistoryID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(final int AccountID) {
        this.AccountID = AccountID;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(final String gameType) {
        this.gameType = gameType;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public void setPlayerOneName(final String playerOneName) {
        this.playerOneName = playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(final String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public void setPlayerOneScore(final int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public void setPlayerTwoScore(final int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(final String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Timestamp getGameDate() {
        return gameDate;
    }

    public void setGameDate(final Timestamp gameDate) {
        this.gameDate = gameDate;
    }
}
