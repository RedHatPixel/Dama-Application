package component.Panel;

import utilities.CommonConstants;
import utilities.Directory;
import utilities.FontManager;
import utilities.FontManager.*;
import utilities.Direction;
import java.awt.Dimension;

public class MainMenu extends javax.swing.JPanel implements Direction {

    public MainMenu() {
        initComponents();
        
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setFooterSize();
        setDirectName();
        
        userName.setFont(FontManager.getFont(FontName.POPPINS_BLACK, FontType.POPPINS, 18));
        
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void setDirectName() {
        this.setName(Directory.Panel.TUTORIAL.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        component.Panel.subPanel.Navigator navigator = new component.Panel.subPanel.Navigator();
        component.ScrollPane.MainScrollPane mainScrollPane = new component.ScrollPane.MainScrollPane();
        mainBody = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        javax.swing.JPanel PlayerProfile = new javax.swing.JPanel();
        userIcon = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        userFlag = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        javax.swing.JPanel leftPart = new javax.swing.JPanel();
        startButton = new component.Button.MainButton();
        multiplayerButton = new component.Button.MainButton();
        botButton = new component.Button.MainButton();
        rightPart = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        history = new component.Panel.subPanel.History();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("MainMenu"); // NOI18N
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

        PlayerProfile.setBackground(getBackground());
        PlayerProfile.setForeground(new java.awt.Color(255, 255, 255));
        PlayerProfile.setMaximumSize(new java.awt.Dimension(230, 100));
        PlayerProfile.setMinimumSize(new java.awt.Dimension(230, 100));
        PlayerProfile.setName(""); // NOI18N
        PlayerProfile.setOpaque(false);
        PlayerProfile.setPreferredSize(new java.awt.Dimension(230, 100));
        PlayerProfile.setRequestFocusEnabled(false);
        PlayerProfile.setLayout(new java.awt.GridBagLayout());

        userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/userIcon.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        PlayerProfile.add(userIcon, gridBagConstraints);

        userName.setBackground(getBackground());
        userName.setFont(new java.awt.Font("Segoe UI Black", 0, 20)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setText("guest");
        PlayerProfile.add(userName, new java.awt.GridBagConstraints());

        userFlag.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/flags/philippines.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        PlayerProfile.add(userFlag, gridBagConstraints);

        header.add(PlayerProfile);

        mainBody.add(header);

        body.setBackground(getBackground());
        body.setForeground(new java.awt.Color(255, 255, 255));
        body.setMaximumSize(new java.awt.Dimension(32767, 250));
        body.setMinimumSize(new java.awt.Dimension(700, 250));
        body.setName("body"); // NOI18N
        body.setOpaque(false);
        body.setPreferredSize(new java.awt.Dimension(700, 250));
        body.setLayout(new java.awt.BorderLayout());

        leftPart.setBackground(getBackground());
        leftPart.setForeground(new java.awt.Color(255, 255, 255));
        leftPart.setMaximumSize(new java.awt.Dimension(32767, 250));
        leftPart.setMinimumSize(new java.awt.Dimension(220, 250));
        leftPart.setName(""); // NOI18N
        leftPart.setOpaque(false);
        leftPart.setPreferredSize(new java.awt.Dimension(220, 250));
        leftPart.setLayout(new java.awt.GridBagLayout());

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/bolt.png"))); // NOI18N
        startButton.setText("Play");
        startButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        startButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        startButton.setIconTextGap(10);
        startButton.setMaximumSize(new java.awt.Dimension(150, 50));
        startButton.setMinimumSize(new java.awt.Dimension(150, 50));
        startButton.setPreferredSize(new java.awt.Dimension(170, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        leftPart.add(startButton, gridBagConstraints);

        multiplayerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/handshake.png"))); // NOI18N
        multiplayerButton.setText("Multiplayer");
        multiplayerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        multiplayerButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        multiplayerButton.setIconTextGap(10);
        multiplayerButton.setMaximumSize(new java.awt.Dimension(150, 50));
        multiplayerButton.setMinimumSize(new java.awt.Dimension(150, 50));
        multiplayerButton.setPreferredSize(new java.awt.Dimension(170, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        leftPart.add(multiplayerButton, gridBagConstraints);

        botButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/user-robot.png"))); // NOI18N
        botButton.setText("Play Bots");
        botButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        botButton.setIconTextGap(10);
        botButton.setMaximumSize(new java.awt.Dimension(150, 50));
        botButton.setMinimumSize(new java.awt.Dimension(150, 50));
        botButton.setPreferredSize(new java.awt.Dimension(170, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 23, 0);
        leftPart.add(botButton, gridBagConstraints);

        body.add(leftPart, java.awt.BorderLayout.WEST);

        rightPart.setBackground(getBackground());
        rightPart.setForeground(new java.awt.Color(255, 255, 255));
        rightPart.setMaximumSize(new java.awt.Dimension(32767, 250));
        rightPart.setMinimumSize(rightPart.getPreferredSize());
        rightPart.setName(""); // NOI18N
        rightPart.setOpaque(false);
        rightPart.setPreferredSize(new java.awt.Dimension(300, 250));
        rightPart.setLayout(new java.awt.BorderLayout());
        body.add(rightPart, java.awt.BorderLayout.CENTER);

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

        getAccessibleContext().setAccessibleName("MainMenu");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private component.Button.MainButton botButton;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private component.Panel.subPanel.History history;
    private javax.swing.JPanel mainBody;
    private component.Button.MainButton multiplayerButton;
    private javax.swing.JPanel rightPart;
    private component.Button.MainButton startButton;
    private javax.swing.JLabel userFlag;
    private javax.swing.JLabel userIcon;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables

    private void setFooterSize() {
        Dimension footerMinSize = footer.getMinimumSize();
        Dimension historySize = history.getPreferredSize();

        // Reset footer size
        int padding = 0;
        int parentHeightSize = Math.max(historySize.height, footerMinSize.height);
        if (parentHeightSize != footerMinSize.height) padding = 20;
        
        footer.setPreferredSize(new Dimension(footer.getWidth(), parentHeightSize + padding));

        footer.revalidate();
        footer.repaint();
        
        Dimension mainBodySize = mainBody.getPreferredSize();
        Dimension headerSize = header.getPreferredSize();
        Dimension bodySize = body.getPreferredSize();
        Dimension footerSize = footer.getPreferredSize(); 
        
        // Reset mainBody size without Footer
        int height = headerSize.height + bodySize.height;
        int width = mainBodySize.width;
        mainBody.setPreferredSize(new Dimension(width, height));
        
        // Set the whole Size
        int overallSize = height + footerSize.height;
        mainBody.setPreferredSize(new Dimension(width, overallSize));
        
        mainBody.revalidate();
        mainBody.repaint();
    }
}
