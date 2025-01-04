package com.dama.gui;
import com.dama.gui.BoardPanel.Direction;

public final class GameInfo {
    
    public static String TopPlayerName = "Opponent";
    public static String BottomPlayerName = "Player";
    public static GameDuration duration = GameDuration.FIVE_MINUTES;
    public static Direction boardDirection = Direction.NORMAL;
    
    public static boolean isChangingTurn = false;
    public static boolean showValidMoves = true;
    public static boolean showCapturable = true;

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
        NULL {
            @Override
            int getTime() {
                return 0;
            }
        };

        abstract int getTime();
    }
}
