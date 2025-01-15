package com.dama.engine.history;

import com.dama.engine.dependencies.Alliance;
import com.dama.gameGui.GameInfo.*;
import com.dama.gameGui.game_panel.PlayerPanel;
import com.dama.gameGui.game_panel.Table.*;
import com.dama.gameGui.game_panel.Table;

public class GameData {
    
    private final Table table;
    private final GameDuration duration;
    private final PlayerPanel[] playersPanel;
    private final int[] playersScore;
    private final Status result;
    private final String date;

    public GameData(final Table table,
                    final GameDuration duration, 
                    final PlayerPanel[] playersPanel, 
                    final int[] playersScore, 
                    final Status result, 
                    final String date) {
        this.table = table;
        this.duration = duration;
        this.playersPanel = playersPanel;
        this.playersScore = playersScore;
        this.result = result;
        this.date = date;
    }
    
    public Table getTable() {
        return this.table;
    }
    
    public GameDuration getDuration() {
        return this.duration;
    }
    
    public PlayerPanel getPlayerPanel(final boolean playerOne) {
        return playerOne ? this.playersPanel[0] : this.playersPanel[1];
    }
    
    public int getPlayerScore(final boolean playerOne) {
        return playerOne ? this.playersScore[0] : this.playersScore[1];
    }
    
    public Status getStatus() {
        return this.result;
    }
    
    public String getDate() {
        return date;
    }
}

