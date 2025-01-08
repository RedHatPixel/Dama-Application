package com.dama.gui;

import java.util.Random;

public final class GameInfo {
    
    // Constructor: Prevent Instantiation
    private GameInfo() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Static Variables: Game Data
    static String TOP_PLAYER_NAME = "Opponent";
    static String BOTTOM_PLAYER_NAME = "Player";
    static GameDuration GAME_DURATION = GameDuration.NULL;
    static GameSwitch BOARD_DIRECTION = GameSwitch.NORMAL;
    
    // Static Variables Game Setting
    static boolean CAN_CHANGE_TURN = false;
    static boolean CAN_SHOW_MOVABLE_PIECE = false;
    static boolean CAN_SHOW_VALID_MOVES = true;
    static boolean CAN_SHOW_CAPTURES = true;
    static boolean CAN_SHOW_LATEST_MOVE = true;
    
    public static void resetSetting() {
        TOP_PLAYER_NAME = "Opponent";
        BOTTOM_PLAYER_NAME = "Player";
        GAME_DURATION = GameDuration.NULL;
        BOARD_DIRECTION = GameSwitch.NORMAL;
        CAN_CHANGE_TURN = false;
        CAN_SHOW_MOVABLE_PIECE = false;
        CAN_SHOW_VALID_MOVES = true;
        CAN_SHOW_CAPTURES = true;
        CAN_SHOW_LATEST_MOVE = true;
    }
    
    public static void setPlayerNames(final String topPlayerName, final String bottomPlayerName) {
        TOP_PLAYER_NAME = topPlayerName;
        BOTTOM_PLAYER_NAME = bottomPlayerName;
        
        if (TOP_PLAYER_NAME.isBlank())
            TOP_PLAYER_NAME = createRandomName();
        if (BOTTOM_PLAYER_NAME.isBlank())
            BOTTOM_PLAYER_NAME = createRandomName();
    }
    
    public static String getTopPlayerName() {
        return TOP_PLAYER_NAME;
    }
    
    public static String getBotttomPlayerName() {
        return BOTTOM_PLAYER_NAME;
    }
    
    public static void setGameDuration(final GameDuration duration) {
        GAME_DURATION = duration;
    }
    
    public static void setDirection(final GameSwitch direction) {
        BOARD_DIRECTION = direction;
    }
    
    public static void allowChangeTurn(final boolean bool) {
        CAN_CHANGE_TURN = bool;
    }
    
    public static void allowShowingMoves(final boolean bool) {
        CAN_SHOW_VALID_MOVES = bool;
    }
    
    public static void allowShowingCapturables(final boolean bool) {
        CAN_SHOW_CAPTURES = bool;
    }
    
    public static void allowShowingMovablePiece(final boolean bool) {
        CAN_SHOW_MOVABLE_PIECE = bool;
    }
    
    public static void allowShowingLatestMove(final boolean bool) {
        CAN_SHOW_LATEST_MOVE = bool;
    }
    
    public static void createRandomAlliance() {
        final Random random = new Random();
        BOARD_DIRECTION = random.nextBoolean() ?
                        GameInfo.GameSwitch.NORMAL :
                        GameInfo.GameSwitch.FLIPPED;
    }
    
    public static String createRandomName() {
        String name = "user";
        final Random random = new Random();
        for (int i = 0; i < 5; i++) {
            final char randomChar = (char) ('a' + random.nextInt(26));
            final int randomDigit = random.nextInt(10);
            name += random.nextBoolean() ? String.valueOf(randomChar).toUpperCase() : randomDigit;
        }
        return name;
    }
    
    // Enum for reverse player allaince board
    public static enum GameSwitch {
        FLIPPED,
        NORMAL;
    }

    // Enum for predefined game durations
    public static enum GameDuration {
        FIVE_MINUTES {
            @Override
            int getTime() {
                return 5 * 60 * 1000;
            }
        },
        TEN_MINUTES {
            @Override
            int getTime() {
                return 10 * 60 * 1000;
            }
        },
        FIFTEEN_MINUTES {
            @Override
            int getTime() {
                return 15 * 60 * 1000;
            }
        },
        NO_TIMER {
            @Override
            int getTime() {
                return 0;
            }
        },
        NULL {
            @Override
            int getTime() {
                return -1;
            }
        };

        abstract int getTime();
    }
}
