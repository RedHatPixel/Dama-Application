package appGui.panels.controlPanel;

import appGui.panels.CardHandlers.CardLayoutManager;
import appGui.panels.CardHandlers.CardPanelRegistry;
import appGui.panels.GamePlay;
import com.dama.gameGui.GameInfo;
import utilities.FontManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultiplayerSetting extends CardPanelRegistry {

    public MultiplayerSetting() {
        initComponents();
        
        title.setFont(FontManager.getFont(FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, title.getFont().getSize()));
        for (final Component comp : content.getComponents()) {
            if (comp instanceof javax.swing.JLabel) {
                javax.swing.JLabel jLabel = (javax.swing.JLabel) comp;
                jLabel.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, jLabel.getFont().getSize()));
            }
            else if (comp instanceof JRadioButton) {
                JRadioButton jRadioButton = (JRadioButton) comp;
                jRadioButton.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_BOLD, FontManager.FontType.POPPINS, jRadioButton.getFont().getSize()));
            }
            else if (comp instanceof JTextField) {
                JTextField jTextField = (JTextField) comp;
                jTextField.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_MEDIUM, FontManager.FontType.POPPINS, jTextField.getFont().getSize()));
            }
            else if (comp instanceof javax.swing.JCheckBox) {
                javax.swing.JCheckBox jCheckBox = (javax.swing.JCheckBox) comp;
                jCheckBox.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_SEMIBOLD, FontManager.FontType.POPPINS, jCheckBox.getFont().getSize()));
            }
        }
        
        rapidRadio.addItemListener(e -> updateDurationAppearance(rapidRadio, e));
        bulletRadio.addItemListener(e -> updateDurationAppearance(bulletRadio, e));
        blitzRadio.addItemListener(e -> updateDurationAppearance(blitzRadio, e));
        noTimerRadio.addItemListener(e -> updateDurationAppearance(noTimerRadio, e));
        
        whiteRadio.addItemListener(e -> updateAllianceAppearance(whiteRadio, e));
        randomRadio.addItemListener(e -> updateAllianceAppearance(randomRadio, e));
        blackRadio.addItemListener(e -> updateAllianceAppearance(blackRadio, e));
        
        bulletRadio.setSelected(true);
        whiteRadio.setSelected(true);
        
        initialize();
        validate();
        repaint();
    }
    
    @Override
    protected void configurePanel() {
        if (!CardLayoutManager.DESIGN_TIME)
            CardLayoutManager.getInstance(SettingContainer.class).registerPanel(this, getName());
    }

    @Override
    public String getPanelName() {
        return "Multiplayer Setting";
    }
    
    private void setGameInformation() {
        final boolean playerOneError = limitStringCapacity(playerOneName);
        final boolean playerTwoError = limitStringCapacity(playerTwoName);
        if (playerOneError || playerTwoError) return;

        GameInfo.resetSetting();
        final String selectedColor = SelectedColor.getSelection() != null ?
        SelectedColor.getSelection().getActionCommand() :
        "No Alliance selected";
        final String selectedDuration = SelectedDuration.getSelection() != null
        ? SelectedDuration.getSelection().getActionCommand()
        : "No duration selected";
        switch (selectedColor) {
            case "Black" ->
                GameInfo.setDirection(GameInfo.GameSwitch.FLIPPED);
            case "Random" ->
                GameInfo.createRandomAlliance();
            default ->
                GameInfo.setDirection(GameInfo.GameSwitch.NORMAL);
        }
        switch (selectedDuration) {
            case "Bullet" ->
                GameInfo.setGameDuration(GameInfo.GameDuration.FIVE_MINUTES);
            case "Blitz" ->
                GameInfo.setGameDuration(GameInfo.GameDuration.TEN_MINUTES);
            case "Rapid" ->
                GameInfo.setGameDuration(GameInfo.GameDuration.FIFTEEN_MINUTES);
            default ->
                GameInfo.setGameDuration(GameInfo.GameDuration.NO_TIMER);
        }
        
        GameInfo.setPlayerNames(playerTwoName.getText(), playerOneName.getText());
        GameInfo.allowShowingCapturables(checkCaptures.isSelected());
        GameInfo.allowChangeTurn(checkChangeTurn.isSelected());
        GameInfo.allowShowingLatestMove(checkLatestMove.isSelected());
        GameInfo.allowShowingMovablePiece(checkMovePiece.isSelected());
        GameInfo.allowShowingMoves(checkMove.isSelected());
    }

    private void updateAllianceAppearance(final JRadioButton button, final ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            button.setBackground(new Color(102,102,102));
        } else {
            button.setBackground(new Color(38,37,34));
        }
    }
    
    private void updateDurationAppearance(final JRadioButton button, final ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(102,102,102)),
                    BorderFactory.createEmptyBorder(0, 24, 0, 0)));
            button.setBackground(new Color(102,102,102));
        } else {
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(102,102,102)),
                    BorderFactory.createEmptyBorder(0, 14, 0, 0)));
            button.setBackground(new Color(38,37,34));
        }
    }
    
    private boolean limitStringCapacity(final JTextField textField) {
        if (textField.getText().length() > 12) {
            textField.setText("");
            return true;
        }
        return false;
    }
    
    public void resetSelection() {
        playerOneName.setText("");
        playerTwoName.setText("");
        SelectedDuration.clearSelection();
        bulletRadio.setSelected(true);
        SelectedColor.clearSelection();
        whiteRadio.setSelected(true);
        checkCaptures.setSelected(true);
        checkChangeTurn.setSelected(false);
        checkLatestMove.setSelected(true);
        checkMove.setSelected(true);
        checkMovePiece.setSelected(false);
    }
    
    public void createNewGame() {
        GameInfo.setIfGameStart(true);
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            CardPanelRegistry.getInstance(GamePlay.class).setNewTable();
            
            if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
                final PlayerSetting playerSetting = CardPanelRegistry.getInstance(PlayerSetting.class);
                CardLayoutManager.getInstance(SettingContainer.class).showPanel(playerSetting);
                playerSetting.setGameInformation();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        SelectedDuration = new javax.swing.ButtonGroup();
        SelectedColor = new javax.swing.ButtonGroup();
        title = new javax.swing.JLabel();
        appGui.scrollPanes.MainScrollPane scrollableContent = new appGui.scrollPanes.MainScrollPane();
        content = new javax.swing.JPanel();
        playerLabelTwo = new javax.swing.JLabel();
        playerTwoName = new javax.swing.JTextField();
        playerLabelOne = new javax.swing.JLabel();
        playerOneName = new javax.swing.JTextField();
        javax.swing.JPanel colorContainer = new javax.swing.JPanel();
        whiteRadio = new javax.swing.JRadioButton();
        randomRadio = new javax.swing.JRadioButton();
        blackRadio = new javax.swing.JRadioButton();
        bulletRadio = new javax.swing.JRadioButton();
        blitzRadio = new javax.swing.JRadioButton();
        rapidRadio = new javax.swing.JRadioButton();
        noTimerRadio = new javax.swing.JRadioButton();
        settingTitle = new javax.swing.JLabel();
        checkMove = new javax.swing.JCheckBox();
        checkChangeTurn = new javax.swing.JCheckBox();
        checkCaptures = new javax.swing.JCheckBox();
        checkMovePiece = new javax.swing.JCheckBox();
        checkLatestMove = new javax.swing.JCheckBox();
        startButton = new appGui.buttons.MainButton();
        resetButton = new appGui.buttons.MainButton();

        setBackground(new java.awt.Color(38, 37, 34));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 0, new java.awt.Color(29, 28, 26)));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(330, 600));
        setName("Multiplayer Setting"); // NOI18N
        setNextFocusableComponent(playerTwoName);
        setPreferredSize(new java.awt.Dimension(330, 600));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("CREATE A GAME");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(title, gridBagConstraints);

        scrollableContent.setBackground(new java.awt.Color(38, 37, 34));
        scrollableContent.setOpaque(false);
        scrollableContent.setPreferredSize(new java.awt.Dimension(100, 330));
        scrollableContent.setRequestFocusEnabled(false);

        content.setBackground(new java.awt.Color(38, 37, 34));
        content.setMinimumSize(new java.awt.Dimension(100, 100));
        content.setRequestFocusEnabled(false);
        content.setLayout(new java.awt.GridBagLayout());

        playerLabelTwo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerLabelTwo.setForeground(new java.awt.Color(255, 255, 255));
        playerLabelTwo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        playerLabelTwo.setText("OPPONENT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(playerLabelTwo, gridBagConstraints);

        playerTwoName.setBackground(new java.awt.Color(38, 37, 34));
        playerTwoName.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        playerTwoName.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        playerTwoName.setToolTipText("Enter the player two name");
        playerTwoName.setActionCommand("<Not Set>");
        playerTwoName.setAutoscrolls(false);
        playerTwoName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        playerTwoName.setCaretColor(new java.awt.Color(102, 102, 102));
        playerTwoName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        playerTwoName.setOpaque(true);
        playerTwoName.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        playerTwoName.setSelectionColor(new java.awt.Color(48, 46, 43));
        playerTwoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerTwoNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(playerTwoName, gridBagConstraints);

        playerLabelOne.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerLabelOne.setForeground(new java.awt.Color(255, 255, 255));
        playerLabelOne.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        playerLabelOne.setText("PLAYER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        content.add(playerLabelOne, gridBagConstraints);

        playerOneName.setBackground(new java.awt.Color(38, 37, 34));
        playerOneName.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        playerOneName.setForeground(new java.awt.Color(255, 255, 255));
        playerOneName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        playerOneName.setToolTipText("Enter player one name");
        playerOneName.setActionCommand("<Not Set>");
        playerOneName.setAutoscrolls(false);
        playerOneName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        playerOneName.setCaretColor(new java.awt.Color(102, 102, 102));
        playerOneName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        playerOneName.setOpaque(true);
        playerOneName.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        playerOneName.setSelectionColor(new java.awt.Color(48, 46, 43));
        playerOneName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerOneNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(playerOneName, gridBagConstraints);

        colorContainer.setBackground(new java.awt.Color(102, 102, 102));
        colorContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        colorContainer.setMinimumSize(new java.awt.Dimension(199, 50));
        colorContainer.setPreferredSize(new java.awt.Dimension(199, 50));
        colorContainer.setLayout(new java.awt.GridLayout(1, 0));

        whiteRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedColor.add(whiteRadio);
        whiteRadio.setForeground(new java.awt.Color(255, 255, 255));
        whiteRadio.setActionCommand("White");
        whiteRadio.setBorder(null);
        whiteRadio.setFocusPainted(false);
        whiteRadio.setFocusable(false);
        whiteRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteRadio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        whiteRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/WhiteSelection.png"))); // NOI18N
        whiteRadio.setMaximumSize(new java.awt.Dimension(65, 70));
        whiteRadio.setMinimumSize(new java.awt.Dimension(65, 70));
        whiteRadio.setOpaque(true);
        whiteRadio.setPreferredSize(new java.awt.Dimension(65, 70));
        whiteRadio.setRequestFocusEnabled(false);
        whiteRadio.setRolloverEnabled(false);
        colorContainer.add(whiteRadio);

        randomRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedColor.add(randomRadio);
        randomRadio.setForeground(new java.awt.Color(255, 255, 255));
        randomRadio.setActionCommand("Random");
        randomRadio.setBorder(null);
        randomRadio.setFocusPainted(false);
        randomRadio.setFocusable(false);
        randomRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        randomRadio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        randomRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/RandomSelection.png"))); // NOI18N
        randomRadio.setMaximumSize(new java.awt.Dimension(65, 70));
        randomRadio.setMinimumSize(new java.awt.Dimension(65, 70));
        randomRadio.setOpaque(true);
        randomRadio.setPreferredSize(new java.awt.Dimension(65, 70));
        randomRadio.setRequestFocusEnabled(false);
        randomRadio.setRolloverEnabled(false);
        colorContainer.add(randomRadio);

        blackRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedColor.add(blackRadio);
        blackRadio.setForeground(new java.awt.Color(255, 255, 255));
        blackRadio.setActionCommand("Black");
        blackRadio.setBorder(null);
        blackRadio.setFocusPainted(false);
        blackRadio.setFocusable(false);
        blackRadio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackRadio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        blackRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/BlackSelection.png"))); // NOI18N
        blackRadio.setMaximumSize(new java.awt.Dimension(65, 70));
        blackRadio.setMinimumSize(new java.awt.Dimension(65, 70));
        blackRadio.setOpaque(true);
        blackRadio.setPreferredSize(new java.awt.Dimension(65, 70));
        blackRadio.setRequestFocusEnabled(false);
        blackRadio.setRolloverEnabled(false);
        colorContainer.add(blackRadio);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        content.add(colorContainer, gridBagConstraints);

        bulletRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedDuration.add(bulletRadio);
        bulletRadio.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bulletRadio.setForeground(new java.awt.Color(255, 255, 255));
        bulletRadio.setText("Bullet      5 min");
        bulletRadio.setToolTipText("");
        bulletRadio.setActionCommand("Bullet");
        bulletRadio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(102, 102, 102)), javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0)));
        bulletRadio.setBorderPainted(true);
        bulletRadio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bulletRadio.setFocusPainted(false);
        bulletRadio.setFocusable(false);
        bulletRadio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bulletRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/bullet.png"))); // NOI18N
        bulletRadio.setIconTextGap(10);
        bulletRadio.setRequestFocusEnabled(false);
        bulletRadio.setRolloverEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        content.add(bulletRadio, gridBagConstraints);

        blitzRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedDuration.add(blitzRadio);
        blitzRadio.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        blitzRadio.setForeground(new java.awt.Color(255, 255, 255));
        blitzRadio.setText("Blitz       10 min");
        blitzRadio.setActionCommand("Blitz");
        blitzRadio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(102, 102, 102)), javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0)));
        blitzRadio.setBorderPainted(true);
        blitzRadio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        blitzRadio.setFocusPainted(false);
        blitzRadio.setFocusable(false);
        blitzRadio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        blitzRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/bolt.png"))); // NOI18N
        blitzRadio.setIconTextGap(10);
        blitzRadio.setRequestFocusEnabled(false);
        blitzRadio.setRolloverEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(blitzRadio, gridBagConstraints);

        rapidRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedDuration.add(rapidRadio);
        rapidRadio.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        rapidRadio.setForeground(new java.awt.Color(255, 255, 255));
        rapidRadio.setText("Rapid     15 min");
        rapidRadio.setActionCommand("Rapid");
        rapidRadio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(102, 102, 102)), javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0)));
        rapidRadio.setBorderPainted(true);
        rapidRadio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rapidRadio.setFocusPainted(false);
        rapidRadio.setFocusable(false);
        rapidRadio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rapidRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/stopwatch.png"))); // NOI18N
        rapidRadio.setIconTextGap(10);
        rapidRadio.setRequestFocusEnabled(false);
        rapidRadio.setRolloverEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(rapidRadio, gridBagConstraints);

        noTimerRadio.setBackground(new java.awt.Color(38, 37, 34));
        SelectedDuration.add(noTimerRadio);
        noTimerRadio.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        noTimerRadio.setForeground(new java.awt.Color(255, 255, 255));
        noTimerRadio.setText("No Timer");
        noTimerRadio.setActionCommand("NoTimer");
        noTimerRadio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(102, 102, 102)), javax.swing.BorderFactory.createEmptyBorder(0, 14, 0, 0)));
        noTimerRadio.setBorderPainted(true);
        noTimerRadio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        noTimerRadio.setFocusPainted(false);
        noTimerRadio.setFocusable(false);
        noTimerRadio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        noTimerRadio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/link.png"))); // NOI18N
        noTimerRadio.setIconTextGap(10);
        noTimerRadio.setRequestFocusEnabled(false);
        noTimerRadio.setRolloverEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(noTimerRadio, gridBagConstraints);

        settingTitle.setBackground(new java.awt.Color(102, 102, 102));
        settingTitle.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        settingTitle.setForeground(new java.awt.Color(255, 255, 255));
        settingTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingTitle.setText("Game Setting");
        settingTitle.setMaximumSize(new java.awt.Dimension(126, 35));
        settingTitle.setMinimumSize(new java.awt.Dimension(126, 35));
        settingTitle.setOpaque(true);
        settingTitle.setPreferredSize(new java.awt.Dimension(126, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        content.add(settingTitle, gridBagConstraints);

        checkMove.setBackground(new java.awt.Color(38, 37, 34));
        checkMove.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        checkMove.setForeground(new java.awt.Color(255, 255, 255));
        checkMove.setSelected(true);
        checkMove.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        checkMove.setBorderPainted(true);
        checkMove.setFocusPainted(false);
        checkMove.setFocusable(false);
        checkMove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkMove.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkMove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        checkMove.setIconTextGap(28);
        checkMove.setLabel("Show Move");
        checkMove.setMaximumSize(new java.awt.Dimension(85, 35));
        checkMove.setMinimumSize(new java.awt.Dimension(85, 35));
        checkMove.setOpaque(true);
        checkMove.setPreferredSize(new java.awt.Dimension(85, 37));
        checkMove.setRequestFocusEnabled(false);
        checkMove.setRolloverEnabled(false);
        checkMove.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(checkMove, gridBagConstraints);

        checkChangeTurn.setBackground(new java.awt.Color(38, 37, 34));
        checkChangeTurn.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        checkChangeTurn.setForeground(new java.awt.Color(255, 255, 255));
        checkChangeTurn.setText("Changing Board");
        checkChangeTurn.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        checkChangeTurn.setBorderPainted(true);
        checkChangeTurn.setFocusPainted(false);
        checkChangeTurn.setFocusable(false);
        checkChangeTurn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkChangeTurn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkChangeTurn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        checkChangeTurn.setIconTextGap(28);
        checkChangeTurn.setMaximumSize(new java.awt.Dimension(85, 35));
        checkChangeTurn.setMinimumSize(new java.awt.Dimension(85, 35));
        checkChangeTurn.setOpaque(true);
        checkChangeTurn.setPreferredSize(new java.awt.Dimension(85, 37));
        checkChangeTurn.setRequestFocusEnabled(false);
        checkChangeTurn.setRolloverEnabled(false);
        checkChangeTurn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(checkChangeTurn, gridBagConstraints);

        checkCaptures.setBackground(new java.awt.Color(38, 37, 34));
        checkCaptures.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        checkCaptures.setForeground(new java.awt.Color(255, 255, 255));
        checkCaptures.setSelected(true);
        checkCaptures.setText("Draw Captures");
        checkCaptures.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        checkCaptures.setBorderPainted(true);
        checkCaptures.setFocusPainted(false);
        checkCaptures.setFocusable(false);
        checkCaptures.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkCaptures.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkCaptures.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        checkCaptures.setIconTextGap(28);
        checkCaptures.setMaximumSize(new java.awt.Dimension(85, 35));
        checkCaptures.setMinimumSize(new java.awt.Dimension(85, 35));
        checkCaptures.setOpaque(true);
        checkCaptures.setPreferredSize(new java.awt.Dimension(85, 37));
        checkCaptures.setRequestFocusEnabled(false);
        checkCaptures.setRolloverEnabled(false);
        checkCaptures.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(checkCaptures, gridBagConstraints);

        checkMovePiece.setBackground(new java.awt.Color(38, 37, 34));
        checkMovePiece.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        checkMovePiece.setForeground(new java.awt.Color(255, 255, 255));
        checkMovePiece.setText("Show Movable Piece");
        checkMovePiece.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        checkMovePiece.setBorderPainted(true);
        checkMovePiece.setFocusPainted(false);
        checkMovePiece.setFocusable(false);
        checkMovePiece.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkMovePiece.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkMovePiece.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        checkMovePiece.setIconTextGap(28);
        checkMovePiece.setMaximumSize(new java.awt.Dimension(85, 35));
        checkMovePiece.setMinimumSize(new java.awt.Dimension(85, 35));
        checkMovePiece.setOpaque(true);
        checkMovePiece.setPreferredSize(new java.awt.Dimension(85, 37));
        checkMovePiece.setRequestFocusEnabled(false);
        checkMovePiece.setRolloverEnabled(false);
        checkMovePiece.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(checkMovePiece, gridBagConstraints);

        checkLatestMove.setBackground(new java.awt.Color(38, 37, 34));
        checkLatestMove.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        checkLatestMove.setForeground(new java.awt.Color(255, 255, 255));
        checkLatestMove.setSelected(true);
        checkLatestMove.setText("Display Latest Move");
        checkLatestMove.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 18, 0, 0));
        checkLatestMove.setBorderPainted(true);
        checkLatestMove.setFocusPainted(false);
        checkLatestMove.setFocusable(false);
        checkLatestMove.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        checkLatestMove.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        checkLatestMove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/preventSetting.png"))); // NOI18N
        checkLatestMove.setIconTextGap(28);
        checkLatestMove.setMaximumSize(new java.awt.Dimension(85, 35));
        checkLatestMove.setMinimumSize(new java.awt.Dimension(85, 35));
        checkLatestMove.setOpaque(true);
        checkLatestMove.setPreferredSize(new java.awt.Dimension(85, 37));
        checkLatestMove.setRequestFocusEnabled(false);
        checkLatestMove.setRolloverEnabled(false);
        checkLatestMove.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/checkSetting.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        content.add(checkLatestMove, gridBagConstraints);

        scrollableContent.setViewportView(content);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(scrollableContent, gridBagConstraints);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        add(startButton, gridBagConstraints);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 20);
        add(resetButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void playerTwoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerTwoNameActionPerformed
        if (limitStringCapacity(playerTwoName))
            playerTwoName.requestFocus();
        else if (playerOneName.getText().isBlank())
            playerOneName.requestFocus();
        else if (!playerTwoName.getText().isBlank()) {
            startButtonActionPerformed(evt);
            this.getRootPane().requestFocus();
        }
    }//GEN-LAST:event_playerTwoNameActionPerformed

    private void playerOneNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerOneNameActionPerformed
        if (limitStringCapacity(playerOneName))
            playerOneName.requestFocus();
        else if (playerTwoName.getText().isBlank())
            playerTwoName.requestFocus();
        else if (!playerOneName.getText().isBlank()) {
            startButtonActionPerformed(evt);
            this.getRootPane().requestFocus();
        }
    }//GEN-LAST:event_playerOneNameActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        setGameInformation();
        createNewGame();
    }//GEN-LAST:event_startButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        GameInfo.resetSetting();
        resetSelection();
    }//GEN-LAST:event_resetButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup SelectedColor;
    private javax.swing.ButtonGroup SelectedDuration;
    private javax.swing.JRadioButton blackRadio;
    private javax.swing.JRadioButton blitzRadio;
    private javax.swing.JRadioButton bulletRadio;
    private javax.swing.JCheckBox checkCaptures;
    private javax.swing.JCheckBox checkChangeTurn;
    private javax.swing.JCheckBox checkLatestMove;
    private javax.swing.JCheckBox checkMove;
    private javax.swing.JCheckBox checkMovePiece;
    private javax.swing.JPanel content;
    private javax.swing.JRadioButton noTimerRadio;
    private javax.swing.JLabel playerLabelOne;
    private javax.swing.JLabel playerLabelTwo;
    private javax.swing.JTextField playerOneName;
    private javax.swing.JTextField playerTwoName;
    private javax.swing.JRadioButton randomRadio;
    private javax.swing.JRadioButton rapidRadio;
    private appGui.buttons.MainButton resetButton;
    private javax.swing.JLabel settingTitle;
    private appGui.buttons.MainButton startButton;
    private javax.swing.JLabel title;
    private javax.swing.JRadioButton whiteRadio;
    // End of variables declaration//GEN-END:variables

}
