package com.dama.sound;

import java.util.Random;

public class SoundManager {
    
    private static final Random RANDOM = new Random();
    
    // Load a sound effect into the static holder
    public static SoundEffect loadSound(final String filePath) {
        SoundEffect soundEffect = new SoundEffect(filePath);
        return soundEffect;
    }
    
    public static void createRandomPieceSound() {
        if (Sounds.MOVE_PIECE_SOUNDS == null) {
            System.err.println("Move piece sound clip is not loaded!");
            return;
        }
        
        // Randomly pick a segment
        int randomIndex = RANDOM.nextInt(Sounds.MOVE_SEGMENTS.length);
        double[] segment = Sounds.MOVE_SEGMENTS[randomIndex];
        
        Sounds.MOVE_PIECE_SOUNDS.play(segment[0], segment[1]);
    }
    
    public static class Sounds {

        private static double[][] MOVE_SEGMENTS = {
            new double[] {1, 1.4},
            new double[] {2.6, 3},
            new double[] {5.6, 6},
            new double[] {8, 8.4},
            new double[] {12, 12.4},
            new double[] {14.4, 14.8},
            new double[] {16.2, 16.6},
            new double[] {18.6, 19},
            new double[] {21.3, 22},
        };
        public static SoundEffect MOVE_PIECE_SOUNDS;
        public static SoundEffect GAME_START_SOUND;
        public static SoundEffect GAME_END_SOUND;
        
        // Initialize Each Sounds
        static {
            try {
                MOVE_PIECE_SOUNDS = loadSound("sounds/move_piece.wav");
                GAME_START_SOUND = loadSound("sounds/game_start.wav");
                GAME_END_SOUND =   loadSound("sounds/game_end.wav");
            } catch (Exception e) {
                System.err.println("Error initializing sounds: " + e.getMessage());
            }
        }
    }
}
