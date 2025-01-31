package app.dialog;

import app.dialog.QuitConfirmation;
import com.dama.engine.sounds.SoundManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.ImageFiles;

public class Setting extends JDialog {

    private int sMusicVolume;
    private int sEffectVolume;
    private boolean muteMusicB;
    private boolean muteSoundEffectB;
    
    public Setting(final Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSliderVolumes();
        setMuteCheckBox();
        setIconImage(ImageFiles.DAMA_LOGO);
        setFont(CommonConstants.DEFAULT_FONT);
        setResizable(false);
        title.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, title.getFont().getSize()));
        musicLabel.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BOLD, FontManager.FontType.POPPINS, musicLabel.getFont().getSize()));
        soundEffectLabel.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BOLD, FontManager.FontType.POPPINS, soundEffectLabel.getFont().getSize()));

        final String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitConfirmation();
            }
        });
        
        validate();
        repaint();
    }
    
    private void setSliderVolumes() {
        sMusicVolume = (int) (SoundManager.Sounds.BACKGROUND_MUSIC.getVolume() * 100f);
        sEffectVolume = (int) (SoundManager.getSoundEffectsVolume() * 100f);
        soundBgVolume.setValue(sMusicVolume);
        soundEffectVolume.setValue(sEffectVolume);
    }
    
    private void setMuteCheckBox() {
        muteMusicB = SoundManager.Sounds.BACKGROUND_MUSIC.isMuted();
        muteSoundEffectB = SoundManager.isMutedSoundEffects();
        muteMusic.setSelected(muteMusicB);
        muteSoundEffect.setSelected(muteSoundEffectB);
    }
    
    private void setNewValues() {
        final int music = soundBgVolume.getValue();
        final int soundEffect = soundEffectVolume.getValue();
        final float bgVolume = music / 100f;
        final float effectVolume = soundEffect / 100f;
        SoundManager.Sounds.BACKGROUND_MUSIC.setVolume(bgVolume);
        SoundManager.changeSoundEffectsVolume(effectVolume);
        final boolean musicMuted = muteMusic.isSelected();
        if (musicMuted) SoundManager.Sounds.BACKGROUND_MUSIC.mute();
        else SoundManager.Sounds.BACKGROUND_MUSIC.unMute();
        boolean soundMuted = muteSoundEffect.isSelected();
        if (soundMuted) SoundManager.muteSoundEffects(); 
        else SoundManager.unMuteSoundEffects();
    }
    
    private boolean isChanged() {
        final float newMusicVolume = soundBgVolume.getValue();
        final float newSoundEffectVolume = soundEffectVolume.getValue();
        final boolean newMuteMusic = muteMusic.isSelected();
        final boolean newMuteSoundEffect = muteSoundEffect.isSelected();
        return  (newMusicVolume != sMusicVolume) ||
                (newSoundEffectVolume != sEffectVolume) ||
                (newMuteMusic != muteMusicB) ||
                (newMuteSoundEffect != muteSoundEffectB);
    }
    
    private void quitConfirmation() {
        final int status = new QuitConfirmation(
                "Your changes are not save yet? Yes to quit", 
                "Quit Confirmation", null).getReturnStatus();
        if (status == app.dialog.QuitConfirmation.RET_YES) {
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel bg = new javax.swing.JPanel();
        confirmButton = new app.buttons.MainButton();
        quitButton = new app.buttons.MainButton();
        title = new javax.swing.JLabel();
        musicLabel = new javax.swing.JLabel();
        soundBgVolume = new javax.swing.JSlider();
        soundEffectLabel = new javax.swing.JLabel();
        soundEffectVolume = new javax.swing.JSlider();
        muteSoundEffect = new javax.swing.JCheckBox();
        muteMusic = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Setting");
        setBackground(new java.awt.Color(48, 46, 43));
        setFocusable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        bg.setBackground(new java.awt.Color(48, 46, 43));
        bg.setForeground(new java.awt.Color(255, 255, 255));

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Cancel");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("SETTING");

        musicLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        musicLabel.setForeground(new java.awt.Color(255, 255, 255));
        musicLabel.setText("Music");

        soundBgVolume.setBackground(new java.awt.Color(48, 46, 43));
        soundBgVolume.setMinimum(60);

        soundEffectLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        soundEffectLabel.setForeground(new java.awt.Color(255, 255, 255));
        soundEffectLabel.setText("Sound Effect");

        soundEffectVolume.setBackground(new java.awt.Color(48, 46, 43));
        soundEffectVolume.setMinimum(60);

        muteSoundEffect.setBorder(null);
        muteSoundEffect.setContentAreaFilled(false);
        muteSoundEffect.setFocusPainted(false);
        muteSoundEffect.setFocusable(false);
        muteSoundEffect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        muteSoundEffect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        muteSoundEffect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        muteSoundEffect.setIconTextGap(0);
        muteSoundEffect.setMargin(new java.awt.Insets(0, 0, 0, 0));
        muteSoundEffect.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        muteSoundEffect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteSoundEffectActionPerformed(evt);
            }
        });

        muteMusic.setBorder(null);
        muteMusic.setContentAreaFilled(false);
        muteMusic.setFocusPainted(false);
        muteMusic.setFocusable(false);
        muteMusic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        muteMusic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        muteMusic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        muteMusic.setIconTextGap(0);
        muteMusic.setMargin(new java.awt.Insets(0, 0, 0, 0));
        muteMusic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        muteMusic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muteMusicActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(muteSoundEffect, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundEffectVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(muteMusic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundBgVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(41, 41, 41))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soundBgVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(muteMusic, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soundEffectVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(muteSoundEffect, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        quitConfirmation();
    }//GEN-LAST:event_closeDialog

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        setNewValues();
        this.dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        if (isChanged()) quitConfirmation();
        else this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void muteMusicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteMusicActionPerformed
    }//GEN-LAST:event_muteMusicActionPerformed

    private void muteSoundEffectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muteSoundEffectActionPerformed
    }//GEN-LAST:event_muteSoundEffectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.buttons.MainButton confirmButton;
    private javax.swing.JLabel musicLabel;
    private javax.swing.JCheckBox muteMusic;
    private javax.swing.JCheckBox muteSoundEffect;
    private app.buttons.MainButton quitButton;
    private javax.swing.JSlider soundBgVolume;
    private javax.swing.JLabel soundEffectLabel;
    private javax.swing.JSlider soundEffectVolume;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
