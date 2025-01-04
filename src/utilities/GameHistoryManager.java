package utilities;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryManager {
    private final List<GameHistoryData> gameHistory;

    public GameHistoryManager() {
        this.gameHistory = new ArrayList<>();
    }
    
    public void addGameResult(GameHistoryData gameData) {
        gameHistory.add(gameData);
    }

    public void addGameResult(String type, String[] playersName, int[] playersScore, int result, String date) {
        gameHistory.add(new GameHistoryData(date, playersName, playersScore, result, date));
    }

    public List<GameHistoryData> getGameResults() {
        return gameHistory;
    }
}