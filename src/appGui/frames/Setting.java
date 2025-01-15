package appGui.frames;

import com.dama.engine.sounds.SoundManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.ImageFiles;

public class Setting extends JDialog {

    public Setting(final Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSliderVolumes();
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
    
    public void setSliderVolumes() {
        final float sMusicVolume = SoundManager.Sounds.BACKGROUND_MUSIC.getVolume();
        final float sEffectVolume = SoundManager.getSoundEffectsVolume();
        soundBgVolume.setValue((int) (sMusicVolume * 100.0f));
        soundEffectVolume.setValue((int) (sEffectVolume * 100.0f));
    }
    
    private boolean isChanged(final float music, final float soundEffect) {
        final float sMusicVolume = SoundManager.Sounds.BACKGROUND_MUSIC.getVolume();
        final float sEffectVolume = SoundManager.getSoundEffectsVolume();
        return !(music == sMusicVolume && soundEffect == sEffectVolume);
    }
    
    private void quitConfirmation() {
        final int status = new QuitConfirmation(
                "Are you sure you want to exit the setting?", 
                "Quit Confirmation", null).getReturnStatus();
        if (status == appGui.frames.QuitConfirmation.RET_YES) {
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel bg = new javax.swing.JPanel();
        confirmButton = new appGui.buttons.MainButton();
        quitButton = new appGui.buttons.MainButton();
        title = new javax.swing.JLabel();
        musicLabel = new javax.swing.JLabel();
        soundBgVolume = new javax.swing.JSlider();
        soundEffectLabel = new javax.swing.JLabel();
        soundEffectVolume = new javax.swing.JSlider();

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

        soundEffectLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        soundEffectLabel.setForeground(new java.awt.Color(255, 255, 255));
        soundEffectLabel.setText("Sound Effect");

        soundEffectVolume.setBackground(new java.awt.Color(48, 46, 43));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(soundEffectVolume, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                            .addComponent(soundBgVolume, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundBgVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundEffectVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        final int music = soundBgVolume.getValue();
        final int soundEffect = soundEffectVolume.getValue();
        final float bgVolume = music / 100.0f;
        final float effectVolume = soundEffect / 100.0f;
        SoundManager.Sounds.BACKGROUND_MUSIC.setVolume(bgVolume);
        SoundManager.changeSoundEffectsVolume(effectVolume);
        this.dispose();
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        final int music = soundBgVolume.getValue();
        final int soundEffect = soundEffectVolume.getValue();
        final float bgVolume = music / 100.0f;
        final float effectVolume = soundEffect / 100.0f;
        if (isChanged(bgVolume, effectVolume)) quitConfirmation();
        else this.dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private appGui.buttons.MainButton confirmButton;
    private javax.swing.JLabel musicLabel;
    private appGui.buttons.MainButton quitButton;
    private javax.swing.JSlider soundBgVolume;
    private javax.swing.JLabel soundEffectLabel;
    private javax.swing.JSlider soundEffectVolume;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
