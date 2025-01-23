package com.dama.gui._configurations.game;

import com.dama.gui._configurations.dependencies.Orientation;
import java.util.Random;

public class GameUtils {
    
    // Constructor: Prevent Instantiation
    private GameUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    /**
     * Shuffle a random alliance(White, Black) for the player
     * @return Orientation
     */
    public static Orientation createRandomOrientation() {
        final Random random = new Random();
        return random.nextBoolean() ?
                    Orientation.NORMAL :
                    Orientation.FLIPPED;
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
}
