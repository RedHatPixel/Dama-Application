package com.dama.gui._configurations.player;

import com.dama.engine.players.Player;

public class PlayerInfo {
    private final Player player;
    private final String name;
    private int score;
    private int availableUndoMove;

    public PlayerInfo(final Player player, final String name, final int score) {
        this.player = player;
        this.name = name;
        this.score = score;
        this.availableUndoMove = 3;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    
    public void addScore() {
        this.score++;
    }

    public int getAvailableUndoMove() {
        return availableUndoMove;
    }
    
    public void subUndoMove() {
        this.availableUndoMove--;
    }
}
