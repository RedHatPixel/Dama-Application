package component.Panel.controlPanel;

import com.dama.gui.GameInfo;
import com.dama.gui.TableManager;
import com.dama.gui.game_panel.Table;
import component.OptionPane.QuitConfirmation;
import component.Panel.CardHandlers.*;
import component.Panel.GamePlay;
import utilities.FontManager;
import utilities.FontManager.*;
import java.awt.Component;

public class PlayerSetting extends CardPanelRegistry {
    
    public PlayerSetting() {
        initComponents();

        for (Component comp : getComponents()) {
            comp.setFont(FontManager.getFont(FontName.POPPINS_SEMIBOLD, FontType.POPPINS, comp.getFont().getSize()));
        }
        
        for (Component comp : playerWins.getComponents()) {
            comp.setFont(FontManager.getFont(FontName.POPPINS_BOLD, FontType.POPPINS, comp.getFont().getSize()));
        }
        
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
        return "Player Setting";
    }
    
    public void setGameInformation() {
        title.setText(GameInfo.getBotttomPlayerName() + " vs " + GameInfo.getTopPlayerName());
        playerOneName.setText(GameInfo.getBotttomPlayerName());
        playerTwoName.setText(GameInfo.getTopPlayerName());
    }
    
    public void endGame() {
        final int status = new QuitConfirmation(
                "Are you sure you want quit the game?",
                "Quit Game", null).getReturnStatus();
        if (status == QuitConfirmation.RET_YES) {
            GameInfo.resetSetting();
            
            if (CardPanelRegistry.isInstanced(GamePlay.class)) {
                CardPanelRegistry.getInstance(GamePlay.class).setNewTable();

                if (CardPanelRegistry.isInstanced(PlayerSetting.class)) {
                    final MultiplayerSetting multiplayerSetting = CardPanelRegistry.getInstance(MultiplayerSetting.class);
                    CardLayoutManager.getInstance(SettingContainer.class).showPanel(multiplayerSetting);
                    multiplayerSetting.resetSelection();
                }
            }
        }
        System.out.println(status);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        title = new javax.swing.JLabel();
        component.ScrollPane.MainScrollPane scrollableContent = new component.ScrollPane.MainScrollPane();
        component.Button.MainButton restartButton = new component.Button.MainButton();
        component.Button.MainButton undoButton = new component.Button.MainButton();
        component.Button.MainButton backButton = new component.Button.MainButton();
        component.Button.MainButton forwardButton = new component.Button.MainButton();
        component.Button.MainButton surrenderButton = new component.Button.MainButton();
        component.Button.MainButton quitButton = new component.Button.MainButton();
        playerWins = new javax.swing.JPanel();
        playerOneName = new javax.swing.JLabel();
        playerTwoName = new javax.swing.JLabel();
        playerOneScore = new javax.swing.JLabel();
        playerTwoScore = new javax.swing.JLabel();

        setBackground(new java.awt.Color(38, 37, 34));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 0, new java.awt.Color(29, 28, 26)));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(330, 600));
        setName("Player Setting"); // NOI18N
        setPreferredSize(new java.awt.Dimension(330, 600));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Player vs Opponent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        add(title, gridBagConstraints);

        scrollableContent.setBackground(new java.awt.Color(38, 37, 34));
        scrollableContent.setBorder(null);
        scrollableContent.setMinimumSize(scrollableContent.getPreferredSize());
        scrollableContent.setOpaque(false);
        scrollableContent.setPreferredSize(new java.awt.Dimension(100, 400));
        scrollableContent.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(scrollableContent, gridBagConstraints);

        restartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/restart.png"))); // NOI18N
        restartButton.setMaximumSize(new java.awt.Dimension(50, 40));
        restartButton.setMinimumSize(new java.awt.Dimension(50, 40));
        restartButton.setPreferredSize(new java.awt.Dimension(50, 40));
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(restartButton, gridBagConstraints);

        undoButton.setText("<<");
        undoButton.setMaximumSize(new java.awt.Dimension(50, 40));
        undoButton.setMinimumSize(new java.awt.Dimension(50, 40));
        undoButton.setPreferredSize(new java.awt.Dimension(50, 40));
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(undoButton, gridBagConstraints);

        backButton.setText("<");
        backButton.setMaximumSize(new java.awt.Dimension(50, 40));
        backButton.setMinimumSize(new java.awt.Dimension(50, 40));
        backButton.setPreferredSize(new java.awt.Dimension(50, 40));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(backButton, gridBagConstraints);

        forwardButton.setText(">");
        forwardButton.setMaximumSize(new java.awt.Dimension(50, 40));
        forwardButton.setMinimumSize(new java.awt.Dimension(50, 40));
        forwardButton.setPreferredSize(new java.awt.Dimension(50, 40));
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(forwardButton, gridBagConstraints);

        surrenderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/give_up.png"))); // NOI18N
        surrenderButton.setMaximumSize(new java.awt.Dimension(50, 40));
        surrenderButton.setMinimumSize(new java.awt.Dimension(50, 40));
        surrenderButton.setPreferredSize(new java.awt.Dimension(50, 40));
        surrenderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surrenderButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(surrenderButton, gridBagConstraints);

        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/exit.png"))); // NOI18N
        quitButton.setMaximumSize(new java.awt.Dimension(50, 40));
        quitButton.setMinimumSize(new java.awt.Dimension(50, 40));
        quitButton.setPreferredSize(new java.awt.Dimension(50, 40));
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(quitButton, gridBagConstraints);

        playerWins.setForeground(new java.awt.Color(255, 255, 255));
        playerWins.setDoubleBuffered(false);
        playerWins.setFocusable(false);
        playerWins.setMinimumSize(playerWins.getPreferredSize());
        playerWins.setOpaque(false);
        playerWins.setPreferredSize(new java.awt.Dimension(100, 0));
        playerWins.setRequestFocusEnabled(false);
        playerWins.setLayout(new java.awt.GridBagLayout());

        playerOneName.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerOneName.setForeground(new java.awt.Color(255, 255, 255));
        playerOneName.setText("Player");
        playerOneName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        playerWins.add(playerOneName, gridBagConstraints);

        playerTwoName.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerTwoName.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoName.setText("Opponent");
        playerTwoName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        playerWins.add(playerTwoName, gridBagConstraints);

        playerOneScore.setBackground(new java.awt.Color(51, 51, 51));
        playerOneScore.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerOneScore.setForeground(new java.awt.Color(255, 255, 255));
        playerOneScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOneScore.setText("0");
        playerOneScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playerOneScore.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 30);
        playerWins.add(playerOneScore, gridBagConstraints);

        playerTwoScore.setBackground(new java.awt.Color(51, 51, 51));
        playerTwoScore.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        playerTwoScore.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTwoScore.setText("0");
        playerTwoScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playerTwoScore.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 30);
        playerWins.add(playerTwoScore, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 100;
        gridBagConstraints.weightx = 1.0;
        add(playerWins, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        // Restart the game
        final int status = new QuitConfirmation(
                "Create new game?",
                "Restart Confirmation", null).getReturnStatus();
        
        if (status == QuitConfirmation.RET_YES) {
            if (CardPanelRegistry.isInstanced(GamePlay.class)) {
                CardPanelRegistry.getInstance(GamePlay.class).setNewTable();
            }
        }
    }//GEN-LAST:event_restartButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // Go back from the game history
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            final Table table = CardPanelRegistry.getInstance(GamePlay.class).getTable();
            TableManager.navigateBack(table);
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        // Go forward from the game history
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            final Table table = CardPanelRegistry.getInstance(GamePlay.class).getTable();
            TableManager.navigateForward(table);
        }
    }//GEN-LAST:event_forwardButtonActionPerformed

    private void surrenderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surrenderButtonActionPerformed
        // End the game with current player giving up
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            final Table table = CardPanelRegistry.getInstance(GamePlay.class).getTable();
            
            if (!table.gameEnded()) {
                final int status = new QuitConfirmation(
                "Are you sure you want to give up?",
                "Surrender Confirmation", null).getReturnStatus();
                
                if (status == QuitConfirmation.RET_YES) {
                    TableManager.setWinner(table, true);
                }
            }
        }
    }//GEN-LAST:event_surrenderButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        // Quit the game and go back to default setting
        endGame();
    }//GEN-LAST:event_quitButtonActionPerformed

    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        // Undo a one move in the game board and goes back
        if (CardPanelRegistry.isInstanced(GamePlay.class)) {
            final Table table = CardPanelRegistry.getInstance(GamePlay.class).getTable();
            TableManager.undoMove(table);
        }
    }//GEN-LAST:event_undoButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel playerOneName;
    private javax.swing.JLabel playerOneScore;
    private javax.swing.JLabel playerTwoName;
    private javax.swing.JLabel playerTwoScore;
    private javax.swing.JPanel playerWins;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
