package com.dama.engine.records;

import com.dama.gui._configurations.dependencies.Duration;
import com.dama.gui._configurations.dependencies.Status;
import com.dama.gui.gamePanel.PlayerPanel;

public class GameData {
    
    private final Duration duration;
    private final PlayerPanel[] playersPanel;
    private final Status result;
    private final String date;

    public GameData(final Duration duration, 
                    final PlayerPanel[] playersPanel, 
                    final Status result, 
                    final String date) {
        this.duration = duration;
        this.playersPanel = playersPanel;
        this.result = result;
        this.date = date;
    }
    
    public Duration getDuration() {
        return this.duration;
    }
    
    public PlayerPanel getPlayerPanel(final boolean playerOne) {
        return playerOne ? this.playersPanel[0] : this.playersPanel[1];
    }
    
    public Status getStatus() {
        return this.result;
    }
    
    public String getDate() {
        return date;
    }
}

