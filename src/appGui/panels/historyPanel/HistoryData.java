package appGui.panels.historyPanel;

import com.dama.engine.history.GameData;
import com.dama.gameGui.GameInfo.GameDuration;
import com.dama.gameGui.game_panel.PlayerPanel;
import utilities.FontManager;
import utilities.FontManager.*;
import javax.swing.JLabel;
import java.awt.Component;
import utilities.ImageFiles;

public class HistoryData extends javax.swing.JPanel {
    
    public HistoryData(final GameData gameData) {
        initComponents();
        
        for (Component comp : this.getComponents()) {
            try {
                JLabel label = (JLabel) comp;
                label.setFont(
                        FontManager.getFont(FontName.POPPINS_SEMIBOLD, FontType.POPPINS, label.getFont().getSize()));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("The comp cannot be cast as JLabel");
            }
        }
        
        final GameDuration duration = gameData.getDuration();
        if (null != duration) switch (duration) {
            case FIVE_MINUTES -> {
                logo.setIcon(ImageFiles.BULLET);
            }
            case TEN_MINUTES -> {
                logo.setIcon(ImageFiles.BOLT);
            }
            case FIFTEEN_MINUTES -> {
                logo.setIcon(ImageFiles.STOP_WATCH);
            }
            case NO_TIMER -> {
                logo.setIcon(ImageFiles.LINK);
            }
            default -> {
                logo.setIcon(ImageFiles.BOLT);
            }
        }
        
        playerOneName.setText(gameData.getPlayerPanel(true).getPlayerName());
        playerTwoName.setText(gameData.getPlayerPanel(false).getPlayerName());
        playerOneScore.setText("" + gameData.getPlayerScore(true));
        playerTwoScore.setText("" + gameData.getPlayerScore(false));
        
        final PlayerPanel playerPanel = gameData.getStatus().getWinner(
            gameData.getPlayerPanel(true), 
            gameData.getPlayerPanel(false));
        if (gameData.getTable().getBottomPlayerPanel().getPlayerName().equals(playerPanel.getPlayerName())) {
            this.result.setIcon(ImageFiles.WIN_ICON);
        } else {
            this.result.setIcon(ImageFiles.LOSE_ICON);
        }

        this.date.setText(gameData.getDate());
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        logo = new javax.swing.JLabel();
        playerOneName = new javax.swing.JLabel();
        playerTwoName = new javax.swing.JLabel();
        playerOneScore = new javax.swing.JLabel();
        playerTwoScore = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setBackground(new java.awt.Color(38, 37, 34));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(600, 50));
        setMinimumSize(new java.awt.Dimension(600, 50));
        setPreferredSize(new java.awt.Dimension(600, 50));
        setLayout(new java.awt.GridBagLayout());

        logo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/game_icon/stopwatch.png"))); // NOI18N
        logo.setToolTipText(null);
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logo.setMaximumSize(new java.awt.Dimension(40, 45));
        logo.setMinimumSize(new java.awt.Dimension(40, 45));
        logo.setPreferredSize(new java.awt.Dimension(40, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        add(logo, gridBagConstraints);

        playerOneName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        playerOneName.setForeground(new java.awt.Color(255, 255, 255));
        playerOneName.setText("playerName");
        playerOneName.setToolTipText(null);
        playerOneName.setMaximumSize(new java.awt.Dimension(150, 18));
        playerOneName.setMinimumSize(new java.awt.Dimension(150, 18));
        playerOneName.setPreferredSize(new java.awt.Dimension(150, 18));
        playerOneName.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        add(playerOneName, gridBagConstraints);

        playerTwoName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        playerTwoName.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoName.setText("playerName");
        playerTwoName.setToolTipText(null);
        playerTwoName.setMaximumSize(new java.awt.Dimension(150, 18));
        playerTwoName.setMinimumSize(new java.awt.Dimension(150, 18));
        playerTwoName.setPreferredSize(new java.awt.Dimension(150, 18));
        playerTwoName.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(playerTwoName, gridBagConstraints);

        playerOneScore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        playerOneScore.setForeground(new java.awt.Color(255, 255, 255));
        playerOneScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOneScore.setText("0");
        playerOneScore.setToolTipText(null);
        playerOneScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playerOneScore.setMaximumSize(new java.awt.Dimension(23, 18));
        playerOneScore.setMinimumSize(new java.awt.Dimension(23, 18));
        playerOneScore.setPreferredSize(new java.awt.Dimension(23, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        add(playerOneScore, gridBagConstraints);

        playerTwoScore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        playerTwoScore.setForeground(new java.awt.Color(255, 255, 255));
        playerTwoScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTwoScore.setText("0");
        playerTwoScore.setToolTipText(null);
        playerTwoScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playerTwoScore.setMaximumSize(new java.awt.Dimension(23, 18));
        playerTwoScore.setMinimumSize(new java.awt.Dimension(23, 18));
        playerTwoScore.setPreferredSize(new java.awt.Dimension(23, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        add(playerTwoScore, gridBagConstraints);

        result.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        result.setForeground(new java.awt.Color(255, 255, 255));
        result.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        result.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/result_icon/winIcon.png"))); // NOI18N
        result.setToolTipText(null);
        result.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        result.setMaximumSize(new java.awt.Dimension(40, 45));
        result.setMinimumSize(new java.awt.Dimension(40, 45));
        result.setPreferredSize(new java.awt.Dimension(40, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        add(result, gridBagConstraints);

        date.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("Date");
        date.setToolTipText(null);
        date.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        date.setMaximumSize(new java.awt.Dimension(90, 45));
        date.setMinimumSize(new java.awt.Dimension(90, 45));
        date.setPreferredSize(new java.awt.Dimension(90, 45));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 5);
        add(date, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel playerOneName;
    private javax.swing.JLabel playerOneScore;
    private javax.swing.JLabel playerTwoName;
    private javax.swing.JLabel playerTwoScore;
    private javax.swing.JLabel result;
    // End of variables declaration//GEN-END:variables
}
