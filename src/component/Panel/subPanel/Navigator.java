package component.Panel.subPanel;

import javax.swing.ImageIcon;
import utilities.CommonConstants;
import utilities.Directory;
import utilities.FontManager;
import utilities.FontManager.*;
import utilities.Configurations;
import main.MainFrame;

public class Navigator extends javax.swing.JPanel {
    
    public Navigator() {
        initComponents();
        
        setFont(CommonConstants.DEFAULT_FONT);
        title.setFont(FontManager.getFont(FontName.POPPINS_BLACK, FontType.POPPINS, 28));
        title.setIcon(Configurations.resizeIcon((ImageIcon) title.getIcon(), 40, 40));
        
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
        playButton = new component.Button.NavButton();
        learnButton = new component.Button.NavButton();
        aboutButton = new component.Button.NavButton();
        settingsButton = new component.Button.NavButton();
        profileButton = new component.Button.NavButton();
        quitButton = new component.Button.NavButton();

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
        navigation.setMaximumSize(new java.awt.Dimension(3000, 250));
        navigation.setMinimumSize(navigation.getPreferredSize());
        navigation.setName("navigation"); // NOI18N
        navigation.setOpaque(false);
        navigation.setPreferredSize(new java.awt.Dimension(150, 250));
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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(settingsButton, gridBagConstraints);

        profileButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/profileIcon.png"))); // NOI18N
        profileButton.setText("Profile");
        profileButton.setAlignmentY(0.0F);
        profileButton.setIconTextGap(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        navigation.add(profileButton, gridBagConstraints);

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

    private void titleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleActionPerformed
        
        final MainFrame main = (MainFrame) Directory.getParent(Directory.ParentName.MAIN_FRAME);
        
        if (main != null)
            main.setDirectory(Directory.Panel.MAIN_MENU);
    }//GEN-LAST:event_titleActionPerformed

    private void learnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnButtonActionPerformed
        
        final MainFrame main = (MainFrame) Directory.getParent(Directory.ParentName.MAIN_FRAME);
        
        if (main != null)
            main.setDirectory(Directory.Panel.TUTORIAL);
    }//GEN-LAST:event_learnButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        
        final MainFrame main = (MainFrame) Directory.getParent(Directory.ParentName.MAIN_FRAME);
        final int status = new component.OptionPane.QuitConfirmation(main, true).getReturnStatus();
        
        if (status == component.OptionPane.QuitConfirmation.RET_YES) {
            main.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_quitButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        
        final MainFrame main = (MainFrame) Directory.getParent(Directory.ParentName.MAIN_FRAME);
        
        if (main != null)
            main.setDirectory(Directory.Panel.GAME_PLAY);
    }//GEN-LAST:event_playButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button.NavButton aboutButton;
    private component.Button.NavButton learnButton;
    private component.Button.NavButton playButton;
    private component.Button.NavButton profileButton;
    private component.Button.NavButton quitButton;
    private component.Button.NavButton settingsButton;
    private javax.swing.JButton title;
    // End of variables declaration//GEN-END:variables
}
