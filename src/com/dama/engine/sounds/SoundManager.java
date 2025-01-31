package com.dama.engine.sounds;

import java.util.Random;

public class SoundManager {
    
    // Constructor: Prevent Instantiation
    private SoundManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
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
    
    public static float getSoundEffectsVolume() {
        final float sMove = Sounds.MOVE_PIECE_SOUNDS.getVolume();
        final float sCapture = Sounds.MOVE_PIECE_SOUNDS.getVolume();
        final float sStart = Sounds.MOVE_PIECE_SOUNDS.getVolume();
        final float sEnd = Sounds.MOVE_PIECE_SOUNDS.getVolume();
        return Math.max(sMove, Math.max(sCapture, Math.max(sStart, Math.max(sMove, sEnd))));
    }
    
    public static void changeSoundEffectsVolume(final float volume) {
        Sounds.MOVE_PIECE_SOUNDS.setVolume(volume);
        Sounds.MOVE_CAPTURE_SOUND.setVolume(volume);
        Sounds.GAME_START_SOUND.setVolume(volume);
        Sounds.GAME_END_SOUND.setVolume(volume);
    }
    
    public static boolean isMutedSoundEffects() {
        final boolean sMove = Sounds.MOVE_PIECE_SOUNDS.isMuted();
        final boolean sCapture = Sounds.MOVE_PIECE_SOUNDS.isMuted();
        final boolean sStart = Sounds.MOVE_PIECE_SOUNDS.isMuted();
        final boolean sEnd = Sounds.MOVE_PIECE_SOUNDS.isMuted();
        return sMove && sCapture && sStart && sEnd;
    }
    
    public static void muteSoundEffects() {
        Sounds.MOVE_PIECE_SOUNDS.mute();
        Sounds.MOVE_CAPTURE_SOUND.mute();
        Sounds.GAME_START_SOUND.mute();
        Sounds.GAME_END_SOUND.mute();
    }
    
    public static void unMuteSoundEffects() {
        Sounds.MOVE_PIECE_SOUNDS.unMute();
        Sounds.MOVE_CAPTURE_SOUND.unMute();
        Sounds.GAME_START_SOUND.unMute();
        Sounds.GAME_END_SOUND.unMute();
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
        
        public static SoundEffect BACKGROUND_MUSIC;
        public static SoundEffect MOVE_PIECE_SOUNDS;
        public static SoundEffect MOVE_CAPTURE_SOUND;
        public static SoundEffect GAME_START_SOUND;
        public static SoundEffect GAME_END_SOUND;
        
        // Initialize Each Sounds
        static {
            try {
                BACKGROUND_MUSIC = loadSound("resources/sounds/background_music.wav");
                MOVE_PIECE_SOUNDS = loadSound("resources/sounds/move_piece.wav");
                MOVE_CAPTURE_SOUND = loadSound("resources/sounds/stationary_kill.wav");
                GAME_START_SOUND = loadSound("resources/sounds/game_start.wav");
                GAME_END_SOUND =   loadSound("resources/sounds/game_end.wav");
                
            } catch (Exception e) {
                System.err.println("Error initializing sounds: " + e.getMessage());
            }
        }
    }
}
