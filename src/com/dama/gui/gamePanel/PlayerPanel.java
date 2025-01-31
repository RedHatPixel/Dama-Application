package com.dama.gui.gamePanel;

import com.dama.gui._configurations.player.PlayerInfo;
import com.dama.gui._configurations.player.PlayerTimer;
import com.dama.engine.players.Player;
import com.dama.gui._controls.TableManager;
import utilities.FontManager;
import utilities.FontManager.*;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class PlayerPanel extends JPanel implements PlayerTimer.TimerListener {
   
    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 50);
    
    // Define Variables
    private final TablePanel tablePanel;
    private JLabel nameLabel;
    private JLabel timerLabel;
    
    private final PlayerTimer timer;
    private final PlayerInfo info;
    
    PlayerPanel(final TablePanel table, final Player player, final String name, final int score) {
        this.tablePanel = table;
        this.timer = new PlayerTimer();
        this.info = new PlayerInfo(player, name, score);
        this.timer.setTimerListener((PlayerTimer.TimerListener) this);
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setForeground(Color.WHITE);
        setOpaque(false);
        setFocusable(false);
        setVisible(true);
        setPreferredSize(PREFERRED_SIZE);
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        setName("Player");
        setRequestFocusEnabled(false);
        
        nameLabel = new JLabel(info.getName());
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setOpaque(false);
        nameLabel.setPreferredSize(new Dimension(170, 50));
        nameLabel.setMaximumSize(nameLabel.getPreferredSize());
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        nameLabel.setFont(FontManager.getFont(
                FontName.POPPINS_SEMIBOLD, FontType.POPPINS, 16));
        
        timerLabel = new JLabel(timer.getTimerToString());
        timerLabel.setForeground(new Color(99, 98, 95));
        timerLabel.setBackground(new Color(152, 151, 149));
        timerLabel.setHorizontalAlignment(JLabel.RIGHT);
        timerLabel.setVerticalAlignment(JLabel.CENTER);
        timerLabel.setOpaque(true);
        timerLabel.setPreferredSize(new Dimension(100, 50));
        timerLabel.setMaximumSize(timerLabel.getPreferredSize());
        timerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        timerLabel.setFont(FontManager.getFont(
                FontName.POPPINS_MEDIUM, FontType.POPPINS, 22));
        
        final JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false);
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.WEST);
        add(timerPanel, BorderLayout.EAST);
    }
    
    /**
     * Get the player information in the playerPanel class
     * @return PlayerInfo
     */
    public PlayerInfo getPlayerInfo() {
        return this.info;
    }
    
    /**
     * Get the player timer in the playerPanel class
     * @return PlayerTimer
     */
    public PlayerTimer getPlayerTimer() {
        return this.timer;
    }
    
    /**
     * Check if the given parameter is pointing at the same player
     * @param player    PlayerPanel
     * @return Boolean
     */
    public boolean isSamePlayer(final PlayerPanel player) {
        return this.getPlayerInfo().getName().equals(player.getPlayerInfo().getName());
    }
    
    /**
     * Move the timer backward and display the result
     */
    public void moveTimerBackward() {
        this.timer.moveTimeBackward();
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timer.getTimerToString());
        });
    }
    
    /**
     * Move the timer forward and display the result
     */
    public void moveTimerForward() {
        this.timer.moveTimeForward();
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timer.getTimerToString());
        });
    }
    
    @Override
    public void onUpdate() {
        if (tablePanel.isOnGame() && !tablePanel.gameEnded()) {
            if (tablePanel.getGameBoard().getCurrentPlayer().getAlliance().equals(info.getPlayer().getAlliance())) {
                if (timer.isPaused()) 
                    timer.resumeTimer();
            } else {
                if (!timer.isPaused())
                    timer.pauseTimer();
            }
        }
    }
    
    @Override
    public void onTimeUpdate(long remainingTime) {
        if (remainingTime <= 0) TableManager.setWinner(tablePanel, true);
        SwingUtilities.invokeLater(() -> {
            timerLabel.setText(timer.getTimerToString());
        });
    }

    @Override
    public void onTurnActive(boolean isActive) {
        SwingUtilities.invokeLater(() -> {
            timerLabel.setBackground(isActive ? new Color(182, 181, 169) : new Color(152, 151, 149));
        });
    }
}
