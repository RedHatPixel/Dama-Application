package appGui.frames;

import com.dama.engine.sounds.SoundManager;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.ImageFiles;

public class SettingFrame extends javax.swing.JFrame {

    public SettingFrame() {
        initComponents();
        setIconImage(ImageFiles.DAMA_LOGO);
        setFont(CommonConstants.DEFAULT_FONT);
        setResizable(false);
        setVisible(true);
        
        title.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, title.getFont().getSize()));
        musicLabel.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BOLD, FontManager.FontType.POPPINS, musicLabel.getFont().getSize()));
        soundEffectLabel.setFont(
                FontManager.getFont(
                        FontManager.FontName.POPPINS_BOLD, FontManager.FontType.POPPINS, soundEffectLabel.getFont().getSize()));
                
        setSliderVolumes();
        validate();
        repaint();
    }
    
    private void setSliderVolumes() {
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
                "Quit Confirmation", this).getReturnStatus();
        if (status == appGui.frames.QuitConfirmation.RET_YES) {
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel bg = new javax.swing.JPanel();
        soundEffectVolume = new javax.swing.JSlider();
        soundBgVolume = new javax.swing.JSlider();
        quitButton = new appGui.buttons.MainButton();
        confirmButton = new appGui.buttons.MainButton();
        soundEffectLabel = new javax.swing.JLabel();
        musicLabel = new javax.swing.JLabel();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Setting Frame");
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(430, 400));
        setMinimumSize(new java.awt.Dimension(430, 400));
        setPreferredSize(new java.awt.Dimension(430, 400));
        setResizable(false);
        setSize(new java.awt.Dimension(430, 400));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                SettingFrame.this.windowClosing(evt);
            }
        });

        bg.setBackground(new java.awt.Color(48, 46, 43));
        bg.setForeground(new java.awt.Color(255, 255, 255));

        soundEffectVolume.setBackground(new java.awt.Color(48, 46, 43));
        soundEffectVolume.setValue(0);

        soundBgVolume.setBackground(new java.awt.Color(48, 46, 43));
        soundBgVolume.setValue(0);

        quitButton.setText("Cancel");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        soundEffectLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        soundEffectLabel.setForeground(new java.awt.Color(255, 255, 255));
        soundEffectLabel.setLabelFor(soundEffectVolume);
        soundEffectLabel.setText("Sound Effect");

        musicLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        musicLabel.setForeground(new java.awt.Color(255, 255, 255));
        musicLabel.setLabelFor(soundBgVolume);
        musicLabel.setText("Music");

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("SETTING");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soundEffectVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soundBgVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundBgVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(soundEffectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundEffectVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void windowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosing
        quitConfirmation();
    }//GEN-LAST:event_windowClosing

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
        if (isChanged(bgVolume, effectVolume))
            quitConfirmation();
        else
            this.dispose();
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
