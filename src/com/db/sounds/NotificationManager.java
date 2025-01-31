package com.db.sounds;

public class NotificationManager {
    
    // Constructor: Prevent Instantiation
    private NotificationManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    // Load a sound effect into the static holder
    public static SoundNotification loadSound(final String filePath) {
        final SoundNotification soundEffect = new SoundNotification(filePath);
        return soundEffect;
    }
    
    public static class Sounds {
        
        public static SoundNotification BACKGROUND_MUSIC;
        public static SoundNotification CORRECT_NOTIF;
        public static SoundNotification WRONG_NOTIF;

        // Initialize Each Sounds
        static {
            try {
                CORRECT_NOTIF = loadSound("resources/sounds/correct-notif.wav");
                WRONG_NOTIF = loadSound("resources/sounds/wrong-notif.wav");
                BACKGROUND_MUSIC = loadSound("resources/sounds/login-signup-music.wav");
            } catch (Exception e) {
                System.err.println("Error initializing sounds: " + e.getMessage());
            }
        }
    }
}
