package app.panels.mainSubPanel;

import app.frames.MainFrame;
import app.frames.Setting;
import app.panels.CardHandlers.*;
import app.panels.*;
import com.dama.gui.controlPanel.PlayerSetting;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.FontManager.*;
import utilities.ImageFiles;
import utilities.ImageManager;

public class Navigator extends javax.swing.JPanel {
    
    public Navigator() {
        initComponents();
        
        setFont(CommonConstants.DEFAULT_FONT);
        title.setFont(FontManager.getFont(FontName.POPPINS_BLACK, FontType.POPPINS, 28));
        title.setIcon(ImageManager.getResizedImage(ImageFiles.DAMA_LOGO_DIR, 40, 40));
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        title = new javax.swing.JButton();
        javax.swing.JPanel content = new javax.swing.JPanel();
        javax.swing.JPanel navigation = new javax.swing.JPanel();
        playButton = new app.buttons.NavButton();
        learnButton = new app.buttons.NavButton();
        aboutButton = new app.buttons.NavButton();
        settingsButton = new app.buttons.NavButton();
        quitButton = new app.buttons.NavButton();

        setBackground(new java.awt.Color(38, 37, 34));
        setForeground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(150, 1500));
        setMinimumSize(new java.awt.Dimension(160, 550));
        setName("navigator"); // NOI18N
        setPreferredSize(new java.awt.Dimension(160, 550));
        setLayout(new java.awt.BorderLayout());

        title.setBackground(new java.awt.Color(38, 37, 34));
        title.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(238, 238, 238));
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/DamaLogo.png"))); // NOI18N
        title.setText("DAMA");
        title.setBorder(null);
        title.setBorderPainted(false);
        title.setContentAreaFilled(false);
        title.setFocusPainted(false);
        title.setFocusable(false);
        title.setIconTextGap(3);
        title.setMaximumSize(new java.awt.Dimension(145, 100));
        title.setMinimumSize(new java.awt.Dimension(145, 100));
        title.setName("title"); // NOI18N
        title.setPreferredSize(new java.awt.Dimension(145, 100));
        title.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleActionPerformed(evt);
            }
        });
        add(title, java.awt.BorderLayout.NORTH);

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setForeground(new java.awt.Color(255, 255, 255));
        content.setFocusable(false);
        content.setMinimumSize(new java.awt.Dimension(150, 400));
        content.setName("content"); // NOI18N
        content.setOpaque(false);
        content.setPreferredSize(new java.awt.Dimension(150, 400));
        content.setLayout(new javax.swing.BoxLayout(content, javax.swing.BoxLayout.Y_AXIS));

        navigation.setBackground(new java.awt.Color(255, 255, 255));
        navigation.setForeground(new java.awt.Color(255, 255, 255));
        navigation.setAlignmentX(0.0F);
        navigation.setAlignmentY(0.0F);
        navigation.setFocusable(false);
        navigation.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        navigation.setMaximumSize(new java.awt.Dimension(3000, 220));
        navigation.setMinimumSize(navigation.getPreferredSize());
        navigation.setName("navigation"); // NOI18N
        navigation.setOpaque(false);
        navigation.setPreferredSize(new java.awt.Dimension(150, 220));
        navigation.setRequestFocusEnabled(false);
        navigation.setLayout(new java.awt.GridBagLayout());

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/playIcon.png"))); // NOI18N
        playButton.setText("Play");
        playButton.setAlignmentY(0.0F);
        playButton.setIconTextGap(14);
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(playButton, gridBagConstraints);

        learnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/graduationCapIcon.png"))); // NOI18N
        learnButton.setText("Learn");
        learnButton.setAlignmentY(0.0F);
        learnButton.setIconTextGap(14);
        learnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learnButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(learnButton, gridBagConstraints);

        aboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/infoIcon.png"))); // NOI18N
        aboutButton.setText("About");
        aboutButton.setAlignmentY(0.0F);
        aboutButton.setIconTextGap(14);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(aboutButton, gridBagConstraints);

        settingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/settingsIcon.png"))); // NOI18N
        settingsButton.setText("Settings");
        settingsButton.setAlignmentY(0.0F);
        settingsButton.setIconTextGap(14);
        settingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(settingsButton, gridBagConstraints);

        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/exitIcon.png"))); // NOI18N
        quitButton.setText("Quit");
        quitButton.setAlignmentY(0.0F);
        quitButton.setIconTextGap(14);
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(quitButton, gridBagConstraints);

        content.add(navigation);
        navigation.getAccessibleContext().setAccessibleName("navigation");

        add(content, java.awt.BorderLayout.CENTER);
        content.getAccessibleContext().setAccessibleName("content");

        getAccessibleContext().setAccessibleName("navigator");
        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    public boolean setNewGame() {
        final PlayerSetting playSetting = CardPanelRegistry.getInstance(PlayerSetting.class);
        return playSetting.quitConfirmation();
    }
    
    public boolean isGamePlaying() {
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            return CardPanelRegistry.getInstance(GamePlay.class).getTable().isPlaying();
        }
        return false;
    }
    
    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        if (isGamePlaying()) {
            if (!setNewGame())   return;
        }
        
        if (CardLayoutManager.isInstanced(MainFrame.class)) {
            final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
            mainManager.showPanel(CardPanelRegistry.getInstance(MainMenu.class));
        }
        
        if (CardPanelRegistry.isInstanced(MainMenu.class)) {
            final MainMenu mainMenu = CardPanelRegistry.getInstance(MainMenu.class);
            mainMenu.updateHistory();
            mainMenu.setButtonStyle();
            mainMenu.setFooterSize();   
        }
    }//GEN-LAST:event_titleActionPerformed

    private void learnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnButtonActionPerformed
        if (isGamePlaying()) {
            if (!setNewGame())   return;
        }
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(Tutorial.class));
    }//GEN-LAST:event_learnButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        final MainFrame mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.closeWindow();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if (isGamePlaying()) {
            if (!setNewGame())   return;
        }
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(GamePlay.class));
    }//GEN-LAST:event_playButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        if (isGamePlaying()) {
            if (!setNewGame())   return;
        }
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(About.class));
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void settingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsButtonActionPerformed
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        new Setting(mainManager, true).setVisible(true);
    }//GEN-LAST:event_settingsButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.buttons.NavButton aboutButton;
    private app.buttons.NavButton learnButton;
    private app.buttons.NavButton playButton;
    private app.buttons.NavButton quitButton;
    private app.buttons.NavButton settingsButton;
    private javax.swing.JButton title;
    // End of variables declaration//GEN-END:variables
}
