package com.dama.engine.sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public final class SoundEffect {
    private final Clip clip;
    private boolean isMuted;
    private float originalVolume;

    // Constructor: Create a new sound using a filePath
    SoundEffect(final String filePath) {
        Clip clipX = null;
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clipX = AudioSystem.getClip();
            clipX.open(audioInputStream);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
        this.clip = clipX;
        this.isMuted = false;
        this.originalVolume = 1f;
        setVolume(originalVolume);
    }

    public void play() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
                clip.flush();
            }
            clip.setFramePosition(0);
            clip.start();
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

        // Clamp volume to [0.0, 1.0]
        volume = Math.max(0.0f, Math.min(volume, 1.0f));
        originalVolume = volume;
        
        if (isMuted) return;

        final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        final float min = volumeControl.getMinimum(); // e.g., -80.0 dB
        final float max = volumeControl.getMaximum(); // e.g., 6.0206 dB
        final float gain = min + (max - min) * volume;
        volumeControl.setValue(gain);
    }
    
    /**
     * Get the volume of the sound
     * @return float 
     */
    public float getVolume() {
        if (clip == null || !clip.isOpen())
            return 0f;
        
        if (isMuted) return originalVolume;
        
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
    
    /**
     * Mute the sound effect
     */
    public void mute() {
        if (clip == null || !clip.isOpen() || isMuted)
            return;

        isMuted = true;
        final FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(volumeControl.getMinimum()); // Set volume to minimum (mute)
    }
    
    /**
     * Unmute the sound effect
     */
    public void unMute() {
        if (clip == null || !clip.isOpen() || !isMuted)
            return;

        isMuted = false;
        setVolume(originalVolume); // Restore the original volume
    }
    
    /**
     * Check if the clip is muted
     * @return Boolean
     */
    public boolean isMuted() {
        return this.isMuted;
    }
}