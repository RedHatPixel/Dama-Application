package com.dama.engine.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public final class SoundEffect {
    private final Clip clip;

    // Constructor: Create a new sound using a filePath
    SoundEffect(final String filePath) {
        Clip clipX = null;
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clipX = AudioSystem.getClip();
            clipX.open(audioInputStream);
            setVolume(1f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
        this.clip = clipX;
    }
    
    /**
     * Play the sound effect
     */
    public void play() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                clip.flush();
            }
            clip.setFramePosition(0);
            clip.start();
            
            synchronized (clip) {
                clip.start();
            }
        }
    }
    
    public void play(double start, double end) {
        if (clip == null || !clip.isOpen()) {
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
     * Loop the sound effect infinitely
     */
    public void loopForever() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    
    /**
     * Set the volume of the sound
     * @param volume Float value between 0.0 (mute) and 1.0 (maximum volume)
     */
    public void setVolume(float volume) {
        if (clip == null || !clip.isOpen())
            return;

        final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        final float min = volumeControl.getMinimum();
        final float max = volumeControl.getMaximum();
        final float gain = min + (max - min) * volume;
        volumeControl.setValue(gain);
    }
    
    /**
     * Get the volume of the sound
     * @return float 
     */
    public float getVolume() {
        if (clip == null || !clip.isOpen())
            return 0;
        
        final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        final float min = volumeControl.getMinimum();
        final float max = volumeControl.getMaximum();
        final float currentGain = volumeControl.getValue();
        return (currentGain - min) / (max - min);
    }

    /**
     * Check if there is clip and is running
     * @return Boolean
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}