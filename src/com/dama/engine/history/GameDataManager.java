package com.dama.engine.history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDataManager {
    private static final List<GameData> gameHistory = new ArrayList<>();

    // Constructor: Prevent Instantiation
    private GameDataManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    /**
     * Adds a game result to the game history.
     *
     * @param gameData the game data to add
     */
    public static void addGameResult(final GameData gameData) {
        gameHistory.add(gameData);
    }
    
    /**
     * Retrieves all game results from the game history.
     *
     * @return the list of game data
     */
    public static List<GameData> getGameResults() {
        return Collections.unmodifiableList(new ArrayList<>(gameHistory));
    }
    
    /**
     * Retrieves the most recent game result.
     *
     * @return the last game data, or null if no data exists
     */
    public static GameData getMostRecentGame() {
        return gameHistory.isEmpty() ? null : gameHistory.get(gameHistory.size() - 1);
    }
    
    /**
     * Clears all game history.
     */
    public static void clearGameHistory() {
        gameHistory.clear();
    }
    
    /**
     * Retrieves the number of games in the history.
     *
     * @return the size of the game history
     */
    public static int getGameCount() {
        return gameHistory.size();
    }
}