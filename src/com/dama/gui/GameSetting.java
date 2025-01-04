package com.dama.gui;

import java.util.Timer;

public final class GameSetting {
    
    // Constructor: Prevent Instantiation
    private GameSetting() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Define Variables
    private static Timer timerType = new Timer();
    
    // Calculate for random user name from each player
    private static String setRandomName() {
        String name = "user";
        for (int i = 0; i < 10; i++) {
            name += (Math.random() * 10);
        }
        return name;
    }
    
    // Player Define State
    public static enum Player {
        WHITE_PLAYER(setRandomName()),
        BLACK_PLAYER(setRandomName());
        
        public String name;
        public Timer timer;
        
        private Player(final String name) {
            this.name = name;
            this.timer = timerType;
        }
        
        public void setPlayerName(final String name) {
            this.name = name;
        }
        
        public Timer getTimer() {
            return this.timer;
        }
    }
}
