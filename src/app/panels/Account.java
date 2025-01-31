package app.panels;

import app.dialog.OkConfirmation;
import app.dialog.QuitConfirmation;
import app.frames.LoginSignUpFrame;
import app.frames.MainFrame;
import utilities.CompManager;
import app.panels.CardHandlers.CardLayoutManager;
import app.panels.CardHandlers.CardPanelRegistry;
import app.panels.loginSignUpPanels.Message;
import com.db.model.ModelProfile;
import com.db.service.ServiceUser;
import com.db.sounds.NotificationManager;
import com.db.token.SessionManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.ImageProfileManager;

public class Account extends CardPanelRegistry {

    public Account() {
        initComponents();
        init();
        displayProfile();
        initialize();
        validate();
        repaint();
    }
    
    @Override
    protected void configurePanel() {
        if (!CardLayoutManager.DESIGN_TIME)
            CardLayoutManager.getInstance(MainDirectory.class).registerPanel(this, getName());
    }

    @Override
    public String getPanelName() {
        return "Account";
    }
    
    private void init() {
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        CompManager.setPoppinsFont(username, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(email, FontManager.FontName.POPPINS_BOLD);
        CompManager.setPoppinsFont(aboutme, FontManager.FontName.POPPINS_MEDIUM);
        CompManager.setPoppinsFont(aboutLabel, FontManager.FontName.POPPINS_BLACK);
    }
    
    public void displayProfile() {
        final MainFrame main = MainFrame.getInstance();
        final ModelProfile user = main.getUserProfile();
        if (main.isLogin()) {
            username.setIcon(ImageProfileManager.getProfilePicture(user.getProfilePicture()));
            username.setText(user.getUserName());
            email.setText(user.getEmail());
            aboutme.setText(user.getAboutMe());
        } else {
            username.setText("Guest");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        app.panels.mainSubPanel.Navigator navigator = new app.panels.mainSubPanel.Navigator();
        javax.swing.JPanel bg = new javax.swing.JPanel();
        javax.swing.JPanel header = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        editButton = new app.buttons.MainButton();
        content = new javax.swing.JPanel();
        email = new javax.swing.JLabel();
        app.scrollPanes.MainScrollPane scrollableText = new app.scrollPanes.MainScrollPane();
        aboutme = new javax.swing.JTextArea();
        aboutLabel = new javax.swing.JLabel();
        javax.swing.JPanel buttonContainer = new javax.swing.JPanel();
        deleteButton = new app.buttons.MainButton();
        logoutButton = new app.buttons.MainButton();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("Account"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        bg.setBackground(new java.awt.Color(48, 46, 43));
        bg.setForeground(new java.awt.Color(255, 255, 255));
        bg.setMinimumSize(new java.awt.Dimension(600, 600));
        bg.setName("bg"); // NOI18N
        bg.setPreferredSize(new java.awt.Dimension(600, 600));
        bg.setLayout(new java.awt.BorderLayout());

        header.setMaximumSize(new java.awt.Dimension(32767, 120));
        header.setMinimumSize(new java.awt.Dimension(100, 120));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(100, 120));
        header.setLayout(new java.awt.GridBagLayout());

        username.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        username.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/profile_icon/user.png"))); // NOI18N
        username.setText("Guest");
        username.setFocusable(false);
        username.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        username.setIconTextGap(10);
        username.setInheritsPopupMenu(false);
        username.setMaximumSize(new java.awt.Dimension(100, 50));
        username.setMinimumSize(new java.awt.Dimension(100, 50));
        username.setPreferredSize(new java.awt.Dimension(100, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        header.add(username, gridBagConstraints);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/selection_icon/settingsIcon.png"))); // NOI18N
        editButton.setText("Edit Profile");
        editButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        editButton.setIconTextGap(18);
        editButton.setMaximumSize(new java.awt.Dimension(180, 45));
        editButton.setMinimumSize(new java.awt.Dimension(180, 45));
        editButton.setPreferredSize(new java.awt.Dimension(180, 45));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        header.add(editButton, gridBagConstraints);

        bg.add(header, java.awt.BorderLayout.NORTH);

        content.setOpaque(false);
        content.setLayout(new java.awt.GridBagLayout());

        email.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        email.setForeground(new java.awt.Color(255, 255, 255));
        email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/email.png"))); // NOI18N
        email.setText("Email@gmail.com");
        email.setToolTipText("");
        email.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        email.setIconTextGap(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        content.add(email, gridBagConstraints);

        scrollableText.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollableText.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableText.setMinimumSize(new java.awt.Dimension(100, 100));

        aboutme.setEditable(false);
        aboutme.setBackground(new java.awt.Color(48, 46, 43));
        aboutme.setColumns(50);
        aboutme.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        aboutme.setForeground(new java.awt.Color(255, 255, 255));
        aboutme.setLineWrap(true);
        aboutme.setRows(4);
        aboutme.setTabSize(3);
        aboutme.setText("\n");
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
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 0);
        content.add(scrollableText, gridBagConstraints);

        aboutLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        aboutLabel.setForeground(new java.awt.Color(102, 153, 255));
        aboutLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        aboutLabel.setText("About Me");
        aboutLabel.setToolTipText("");
        aboutLabel.setIconTextGap(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(40, 40, 0, 0);
        content.add(aboutLabel, gridBagConstraints);

        buttonContainer.setOpaque(false);
        buttonContainer.setLayout(new java.awt.GridBagLayout());

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/setting_icon/broken-heart.png"))); // NOI18N
        deleteButton.setText("Sign Out");
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        deleteButton.setIconTextGap(15);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        buttonContainer.add(deleteButton, gridBagConstraints);

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/setting_icon/delete.png"))); // NOI18N
        logoutButton.setText("Delete Account");
        logoutButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        logoutButton.setIconTextGap(15);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        buttonContainer.add(logoutButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        content.add(buttonContainer, gridBagConstraints);

        bg.add(content, java.awt.BorderLayout.CENTER);

        add(bg, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Account");
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        MainFrame.getInstance().changeProfile.setVisible(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        final MainFrame main = MainFrame.getInstance();
        final ServiceUser service = new ServiceUser();
        final ModelProfile user = main.getUserProfile();
        if (main.isLogin()) {
            int status = new QuitConfirmation(
                "Are you sure to delete the account?", 
                "Delete Confirmation", main).getReturnStatus();
            if (status == QuitConfirmation.RET_YES) {
                status = new OkConfirmation(
                    "Your account will never return.", 
                    "Delete Confirmation", main).getReturnStatus();
                if (status == OkConfirmation.RET_CANCEL) return;
                
                try {
                    if (!service.isConnectionValid()) {
                        main.showMessage(Message.MessageType.ERROR, "Cannot connect to the database.");
                        NotificationManager.Sounds.WRONG_NOTIF.play();
                        return;
                    }
                    
                    if (!service.checkAccountExist(user.getUserID())) {
                        main.showMessage(Message.MessageType.ERROR, "No account was found.");
                        NotificationManager.Sounds.WRONG_NOTIF.play();
                        return;
                    }
                    
                    if (!service.deleteVerifiedAccount(user.getUserID())) {
                        main.showMessage(Message.MessageType.ERROR, "Cannot delete the account.");
                        NotificationManager.Sounds.WRONG_NOTIF.play();
                        return;
                    } 
                    
                    main.showMessage(Message.MessageType.SUCCESS, "Account was deleted.");
                    NotificationManager.Sounds.CORRECT_NOTIF.play();
                    SessionManager.clearSession();
                    status = new QuitConfirmation(
                        "Want to login from another account?", 
                        "Signin Confirmation", main).getReturnStatus();
                    if (status == QuitConfirmation.RET_YES) {
                        main.dispose();
                        new LoginSignUpFrame();
                    } else {
                        main.dispose();
                        new MainFrame(null);
                    }
                } catch (SQLException e) {
                    main.showMessage(Message.MessageType.ERROR, "Error deleting the account.");
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                }
            }
        } else {
            main.showMessage(Message.MessageType.ERROR, "You are currently offline.");
            NotificationManager.Sounds.WRONG_NOTIF.play();
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        final MainFrame mainManager = MainFrame.getInstance();
        if (mainManager.isLogin()) {
            int status = new QuitConfirmation(
                "Are you sure you want to logout?", 
                "Logout Confirmation", mainManager).getReturnStatus();
            if (status == QuitConfirmation.RET_YES) {
                status = new QuitConfirmation(
                    "Want to login from another account?", 
                    "Signin Confirmation", mainManager).getReturnStatus();
                if (status == QuitConfirmation.RET_YES) {
                    mainManager.dispose();
                    new LoginSignUpFrame();
                } else {
                    mainManager.dispose();
                    new MainFrame(null);
                }
                SessionManager.clearSession();
            }
        } else {
            mainManager.showMessage(Message.MessageType.ERROR, "You are currently offline.");
            NotificationManager.Sounds.WRONG_NOTIF.play();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutLabel;
    private javax.swing.JTextArea aboutme;
    private javax.swing.JPanel content;
    private app.buttons.MainButton deleteButton;
    private app.buttons.MainButton editButton;
    private javax.swing.JLabel email;
    private app.buttons.MainButton logoutButton;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
