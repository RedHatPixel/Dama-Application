package com.db.service;

import com.db.model.ModelHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceHistory extends Service {
    
    public ServiceHistory() {
        super();
    }
   
    public void deleteHistory(final ModelHistory history) throws SQLException {
        try (final PreparedStatement ps = getConnection().prepareStatement(
                "DELETE FROM `history` WHERE HistoryID=? limit 1")) {
            ps.setInt(1, history.getHistoryID());
            ps.execute();
        }
    }
    
    public void insertHistory(final ModelHistory history) throws SQLException {
        final int historyID;
        try (final PreparedStatement ps = (PreparedStatement) getConnection().prepareStatement(
            "INSERT INTO `history`(AccountID, GameType, PlayerOneName, PlayerTwoName, PlayerOneScore, PlayerTwoScore, GameStatus, GameDate) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, history.getAccountID());
            ps.setString(2, history.getGameType());
            ps.setString(3, history.getPlayerOneName());
            ps.setString(4, history.getPlayerTwoName());
            ps.setInt(5, history.getPlayerOneScore());
            ps.setInt(6, history.getPlayerTwoScore());
            ps.setString(7, history.getGameStatus());
            ps.execute();
            try (final ResultSet r = ps.getGeneratedKeys()) {
                r.next();
                historyID = r.getInt(1);
            }
        }
        history.setHistoryID(historyID);
    }
    
    public List<ModelHistory> getLatestHistory(int accountID, int limit) throws SQLException {
        final List<ModelHistory> historyList = new ArrayList<>();
        final String query = "SELECT * FROM `history` WHERE AccountID = ? ORDER BY GameDate DESC LIMIT ?";
        try (final PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, accountID);
            ps.setInt(2, limit);
            try (final ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final ModelHistory history = new ModelHistory();
                    history.setHistoryID(rs.getInt(1));
                    history.setAccountID(rs.getInt(2));
                    history.setGameType(rs.getString(3));
                    history.setPlayerOneName(rs.getString(4));
                    history.setPlayerTwoName(rs.getString(5));
                    history.setPlayerOneScore(rs.getInt(6));
                    history.setPlayerTwoScore(rs.getInt(7));
                    history.setGameStatus(rs.getString(8));
                    history.setGameDate(rs.getTimestamp(9));
                    historyList.add(history);
                }
            }
        }
        return historyList;
    }
    
    public ModelHistory getLatestGame(int accountID) throws SQLException {
        final String query = "SELECT * FROM `history` WHERE AccountID = ? ORDER BY GameDate DESC LIMIT 1";
        try (final PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, accountID);
            try (final ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    final ModelHistory history = new ModelHistory();
                    history.setHistoryID(rs.getInt(1));
                    history.setAccountID(rs.getInt(2));
                    history.setGameType(rs.getString(3));
                    history.setPlayerOneName(rs.getString(4));
                    history.setPlayerTwoName(rs.getString(5));
                    history.setPlayerOneScore(rs.getInt(6));
                    history.setPlayerTwoScore(rs.getInt(7));
                    history.setGameStatus(rs.getString(8));
                    history.setGameDate(rs.getTimestamp(9));
                    return history;
                }
            }
        }
        return null;
    }
}
