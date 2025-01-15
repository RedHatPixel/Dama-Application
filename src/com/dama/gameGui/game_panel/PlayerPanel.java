package com.dama.gameGui.game_panel;

import com.dama.engine.players.Player;
import com.dama.gameGui.GameInfo;
import com.dama.gameGui.GameInfo.GameDuration;
import com.dama.gameGui.TableManager;
import java.awt.BorderLayout;
import utilities.FontManager;
import utilities.FontManager.*;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class PlayerPanel extends JPanel {
   
    // Static Variables
    private static final Dimension PREFERRED_SIZE = new Dimension(500, 50);
    
    // Define Variables
    private final Player player;
    private final String name;
    private final Table table;
    private final Timer timer;
    private final ArrayList<Long> timeIntervals;
    private int currentIntervalIndex;
    
    private long remainingTime;
    private int moveBackCount;
    private boolean isPaused;
    
    // Define Components
    private JLabel nameLabel;
    private JLabel timerLabel;
    
    PlayerPanel(final Table table, final Player player, final String name) {
        this.table = table;
        this.name = name;
        this.player = player;
        this.timer = new Timer();
        this.timeIntervals = new ArrayList<>();
        this.currentIntervalIndex = -1;
        this.remainingTime = 0;
        this.moveBackCount = 3;
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
        
        nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setOpaque(false);
        nameLabel.setPreferredSize(new Dimension(170, 50));
        nameLabel.setMaximumSize(nameLabel.getPreferredSize());
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        nameLabel.setFont(FontManager.getFont(
                FontName.POPPINS_SEMIBOLD, FontType.POPPINS, 16));
        
        timerLabel = new JLabel(getTimerToString());
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
        
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setOpaque(false);
        timerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));
        timerPanel.add(timerLabel, BorderLayout.CENTER);
        
        add(nameLabel, BorderLayout.WEST);
        add(timerPanel, BorderLayout.EAST);
    }
    
    /**
     * Get the player engine in the player class
     * @return Player
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Get the name of the player
     * @return Player
     */
    public String getPlayerName() {
        return this.name;
    }
    
    /**
     * Set the latest time of the player latest move
     */
    public void setLatestTime() {
        if (currentIntervalIndex > 0) {
            currentIntervalIndex--;
            this.remainingTime = timeIntervals.get(currentIntervalIndex);
            timerLabel.setText(getTimerToString());
        }
    }

    public void moveBackTime() {
        if (currentIntervalIndex > 0) {
            currentIntervalIndex--;
            timerLabel.setText(getTimerToString(timeIntervals.get(currentIntervalIndex)));
        }
    }
    
    public void moveForwardTime() {
        if (currentIntervalIndex < timeIntervals.size()) {
            currentIntervalIndex++;
            timerLabel.setText(getTimerToString(timeIntervals.get(currentIntervalIndex)));
        }
    }
    
    /**
     * Set new time interval after a move
     */
    public void setNewTimeInterval() {
        if (currentIntervalIndex < timeIntervals.size() - 1) {
            timeIntervals.subList(currentIntervalIndex + 1, timeIntervals.size()).clear();
        }
        timeIntervals.add(this.remainingTime);
        currentIntervalIndex = timeIntervals.size() - 1;
    }
    
    /**
     * Get the available back move of the player
     * @return Integer
     */
    public int getAvailableBackMove() {
        return this.moveBackCount;
    }
    
    /**
     * Subtract the available back move of the player
     */
    public void subsAvailableBackMove() {
        this.moveBackCount--;
    }
    
    public String getTimerToString(final long time) {
        final long minutes = (time / 1000) / 60;
        final long seconds = (time / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private String getTimerToString() {
        return getTimerToString(remainingTime);
    }
    
    void startTimer(final GameDuration gameDuration) {
        this.remainingTime = gameDuration.getTime();
        timerLabel.setText(getTimerToString());
        setNewTimeInterval();
        
        if (!GameInfo.isGameStart() || gameDuration == GameDuration.NO_TIMER) return;
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                if (table.isOnGame() && !table.gameEnded()) {
                    if (table.getGameBoard().getCurrentPlayer().getAlliance().equals(player.getAlliance())) {
                        resumeTimer();
                        timerLabel.setBackground(new Color(182, 181, 169));
                    } else {
                        timerLabel.setBackground(new Color(152, 151, 149));
                        pauseTimer();
                    }
                }
                
                if (!isPaused && remainingTime > 0) {
                    remainingTime -= 1000;
                    timerLabel.setText(getTimerToString());
                }
                else if (remainingTime <= 0) {
                    timerLabel.setText(getTimerToString());
                    TableManager.setWinner(table, true);
                    timer.cancel();
                }
            }
        }, 2000, 1000);
        pauseTimer();
    }
    
    public void stopTimer() {
        this.timer.cancel();
    }
    
    public void pauseTimer() {
        this.isPaused = true;
    }

    public void resumeTimer() {
        this.isPaused = false;
    }
}
