package com.dama.gameGui;

import java.util.Random;

public final class GameInfo {
    
    // Constructor: Prevent Instantiation
    private GameInfo() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Static Variables: Game Data
    private static String TOP_PLAYER_NAME = "Opponent";
    private static String BOTTOM_PLAYER_NAME = "Player";
    private static GameDuration GAME_DURATION = GameDuration.NO_TIMER;
    private static GameSwitch BOARD_DIRECTION = GameSwitch.NORMAL;
    private static boolean GAME_START = false;
    
    // Static Varaiables: Game Scores
    private static int TOP_PLAYER_SCORE = 0;
    private static int BOTTOM_PLAYER_SCORE = 0;
    
    // Static Variables: Game Setting
    private static boolean CAN_CHANGE_TURN = false;
    private static boolean CAN_SHOW_MOVABLE_PIECE = false;
    private static boolean CAN_SHOW_VALID_MOVES = true;
    private static boolean CAN_SHOW_CAPTURES = true;
    private static boolean CAN_SHOW_LATEST_MOVE = true;
    
    /**
     * Reset the game information and settings
     */
    public static void resetSetting() {
        TOP_PLAYER_NAME = "Opponent";
        BOTTOM_PLAYER_NAME = "Player";
        GAME_DURATION = GameDuration.NO_TIMER;
        BOARD_DIRECTION = GameSwitch.NORMAL;
        CAN_CHANGE_TURN = false;
        CAN_SHOW_MOVABLE_PIECE = false;
        CAN_SHOW_VALID_MOVES = true;
        CAN_SHOW_CAPTURES = true;
        CAN_SHOW_LATEST_MOVE = true;
    }
    
    public static void resetRules() {
        CAN_CHANGE_TURN = false;
        CAN_SHOW_MOVABLE_PIECE = false;
        CAN_SHOW_VALID_MOVES = true;
        CAN_SHOW_CAPTURES = true;
        CAN_SHOW_LATEST_MOVE = true;
    }
    
    /**
     * Apply new names for the players
     * @param topPlayerName        String 
     * @param bottomPlayerName     String
     */
    public static void setPlayerNames(final String topPlayerName, final String bottomPlayerName) {
        TOP_PLAYER_NAME = topPlayerName;
        BOTTOM_PLAYER_NAME = bottomPlayerName;
        
        if (TOP_PLAYER_NAME.isBlank())
            TOP_PLAYER_NAME = createRandomName();
        if (BOTTOM_PLAYER_NAME.isBlank())
            BOTTOM_PLAYER_NAME = createRandomName();
    }
    
    // Players Data: Getters and Setters
    public static String getTopPlayerName() {
        return TOP_PLAYER_NAME;
    }
    
    public static String getBotttomPlayerName() {
        return BOTTOM_PLAYER_NAME;
    }
    
    public static void resetPlayerScores() {
        TOP_PLAYER_SCORE = 0;
        BOTTOM_PLAYER_SCORE = 0;
    }
    
    public static void setPlayerScoresByName(final String name) {
        if (name == null) return;
        if (TOP_PLAYER_NAME.equals(name)) {
            TOP_PLAYER_SCORE++;
        } else if (BOTTOM_PLAYER_NAME.equals(name)) {
            BOTTOM_PLAYER_SCORE++;
        }
    }
    
    public static int getTopPlayerScore() {
        return TOP_PLAYER_SCORE;
    }

    public static int getBottomPlayerScore() {
        return BOTTOM_PLAYER_SCORE;
    }
    
    // Game Setting: Getters and Setters
    public static void setGameDuration(final GameDuration duration) {
        GAME_DURATION = duration;
    }
    
    public static GameDuration getGameDuration() {
        return GAME_DURATION;
    }
    
    public static void setDirection(final GameSwitch direction) {
        BOARD_DIRECTION = direction;
    }
    
    public static GameSwitch getDirection() {
        return BOARD_DIRECTION;
    }
    
    public static boolean isGameStart() {
        return GAME_START;
    }
    
    public static void setIfGameStart(final boolean bool) {
        GAME_START = bool;
    }
    
    // Game Rules: Getters
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
    
    // Game Rules: Setters
    public static boolean canChangeTurn() {
        return CAN_CHANGE_TURN;
    }
    
    public static boolean showValidMoves() {
        return CAN_SHOW_VALID_MOVES;
    }
    
    public static boolean showCaptures() {
        return CAN_SHOW_CAPTURES;
    }
    
    public static boolean showMovablePiece() {
        return CAN_SHOW_MOVABLE_PIECE;
    }
    
    public static boolean showLatestMove() {
        return CAN_SHOW_LATEST_MOVE;
    }
    
    /**
     * Shuffle a random alliance(White, Black) for the player
     */
    public static void createRandomAlliance() {
        final Random random = new Random();
        BOARD_DIRECTION = random.nextBoolean() ?
                        GameSwitch.NORMAL :
                        GameSwitch.FLIPPED;
    }
    
    /**
     * Create a random name for the players
     * @return String
     */
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
            public int getTime() {
                return 5 * 60 * 1000;
            }
        },
        TEN_MINUTES {
            @Override
            public int getTime() {
                return 10 * 60 * 1000;
            }
        },
        FIFTEEN_MINUTES {
            @Override
            public int getTime() {
                return 15 * 60 * 1000;
            }
        },
        NO_TIMER {
            @Override
            public int getTime() {
                return 0;
            }
        };

        public abstract int getTime();
    }
}
