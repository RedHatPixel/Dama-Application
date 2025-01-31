package app.panels.popupPanel;

import app.dialog.QuitConfirmation;
import app.frames.MainFrame;
import app.panels.Account;
import app.panels.CardHandlers.CardPanelRegistry;
import app.panels.loginSignUpPanels.Message;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelProfile;
import com.db.service.ServiceProfile;
import com.db.service.ServiceUser;
import com.db.sounds.NotificationManager;
import com.db.token.SessionManager;
import java.sql.SQLException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import utilities.CompManager;
import utilities.TextFieldManager;
import utilities.FontManager;
import utilities.ImageProfileManager;

public class PanelChangeProfile extends JPanel {

    private ServiceUser serviceUser;
    private ServiceProfile serviceProfile;
    private final String USER_HINT = "New Username";
    
    public PanelChangeProfile() {
        initComponents();
        setVisible(false);
        addMouseListener(new MouseAdapter() {});
        init();
        resetProfile();
    }
    
    private void init() {
        serviceUser = new ServiceUser();
        serviceProfile = new ServiceProfile();
        CompManager.setPoppinsFont(title, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(profile, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(aboutme, FontManager.FontName.POPPINS_MEDIUM);
        TextFieldManager.setHint(userName, userName.getForeground(), USER_HINT);
        TextFieldManager.setFont(userName);
        imageButton1.addItemListener(e -> updateImageAppearance(imageButton1, e));
        imageButton2.addItemListener(e -> updateImageAppearance(imageButton2, e));
        imageButton3.addItemListener(e -> updateImageAppearance(imageButton3, e));
        imageButton4.addItemListener(e -> updateImageAppearance(imageButton4, e));
        imageButton5.addItemListener(e -> updateImageAppearance(imageButton5, e));
    }
    
    private void updateImageAppearance(final JRadioButton button, final ItemEvent e) {
        if (e.getStateChange()== ItemEvent.SELECTED) {
            button.setBackground(new Color(102,102,102));
        } else {
            button.setBackground(new Color(48,46,43));
        }
    }
    
    private void resetProfile() {
        TextFieldManager.setHint(userName, userName.getForeground(), USER_HINT);
        imageSelection.clearSelection();
        aboutme.setText("");
        final ModelProfile user = MainFrame.getInstance().getUserProfile();
        if (user != null) {
            profile.setIcon(ImageProfileManager.getProfilePicture(user.getProfilePicture()));
            profile.setText(user.getUserName());
            aboutme.setText(user.getAboutMe());
            switch (user.getProfilePicture()) {
                case "beardedman" -> {
                    imageButton1.setSelected(true);
                }
                case "bussinessman" -> {
                    imageButton2.setSelected(true);
                }
                case "girl" -> {
                    imageButton3.setSelected(true);
                }
                case "housekeeper" -> {
                    imageButton4.setSelected(true);
                }
                case "youngman" -> {
                    imageButton5.setSelected(true);
                }
            }
        }
    }
    
    private void setProfileInformation() {
        final ModelProfile user = MainFrame.getInstance().getUserProfile();
        boolean applyChanges = false;
        if (!userName.getText().isBlank() && !TextFieldManager.isHint(userName, USER_HINT) ||
            (imageSelection.getSelection() == null ? false : 
            !imageSelection.getSelection().getActionCommand().equals(user.getProfilePicture())) ||
            !aboutme.getText().equals(user.getAboutMe())) {
            final int status = new QuitConfirmation(
                "Are you sure to change your profile?",
                "Profile Confirmation", MainFrame.getInstance()).getReturnStatus();
            if (status == QuitConfirmation.RET_NO) {
                return;
            } else {
                applyChanges = true;
            }
        }
            
        final Account account = CardPanelRegistry.getInstance(Account.class);
        final MainFrame main = MainFrame.getInstance();
        if (user != null && applyChanges) {
            try {
                if (aboutme.getText().length() > 255) {
                    main.showMessage(Message.MessageType.ERROR, "Aboutme section is within capacity");
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                    return;
                }
                if (!userName.getText().isBlank() && !TextFieldManager.isHint(userName, USER_HINT)) {
                    if (serviceUser.checkDuplicateUser(userName.getText())) {
                        main.showMessage(Message.MessageType.ERROR, "Username already exist");
                        NotificationManager.Sounds.WRONG_NOTIF.play();
                        return;
                    }
                    user.setUserName(userName.getText());
                }
                final String selectedImage = imageSelection.getSelection() != null ?
                    imageSelection.getSelection().getActionCommand() : "user";
                user.setProfilePicture(selectedImage);
                user.setAboutMe(aboutme.getText());
                if (serviceProfile.updateProfile(user)) {
                    SessionManager.saveSession(user);
                    main.setUserProfile(user);
                    account.displayProfile();
                    main.showMessage(Message.MessageType.SUCCESS, "Account updated successfully");
                    NotificationManager.Sounds.CORRECT_NOTIF.play();
                    setVisible(false);
                } else {
                    main.showMessage(Message.MessageType.ERROR, "Account update failed");
                    NotificationManager.Sounds.CORRECT_NOTIF.play();
                }
            } catch (SQLException e) {
                main.showMessage(Message.MessageType.ERROR, "Error updating profile");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            }
        } else {
            setVisible(false);
        }
    }
    
    @Override
    public void setVisible(final boolean bln) {
        super.setVisible(bln);
        if (bln) {
            aboutme.requestFocus();
            if (DatabaseConnection.getInstance().isConnectionValid()) {
                DatabaseConnection.getInstance().reconnect(List.of(serviceProfile, serviceUser));
            }
            resetProfile();
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(50, 50, 50));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        imageSelection = new javax.swing.ButtonGroup();
        app.panels.loginSignUpPanels.PanelRound panelRound = new app.panels.loginSignUpPanels.PanelRound();
        title = new javax.swing.JLabel();
        javax.swing.JLabel nameLabel = new javax.swing.JLabel();
        profile = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        javax.swing.JPanel pictures = new javax.swing.JPanel();
        imageButton1 = new javax.swing.JRadioButton();
        imageButton2 = new javax.swing.JRadioButton();
        imageButton3 = new javax.swing.JRadioButton();
        imageButton4 = new javax.swing.JRadioButton();
        imageButton5 = new javax.swing.JRadioButton();
        app.scrollPanes.MainScrollPane scrollableText = new app.scrollPanes.MainScrollPane();
        aboutme = new javax.swing.JTextArea();
        javax.swing.JPanel buttonGroup = new javax.swing.JPanel();
        confirmButton = new app.buttons.MainButton();
        cancelButton = new app.buttons.MainButton();

        setFocusCycleRoot(true);
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        panelRound.setMinimumSize(new java.awt.Dimension(600, 450));
        panelRound.setPreferredSize(new java.awt.Dimension(600, 450));
        panelRound.setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title.setText("Profile");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 40, 0, 40);
        panelRound.add(title, gridBagConstraints);

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/username.png"))); // NOI18N
        nameLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        nameLabel.setMinimumSize(new java.awt.Dimension(40, 40));
        nameLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        nameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(10, 70, 10, 0);
        panelRound.add(nameLabel, gridBagConstraints);

        profile.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        profile.setForeground(new java.awt.Color(255, 255, 255));
        profile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/user.png"))); // NOI18N
        profile.setText("Guest");
        profile.setFocusable(false);
        profile.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        profile.setIconTextGap(10);
        profile.setInheritsPopupMenu(false);
        profile.setMaximumSize(new java.awt.Dimension(100, 50));
        profile.setMinimumSize(new java.awt.Dimension(100, 50));
        profile.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 10, 60);
        panelRound.add(profile, gridBagConstraints);

        userName.setBackground(new java.awt.Color(38, 37, 34));
        userName.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userName.setToolTipText("Enter your username");
        userName.setActionCommand("<Not Set>");
        userName.setAutoscrolls(false);
        userName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userName.setCaretColor(new java.awt.Color(102, 102, 102));
        userName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userName.setMinimumSize(new java.awt.Dimension(280, 40));
        userName.setOpaque(true);
        userName.setPreferredSize(new java.awt.Dimension(280, 40));
        userName.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userName.setSelectionColor(new java.awt.Color(48, 46, 43));
        userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 110);
        panelRound.add(userName, gridBagConstraints);

        pictures.setMinimumSize(new java.awt.Dimension(100, 60));
        pictures.setOpaque(false);
        pictures.setPreferredSize(new java.awt.Dimension(100, 60));
        pictures.setLayout(new java.awt.GridLayout());

        imageButton1.setBackground(new java.awt.Color(48, 46, 43));
        imageSelection.add(imageButton1);
        imageButton1.setActionCommand("beardedman");
        imageButton1.setBorder(null);
        imageButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/beardedman.png"))); // NOI18N
        imageButton1.setIconTextGap(0);
        imageButton1.setMultiClickThreshhold(2L);
        imageButton1.setName("selector"); // NOI18N
        imageButton1.setRolloverEnabled(false);
        pictures.add(imageButton1);

        imageButton2.setBackground(new java.awt.Color(48, 46, 43));
        imageSelection.add(imageButton2);
        imageButton2.setActionCommand("bussinessman");
        imageButton2.setBorder(null);
        imageButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/bussinessman.png"))); // NOI18N
        imageButton2.setIconTextGap(0);
        imageButton2.setMultiClickThreshhold(2L);
        imageButton2.setName("selector"); // NOI18N
        imageButton2.setRolloverEnabled(false);
        pictures.add(imageButton2);

        imageButton3.setBackground(new java.awt.Color(48, 46, 43));
        imageSelection.add(imageButton3);
        imageButton3.setActionCommand("girl");
        imageButton3.setBorder(null);
        imageButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/girl.png"))); // NOI18N
        imageButton3.setIconTextGap(0);
        imageButton3.setMultiClickThreshhold(2L);
        imageButton3.setName("selector"); // NOI18N
        imageButton3.setRolloverEnabled(false);
        pictures.add(imageButton3);

        imageButton4.setBackground(new java.awt.Color(48, 46, 43));
        imageSelection.add(imageButton4);
        imageButton4.setActionCommand("housekeeper");
        imageButton4.setBorder(null);
        imageButton4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/housekeeper.png"))); // NOI18N
        imageButton4.setIconTextGap(0);
        imageButton4.setMultiClickThreshhold(2L);
        imageButton4.setName("selector"); // NOI18N
        imageButton4.setRolloverEnabled(false);
        pictures.add(imageButton4);

        imageButton5.setBackground(new java.awt.Color(48, 46, 43));
        imageSelection.add(imageButton5);
        imageButton5.setActionCommand("youngman");
        imageButton5.setBorder(null);
        imageButton5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/youngman.png"))); // NOI18N
        imageButton5.setIconTextGap(0);
        imageButton5.setMultiClickThreshhold(2L);
        imageButton5.setName("selector"); // NOI18N
        imageButton5.setRolloverEnabled(false);
        pictures.add(imageButton5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 50);
        panelRound.add(pictures, gridBagConstraints);

        scrollableText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollableText.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableText.setMinimumSize(new java.awt.Dimension(100, 100));

        aboutme.setBackground(new java.awt.Color(48, 46, 43));
        aboutme.setColumns(50);
        aboutme.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        aboutme.setForeground(new java.awt.Color(255, 255, 255));
        aboutme.setLineWrap(true);
        aboutme.setRows(4);
        aboutme.setTabSize(3);
        aboutme.setWrapStyleWord(true);
        aboutme.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 8, 5, 8));
        aboutme.setCaretColor(new java.awt.Color(255, 255, 255));
        aboutme.setCaretPosition(0);
        aboutme.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        aboutme.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        aboutme.setName(""); // NOI18N
        scrollableText.setViewportView(aboutme);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 50);
        panelRound.add(scrollableText, gridBagConstraints);

        buttonGroup.setOpaque(false);
        buttonGroup.setLayout(new java.awt.GridBagLayout());

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        buttonGroup.add(confirmButton, gridBagConstraints);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        buttonGroup.add(cancelButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panelRound.add(buttonGroup, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(panelRound, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameActionPerformed
        if (!userName.getText().isBlank() && !TextFieldManager.isHint(userName, USER_HINT)) {
            confirmButtonActionPerformed(evt);
        }
    }//GEN-LAST:event_userNameActionPerformed

    private void nameLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLabelMouseClicked
        userName.requestFocus();
    }//GEN-LAST:event_nameLabelMouseClicked

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        setProfileInformation();
    }//GEN-LAST:event_confirmButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea aboutme;
    private app.buttons.MainButton cancelButton;
    private app.buttons.MainButton confirmButton;
    private javax.swing.JRadioButton imageButton1;
    private javax.swing.JRadioButton imageButton2;
    private javax.swing.JRadioButton imageButton3;
    private javax.swing.JRadioButton imageButton4;
    private javax.swing.JRadioButton imageButton5;
    private javax.swing.ButtonGroup imageSelection;
    private javax.swing.JLabel profile;
    private javax.swing.JLabel title;
    private javax.swing.JTextField userName;
    // End of variables declaration//GEN-END:variables
}
