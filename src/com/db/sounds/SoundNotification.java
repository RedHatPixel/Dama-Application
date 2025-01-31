package com.db.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public final class SoundNotification {
    private final Clip clip;
    private boolean isMuted;
    private float originalVolume;

    // Constructor: Create a new sound using a filePath
    SoundNotification(final String resourcePath) {
        Clip clipX = null;
        try {
            final URL audioSrc = SoundNotification.class.getClassLoader().getResource(resourcePath);
            if (audioSrc == null) {
                System.err.println("Sound file not found: " + resourcePath);
            }
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
            clipX = AudioSystem.getClip();
            clipX.open(audioInputStream);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error loading sound: " + e.getMessage());
        }
        this.clip = clipX;
        this.isMuted = false;
        this.originalVolume = .8f;
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

    /**
     * Stop the sound effect
     */
    public void stop() {
        if (isPlaying())
            clip.stop();
    }
    
    /**
     * Set the volume of the sound
     * @param volume Float value between 0.0 (mute) and 1.0 (maximum volume)
     */
    private void setVolume(float volume) {
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
     * Check if there is clip and is running
     * @return Boolean
     */
    public boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}