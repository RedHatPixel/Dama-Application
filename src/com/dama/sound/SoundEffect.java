package com.dama.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public final class SoundEffect {
    private Clip clip;

    // Constructor: Create a new sound using a filePath
    SoundEffect(final String filePath) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
    }
    
    /**
     * Play the sound effect
     */
    public void play() {
        if (clip != null) {
            if (clip.isRunning()) clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    public void play(double start, double end) {
        if (clip == null || !clip.isOpen()) {
            System.err.println("Clip is not loaded or open.");
            return;
        }
        
        // Get the audio format's frame rate to calculate positions
        final float frameRate = clip.getFormat().getFrameRate();
        final int startFrame = (int) (start * frameRate);
        final int endFrame = (int) (end * frameRate);

        if (startFrame < 0 || endFrame > clip.getFrameLength() || startFrame >= endFrame) {
            System.err.println("Invalid start or end times.");
            return;
        }

        // Play the clip from the start frame to the end frame
        clip.setFramePosition(startFrame);
        clip.start();

        // Create a thread to stop the clip at the end frame
        new Thread(() -> {
            while (clip.isRunning()) {
                if (clip.getFramePosition() >= endFrame) {
                    clip.stop();
                    break;
                }
            }
        }).start();
    }

    /**
     * Stop the sound effect
     */
    public void stop() {
        if (isPlaying())
            clip.stop();
    }
    
    /**
     * Loop the sound effect with limited times
     * @param count     Integer, how many times you want the sound to loop
     */
    public void loop(final int count) {
        if (clip != null)
            clip.loop(count);
    }

    /**
     * Check if there is clip and is running
     * @return Boolean
     */
    boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}