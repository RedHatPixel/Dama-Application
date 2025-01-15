package appGui.panels;

import appGui.frames.MainFrame;
import appGui.panels.CardHandlers.CardLayoutManager;
import appGui.panels.CardHandlers.CardPanelRegistry;
import appGui.panels.CardHandlers.FrameCardManager;
import appGui.panels.controlPanel.MultiplayerSetting;
import appGui.panels.controlPanel.PlayerSetting;
import com.dama.gameGui.GameInfo;
import com.dama.gameGui.GameInfo.GameDuration;
import static com.dama.gameGui.GameInfo.GameDuration.FIVE_MINUTES;
import utilities.CommonConstants;
import utilities.FontManager;
import utilities.FontManager.*;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.SwingUtilities;
import utilities.ImageFiles;
import utilities.ImageManager;

public final class MainMenu extends CardPanelRegistry {

    public MainMenu() {
        initComponents();
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                learnImage.setIcon(
                        ImageManager.getResizedImage(
                                ImageFiles.LEARN_DIR, 
                                (int) learnImage.getSize().getWidth(),
                                (int) (learnImage.getSize().getHeight())));
                aboutImage.setIcon(
                            ImageManager.getResizedImage(
                                    ImageFiles.ABOUT_DIR, 
                                    (int) aboutImage.getSize().getWidth(), 
                                    (int) (aboutImage.getSize().getHeight())));
            }
        });
        setButtonStyle();
        setFooterSize();
        initialize();
        validate();
        repaint();
    }
    
    @Override
    protected void configurePanel() {
        if (!CardLayoutManager.DESIGN_TIME)
            CardLayoutManager.getInstance(MainFrame.class).registerPanel(this, getName());
    }

    @Override
    public String getPanelName() {
        return "Main Menu";
    }
    
    public void updateHistory() {
        history.refreshContent();
    }
    
    public void setButtonStyle() {
        final GameDuration duration = GameInfo.getGameDuration();
        if (duration != null) {
            switch (duration) {
                case FIVE_MINUTES -> {
                    startButton.setIcon(ImageFiles.BULLET);
                }
                case TEN_MINUTES -> {
                    startButton.setIcon(ImageFiles.BOLT);
                }
                case FIFTEEN_MINUTES -> {
                    startButton.setIcon(ImageFiles.STOP_WATCH);
                }
                default -> {
                    startButton.setIcon(ImageFiles.LINK);
                }
            }
            
            final int seconds = (duration.getTime() / 1000) % 60;
            final int minutes = (duration.getTime() / 1000) / 60;
            final String format = String.format("%02d:%02d", minutes, seconds);
            if (duration == GameDuration.NO_TIMER) {
                startButton.setText("Play");
                return;
            }
            startButton.setText("Play     " + format);
        }
    }
    
    public void setFooterSize() {
        SwingUtilities.invokeLater(() -> {
            final Dimension footerMinSize = footer.getMinimumSize();
            final Dimension historySize = history.getPreferredSize();

            int padding = 0;
            final int parentHeightSize = Math.max(historySize.height, footerMinSize.height);
            if (parentHeightSize != footerMinSize.height) padding = 20;

            footer.setPreferredSize(new Dimension(footer.getWidth(), parentHeightSize + padding));
            footer.revalidate();
            footer.repaint();

            final Dimension mainBodySize = mainBody.getPreferredSize();
            final Dimension headerSize = header.getPreferredSize();
            final Dimension bodySize = body.getPreferredSize();
            final Dimension footerSize = footer.getPreferredSize(); 

            final int height = headerSize.height + bodySize.height;
            final int width = mainBodySize.width;
            mainBody.setPreferredSize(new Dimension(width, height));

            final int overallSize = height + footerSize.height;
            mainBody.setPreferredSize(new Dimension(width, overallSize));
            mainBody.revalidate();
            mainBody.repaint();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        appGui.panels.mainSubPanel.Navigator navigator = new appGui.panels.mainSubPanel.Navigator();
        appGui.scrollPanes.MainScrollPane mainScrollPane = new appGui.scrollPanes.MainScrollPane();
        mainBody = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        body = new javax.swing.JPanel();
        javax.swing.JPanel leftPart = new javax.swing.JPanel();
        startButton = new appGui.buttons.MainButton();
        multiplayerButton = new appGui.buttons.MainButton();
        javax.swing.JPanel rightPart = new javax.swing.JPanel();
        learnContainer = new javax.swing.JPanel();
        learnButton = new appGui.buttons.MainButton();
        learnImage = new javax.swing.JLabel();
        javax.swing.JPanel AboutContainer = new javax.swing.JPanel();
        aboutImage = new javax.swing.JLabel();
        aboutButton = new appGui.buttons.MainButton();
        footer = new javax.swing.JPanel();
        history = new appGui.panels.historyPanel.History();

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
        header.setLayout(new javax.swing.BoxLayout(header, javax.swing.BoxLayout.LINE_AXIS));
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
        footer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 20, 0));
        footer.add(history);

        mainBody.add(footer);

        mainScrollPane.setViewportView(mainBody);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Main Menu");
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(GamePlay.class));
        final MultiplayerSetting mulSetting = CardPanelRegistry.getInstance(MultiplayerSetting.class);
        mulSetting.createNewGame();
    }//GEN-LAST:event_startButtonActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(About.class));
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void learnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_learnButtonActionPerformed
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(Tutorial.class));
    }//GEN-LAST:event_learnButtonActionPerformed

    private void multiplayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplayerButtonActionPerformed
        final FrameCardManager mainManager = CardLayoutManager.getInstance(MainFrame.class);
        mainManager.showPanel(CardPanelRegistry.getInstance(GamePlay.class));
        final PlayerSetting playerSetting = CardPanelRegistry.getInstance(PlayerSetting.class);
        playerSetting.setNewGame();
    }//GEN-LAST:event_multiplayerButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private appGui.buttons.MainButton aboutButton;
    private javax.swing.JLabel aboutImage;
    private javax.swing.JPanel body;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private appGui.panels.historyPanel.History history;
    private appGui.buttons.MainButton learnButton;
    private javax.swing.JPanel learnContainer;
    private javax.swing.JLabel learnImage;
    private javax.swing.JPanel mainBody;
    private appGui.buttons.MainButton multiplayerButton;
    private appGui.buttons.MainButton startButton;
    // End of variables declaration//GEN-END:variables
}
