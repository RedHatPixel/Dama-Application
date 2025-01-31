package app.panels;

import app.dialog.OkConfirmation;
import utilities.CompManager;
import app.frames.LoginSignUpFrame;
import app.frames.MainFrame;
import app.dialog.QuitConfirmation;
import app.panels.CardHandlers.CardLayoutManager;
import app.panels.CardHandlers.CardPanelRegistry;
import app.panels.CardHandlers.PanelCardManager;
import app.panels.loginSignUpPanels.Message;
import com.dama.gui._configurations.dependencies.Duration;
import com.dama.gui.controlPanel.MultiplayerSetting;
import com.dama.gui.controlPanel.PlayerSetting;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelHistory;
import com.db.model.ModelProfile;
import com.db.sounds.NotificationManager;
import com.db.token.SessionManager;
import utilities.CommonConstants;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import utilities.FontManager;
import utilities.ImageFiles;
import utilities.ImageLoader;
import utilities.ImageProfileManager;

public final class MainMenu extends CardPanelRegistry {

    public MainMenu() {
        initComponents();
        init();
        setProfile();
        setButtonStyle();
        setFooterSize();
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
        return "Main Menu";
    }
    
    public void init() {
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                learnImage.setIcon(
                        ImageLoader.getResizedImage(
                                ImageFiles.LEARN_DIR, 
                                (int) learnImage.getSize().getWidth(),
                                (int) (learnImage.getSize().getHeight())));
                aboutImage.setIcon(
                            ImageLoader.getResizedImage(
                                    ImageFiles.ABOUT_DIR, 
                                    (int) aboutImage.getSize().getWidth(), 
                                    (int) (aboutImage.getSize().getHeight())));
            }
        });
        CompManager.setPoppinsFont(profile, FontManager.FontName.POPPINS_BLACK);
    }
    
    public void updateHistory() {
        try {
            history.refreshContent();
        } catch (SQLException e) {
            System.err.println("Something went wrong while loading all history -> History line 27");
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void setButtonStyle() {
        if (!CardLayoutManager.DESIGN_TIME) {
            try {
                if (!MainFrame.isInstance()) return;
                final MainFrame mainManager = MainFrame.getInstance();
                final ModelProfile user = mainManager.getUserProfile();
                final boolean online = mainManager.getServiceHistory().isConnectionValid();
                ModelHistory gameHistory;

                if (mainManager.isLogin() && online) {
                    gameHistory = mainManager.getServiceHistory().getLatestGame(user.getUserID());
                } else {
                    final int size = mainManager.getLocalHistory().size();
                    gameHistory = mainManager.getLocalHistory().isEmpty() ?
                            null : mainManager.getLocalHistory().get(size < 1 ? 0 : size - 1);
                }
                
                if (gameHistory != null) {
                    final String gameType = gameHistory.getGameType();
                    if (gameType != null) {
                        switch (gameType) {
                            case "Bullet" -> {
                                startButton.setIcon(ImageFiles.BULLET);
                                startButton.setText("Play    " + Duration.BULLET.getFormattedTime());
                            }
                            case "Blitz" -> {
                                startButton.setIcon(ImageFiles.BOLT);
                                startButton.setText("Play    " + Duration.BLITZ.getFormattedTime());
                            }
                            case "Rapid" -> {
                                startButton.setIcon(ImageFiles.STOP_WATCH);
                                startButton.setText("Play    " + Duration.RAPID.getFormattedTime());
                            }
                            case "Infinite" -> {
                                startButton.setIcon(ImageFiles.LINK);
                                startButton.setText("Play");
                            }
                        }
                    }
                } else {
                    startButton.setIcon(ImageFiles.BULLET);
                    startButton.setText("Play");
                }
            } catch (SQLException e) {
                System.err.println("Something went wrong while loading play button -> MainMenu line 80");
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public void setProfile() {
        if (!CardLayoutManager.DESIGN_TIME) {
            if (!MainFrame.isInstance()) return;
            final MainFrame main = MainFrame.getInstance();
            final ModelProfile user = main.getUserProfile();
            if (main.isLogin()) {
                profile.setIcon(ImageProfileManager.getProfilePicture(user.getProfilePicture()));
                profile.setText(user.getUserName());
            } else {
                profile.setText("Guest");
            }
        }
    }
    
    public void setFooterSize() {
        SwingUtilities.invokeLater(() -> {
            final Dimension footerMinSize = footer.getMinimumSize();
            final Dimension historySize = history.getSize();

            int padding = 0;
            final int parentHeightSize = Math.max(historySize.height, footerMinSize.height);
            if (parentHeightSize != footerMinSize.height) padding = 70;

            footer.setPreferredSize(new Dimension(footer.getWidth(), parentHeightSize + padding));
            footer.revalidate();
            footer.repaint();

            final Dimension mainBodySize = mainBody.getPreferredSize();
            final Dimension headerSize = header.getPreferredSize();
            final Dimension bodySize = body.getPreferredSize();
            final Dimension footerSize = footer.getPreferredSize(); 

            final int height = headerSize.height + bodySize.height;
            final int overallSize = height + footerSize.height;
            final int width = mainBodySize.width;
            
            mainBody.setPreferredSize(new Dimension(width, overallSize));
            mainBody.setSize(new Dimension(width, overallSize));
            mainBody.revalidate();
            mainBody.repaint();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        app.panels.mainSubPanel.Navigator navigator = new app.panels.mainSubPanel.Navigator();
        app.scrollPanes.MainScrollPane mainScrollPane = new app.scrollPanes.MainScrollPane();
        mainBody = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        profile = new javax.swing.JLabel();
        setting = new javax.swing.JPanel();
        accountButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        body = new javax.swing.JPanel();
        javax.swing.JPanel leftPart = new javax.swing.JPanel();
        startButton = new app.buttons.MainButton();
        multiplayerButton = new app.buttons.MainButton();
        javax.swing.JPanel rightPart = new javax.swing.JPanel();
        learnContainer = new javax.swing.JPanel();
        learnButton = new app.buttons.MainButton();
        learnImage = new javax.swing.JLabel();
        javax.swing.JPanel AboutContainer = new javax.swing.JPanel();
        aboutImage = new javax.swing.JLabel();
        aboutButton = new app.buttons.MainButton();
        footer = new javax.swing.JPanel();
        history = new com.dama.gui.historyPanel.History();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("Main Menu"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setAutoscrolls(true);
        mainScrollPane.setMinimumSize(new java.awt.Dimension(540, 600));
        mainScrollPane.setPreferredSize(new java.awt.Dimension(540, 600));

        mainBody.setBackground(getBackground());
        mainBody.setForeground(new java.awt.Color(255, 255, 255));
        mainBody.setMinimumSize(mainBody.getPreferredSize());
        mainBody.setName(""); // NOI18N
        mainBody.setPreferredSize(new java.awt.Dimension(540, 550));
        mainBody.setLayout(new javax.swing.BoxLayout(mainBody, javax.swing.BoxLayout.PAGE_AXIS));

        header.setBackground(getBackground());
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setMaximumSize(new java.awt.Dimension(32767, 100));
        header.setMinimumSize(new java.awt.Dimension(700, 100));
        header.setName("header"); // NOI18N
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(700, 100));
        header.setLayout(new java.awt.GridBagLayout());

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
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 30);
        header.add(profile, gridBagConstraints);

        setting.setFocusable(false);
        setting.setMinimumSize(new java.awt.Dimension(200, 100));
        setting.setOpaque(false);
        setting.setPreferredSize(new java.awt.Dimension(140, 100));
        setting.setLayout(new java.awt.GridBagLayout());

        accountButton.setBackground(new java.awt.Color(47, 46, 43));
        accountButton.setForeground(new java.awt.Color(255, 255, 255));
        accountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/setting_icon/setting.png"))); // NOI18N
        accountButton.setBorder(null);
        accountButton.setBorderPainted(false);
        accountButton.setContentAreaFilled(false);
        accountButton.setFocusPainted(false);
        accountButton.setFocusable(false);
        accountButton.setRolloverEnabled(false);
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        setting.add(accountButton, gridBagConstraints);

        logoutButton.setBackground(new java.awt.Color(47, 46, 43));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/setting_icon/signout.png"))); // NOI18N
        logoutButton.setBorder(null);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusable(false);
        logoutButton.setRolloverEnabled(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        setting.add(logoutButton, gridBagConstraints);

        quitButton.setBackground(new java.awt.Color(47, 46, 43));
        quitButton.setForeground(new java.awt.Color(255, 255, 255));
        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/setting_icon/quit.png"))); // NOI18N
        quitButton.setBorder(null);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.setFocusable(false);
        quitButton.setRolloverEnabled(false);
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        setting.add(quitButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        header.add(setting, gridBagConstraints);

        mainBody.add(header);

        body.setBackground(getBackground());
        body.setForeground(new java.awt.Color(255, 255, 255));
        body.setMaximumSize(new java.awt.Dimension(32767, 250));
        body.setMinimumSize(new java.awt.Dimension(700, 250));
        body.setName("body"); // NOI18N
        body.setOpaque(false);
        body.setPreferredSize(new java.awt.Dimension(700, 250));
        body.setLayout(new javax.swing.BoxLayout(body, javax.swing.BoxLayout.LINE_AXIS));

        leftPart.setBackground(getBackground());
        leftPart.setForeground(new java.awt.Color(255, 255, 255));
        leftPart.setMaximumSize(new java.awt.Dimension(350, 250));
        leftPart.setMinimumSize(new java.awt.Dimension(300, 250));
        leftPart.setName(""); // NOI18N
        leftPart.setOpaque(false);
        leftPart.setPreferredSize(new java.awt.Dimension(300, 250));
        leftPart.setLayout(new java.awt.GridBagLayout());

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/bolt.png"))); // NOI18N
        startButton.setText("Play");
        startButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        startButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        startButton.setIconTextGap(10);
        startButton.setMaximumSize(new java.awt.Dimension(230, 65));
        startButton.setMinimumSize(new java.awt.Dimension(230, 65));
        startButton.setPreferredSize(new java.awt.Dimension(230, 65));
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        leftPart.add(startButton, gridBagConstraints);

        multiplayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/handshake.png"))); // NOI18N
        multiplayerButton.setText("Multiplayer");
        multiplayerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        multiplayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        multiplayerButton.setIconTextGap(10);
        multiplayerButton.setMaximumSize(new java.awt.Dimension(230, 65));
        multiplayerButton.setMinimumSize(new java.awt.Dimension(230, 65));
        multiplayerButton.setPreferredSize(new java.awt.Dimension(230, 65));
        multiplayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplayerButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        leftPart.add(multiplayerButton, gridBagConstraints);

        body.add(leftPart);

        rightPart.setBackground(getBackground());
        rightPart.setForeground(new java.awt.Color(255, 255, 255));
        rightPart.setMaximumSize(new java.awt.Dimension(32767, 250));
        rightPart.setMinimumSize(rightPart.getPreferredSize());
        rightPart.setName(""); // NOI18N
        rightPart.setOpaque(false);
        rightPart.setPreferredSize(new java.awt.Dimension(300, 250));
        rightPart.setLayout(new java.awt.GridBagLayout());

        learnContainer.setBackground(new java.awt.Color(38, 37, 34));
        learnContainer.setMinimumSize(new java.awt.Dimension(100, 100));
        learnContainer.setLayout(new java.awt.BorderLayout());

        learnButton.setText("Learn");
        learnButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        learnButton.setIconTextGap(10);
        learnButton.setMaximumSize(new java.awt.Dimension(180, 200));
        learnButton.setMinimumSize(new java.awt.Dimension(180, 200));
        learnButton.setPreferredSize(new java.awt.Dimension(180, 60));
        learnButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        learnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                learnButtonActionPerformed(evt);
            }
        });
        learnContainer.add(learnButton, java.awt.BorderLayout.SOUTH);

        learnImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        learnImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        learnImage.setMaximumSize(learnContainer.getPreferredSize());
        learnImage.setMinimumSize(learnContainer.getPreferredSize());
        learnImage.setPreferredSize(new java.awt.Dimension(100, 100));
        learnContainer.add(learnImage, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        rightPart.add(learnContainer, gridBagConstraints);

        AboutContainer.setBackground(new java.awt.Color(38, 37, 34));
        AboutContainer.setMinimumSize(new java.awt.Dimension(100, 100));
        AboutContainer.setLayout(new java.awt.BorderLayout());

        aboutImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aboutImage.setMaximumSize(learnContainer.getPreferredSize());
        aboutImage.setMinimumSize(learnContainer.getPreferredSize());
        aboutImage.setPreferredSize(new java.awt.Dimension(100, 100));
        AboutContainer.add(aboutImage, java.awt.BorderLayout.CENTER);

        aboutButton.setText("About");
        aboutButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        aboutButton.setIconTextGap(10);
        aboutButton.setMaximumSize(new java.awt.Dimension(180, 200));
        aboutButton.setMinimumSize(new java.awt.Dimension(180, 200));
        aboutButton.setPreferredSize(new java.awt.Dimension(180, 60));
        aboutButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        AboutContainer.add(aboutButton, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        rightPart.add(AboutContainer, gridBagConstraints);

        body.add(rightPart);

        mainBody.add(body);

        footer.setBackground(getBackground());
        footer.setForeground(new java.awt.Color(255, 255, 255));
        footer.setInheritsPopupMenu(true);
        footer.setMinimumSize(new java.awt.Dimension(700, 200));
        footer.setName("footer"); // NOI18N
        footer.setOpaque(false);
        footer.setPreferredSize(new java.awt.Dimension(700, 200));
        footer.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        footer.add(history, gridBagConstraints);

        mainBody.add(footer);

        mainScrollPane.setViewportView(mainBody);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Main Menu");
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        final PanelCardManager manager = CardLayoutManager.getInstance(MainDirectory.class);
        manager.showPanel(CardPanelRegistry.getInstance(GamePlay.class));
        final MultiplayerSetting mulSetting = CardPanelRegistry.getInstance(MultiplayerSetting.class);
        mulSetting.createPreviousGame();
    }//GEN-LAST:event_startButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        final PanelCardManager manager = CardLayoutManager.getInstance(MainDirectory.class);
        manager.showPanel(CardPanelRegistry.getInstance(About.class));
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void learnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnButtonActionPerformed
        final PanelCardManager manager = CardLayoutManager.getInstance(MainDirectory.class);
        manager.showPanel(CardPanelRegistry.getInstance(Tutorial.class));
    }//GEN-LAST:event_learnButtonActionPerformed

    private void multiplayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplayerButtonActionPerformed
        final PanelCardManager manager = CardLayoutManager.getInstance(MainDirectory.class);
        manager.showPanel(CardPanelRegistry.getInstance(GamePlay.class));
        final PlayerSetting playerSetting = CardPanelRegistry.getInstance(PlayerSetting.class);
        playerSetting.createNewUnplayableTable();
    }//GEN-LAST:event_multiplayerButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
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
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        MainFrame.getInstance().closeWindow();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        final MainFrame main = MainFrame.getInstance();
        if (main.isLogin()) {
            final PanelCardManager manager = CardLayoutManager.getInstance(MainDirectory.class);
            manager.showPanel(CardPanelRegistry.getInstance(Account.class));
        } else {
            final int status = new OkConfirmation(
                "Login first to access your account.", 
                "Signin Confirmation", main).getReturnStatus();
            if (status == OkConfirmation.RET_OK) {
                if (DatabaseConnection.getInstance().tryConnectionIfValid()) {
                    final ModelProfile user = SessionManager.getSessionUser();
                    if (user != null) {
                        main.dispose();
                        new MainFrame(user);
                    } else {
                        main.dispose();
                        new LoginSignUpFrame();
                    }
                } else {
                    main.showMessage(Message.MessageType.ERROR, "Cannot connect to the server.");
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                }
            }
        }
    }//GEN-LAST:event_accountButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.buttons.MainButton aboutButton;
    private javax.swing.JLabel aboutImage;
    private javax.swing.JButton accountButton;
    private javax.swing.JPanel body;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private com.dama.gui.historyPanel.History history;
    private app.buttons.MainButton learnButton;
    private javax.swing.JPanel learnContainer;
    private javax.swing.JLabel learnImage;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainBody;
    private app.buttons.MainButton multiplayerButton;
    private javax.swing.JLabel profile;
    private javax.swing.JButton quitButton;
    private javax.swing.JPanel setting;
    private app.buttons.MainButton startButton;
    // End of variables declaration//GEN-END:variables
}
