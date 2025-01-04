package utilities;

public class GameHistoryData {
    
    public String type;
    public String[] playersName;
    public int[] playersScore;
    public int result;
    public String date;

    public GameHistoryData(String type, String[] playersName, int[] playersScore, int result, String date) {
        this.type = type;
        this.playersName = playersName;
        this.playersScore = playersScore;
        this.result = result;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getWinner() {
        return playersName[result];
    }

    @Override
    public String toString() {
        String sResult = result == 1 ? "win" : "lose";
        
        return String.format("Type: %s | %s - %s | Result: %s | Date: %s", 
                type, playersName[0], playersName[1], sResult, date);
    }
}

