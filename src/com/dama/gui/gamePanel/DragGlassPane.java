package com.dama.gui.gamePanel;

import com.dama.gui._configurations.dependencies.Status;
import utilities.FontManager;
import utilities.FontManager.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Inner Class: DragGlassPane for overlaying dragged icons
public final class DragGlassPane extends JPanel {
    
    // Define Variables
    private final BoardPanel boardPanel;
    private final FadeLabel title;
    
    private Timer fadeInTimer;
    private Timer fadeOutTimer;
    
    private Point mouseLocation;
    private ImageIcon draggedIcon;
    private boolean isDragging;

    // Construction: Define the container of dragged icon
    DragGlassPane(final BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.title = new FadeLabel();
        this.draggedIcon = null;
        this.mouseLocation = null;
        this.isDragging = true;
        initComponents();
    }
    
    // Configurations: Display the Drag Icon designs
    private void initComponents() {
        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setDoubleBuffered(true);
        validate();
        repaint();
    }
    
    /**
     * Show the start message of the game
     * USED: for the system(TilePanel) only -> same package only
     * @param table     TablePanel
     */
    public void showGameStart(final TablePanel table) {
        removeAnimation();
        final String playerName = table.getPlayerPanel().getPlayerInfo().getName();
        final String opponentName = table.getOpponentPanel().getPlayerInfo().getName();
        
        String message;
        if (!table.getOrientation().isNormal()) {
            message = "<html><div style='text-align: center;'>" +
                opponentName + "<br>vs<br>" + playerName + 
                "</div></html>";
        } else {
            message = "<html><div style='text-align: center;'>" +
                playerName + "<br>vs<br>" + opponentName + 
                "</div></html>";
        }
        
        title.setText(message);
        setBoardLocation();
        
        if (fadeInTimer != null && fadeInTimer.isRunning()) {
            fadeInTimer.stop();
        }
        if (fadeOutTimer != null && fadeOutTimer.isRunning()) {
            fadeOutTimer.stop();
        }
        
        // Fade-in effect
        fadeInTimer = new javax.swing.Timer(30, null);
        fadeInTimer.addActionListener(new ActionListener() {
            private float alpha = 0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                title.setAlpha(alpha);
                if (alpha >= 1f) {
                    fadeInTimer.stop();
                }
            }
        });
        fadeInTimer.start();

        // Fade-out effect
        fadeOutTimer = new javax.swing.Timer(30, null);
        fadeOutTimer.addActionListener(new ActionListener() {
            private float alpha = 1f;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.05f;
                title.setAlpha(alpha);
                if (alpha <= 0f) {
                    title.setText("");
                    fadeOutTimer.stop();
                }
            }
        });
        fadeOutTimer.setInitialDelay(2000);
        fadeOutTimer.start();
    }
    
    /**
     * Show the ending message of the game
     * USED: for the system(TilePanel) only -> same package only
     * @param status    Status
     * @param table     TablePanel
     */
    public void showGameEnd(final Status status, final TablePanel table) {
        removeAnimation();
        final PlayerPanel winner = status.getWinner(
                table.getOpponentPanel(), table.getPlayerPanel());
        final String message = winner != null ? winner.getPlayerInfo().getName() + " wins" : "Game Draw";
        title.setText(message);
        setBoardLocation();
        
        if (fadeInTimer != null && fadeInTimer.isRunning()) {
            fadeInTimer.stop();
        }
        if (fadeOutTimer != null && fadeOutTimer.isRunning()) {
            fadeOutTimer.stop();
        }

        // Fade-in effect
        fadeInTimer = new javax.swing.Timer(30, null);
        fadeInTimer.addActionListener(new ActionListener() {
            private float alpha = 0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                title.setAlpha(alpha);
                if (alpha >= 1f) {
                    fadeInTimer.stop();
                }
            }
        });
        fadeInTimer.start();
    }
    
    /**
     * To remove any animation or background from the board
     * USED: for the controls(BoardPanel) only -> same package only
     */
    public void removeAnimation() {
        if (fadeInTimer != null && fadeInTimer.isRunning()) {
            fadeInTimer.stop();
        }
        if (fadeOutTimer != null && fadeOutTimer.isRunning()) {
            fadeOutTimer.stop();
        }
        removeAll();
        revalidate();
        repaint();
    }
    
    /**
     * Set the new location of the dragged icon
     * USED: for the system(TilePanel) only -> same package only
     * @param location Point
     */
    public void setPointLocation(final Point location) {
        mouseLocation = location;
        revalidate();
        repaint();
    }
    
    /**
     * Set the new location of the board
     * USED: for the system(TilePanel) only -> same package only
     */
    public void setBoardLocation() {
        if (!title.getText().isBlank()) {
            removeAll();
            
            // Convert the boardPanel's bounds to the coordinate space of the dragGlassPane
            final Rectangle boardBounds = boardPanel.getBounds();
            final Point boardLocationOnGlassPane = SwingUtilities.convertPoint(
                boardPanel.getParent(), boardBounds.x, boardBounds.y, this);
            final Rectangle absoluteBoardBounds = new Rectangle(
                boardLocationOnGlassPane.x, 
                boardLocationOnGlassPane.y, 
                boardBounds.width, 
                boardBounds.height
            );
            
            title.setBounds(absoluteBoardBounds.x, absoluteBoardBounds.y, 
                    absoluteBoardBounds.width, absoluteBoardBounds.height);
            add(title);
            revalidate();
            repaint();
        }
        else {
            removeAll();
            revalidate();
            repaint();
        }
    }

    /**
     * Set the new dragged icon
     * USED: for the system(TilePanel) only -> same package only
     * @param icon ImageIcon
     */
    public void setDraggedIcon(final ImageIcon icon) {
        draggedIcon = icon;
    }
    
    /**
     * Set the new condition of dragging
     * USED: for the system(TilePanel) only -> same package only
     * @param isDragged Boolean
     */
    public void setDragging(final boolean isDragged) {
        isDragging = isDragged;
    }
    
    /**
     * Get the condition if the piece is dragging
     * USED: for the system(TilePanel) only -> same package only
     * @return Boolean
     */
    public boolean isDragging() {
        return isDragging;
    }
    
    class FadeLabel extends JLabel {
        private float alpha = 0f; // Default to fully transparent

        private FadeLabel() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
            setVerticalAlignment(JLabel.CENTER);
            setOpaque(false);
            setVisible(true);
            setForeground(Color.WHITE);
            setBackground(new Color(39, 38, 36, 255));
            setFont(FontManager.getFont(
                    FontName.POPPINS_BLACK, FontType.POPPINS, 22));
        }
        @Override
        protected void paintComponent(final Graphics g) {
            final Graphics2D g2d = (Graphics2D) g;
            
            final float bgAlpha = 0.4f * alpha;
            final Color bgColor = getBackground();
            g2d.setColor(new Color(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), Math.round(bgAlpha * 255)));
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paintComponent(g);
        }
        public void setAlpha(float alpha) {
            this.alpha = Math.max(0f, Math.min(alpha, 1f));
            repaint();
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (isDragging && draggedIcon != null && mouseLocation != null) {
            int x = mouseLocation.x - (draggedIcon.getIconWidth() / 2);
            int y = mouseLocation.y - (draggedIcon.getIconHeight() / 2);
            
            // Convert the boardPanel's bounds to the coordinate space of the dragGlassPane
            Rectangle boardBounds = boardPanel.getBounds();
            Point boardLocationOnGlassPane = SwingUtilities.convertPoint(
                boardPanel.getParent(), boardBounds.x, boardBounds.y, this);
            Rectangle absoluteBoardBounds = new Rectangle(
                boardLocationOnGlassPane.x, 
                boardLocationOnGlassPane.y, 
                boardBounds.width, 
                boardBounds.height
            );

            // Clamp the x and y positions within the bounds of the board
            x = Math.max(absoluteBoardBounds.x, 
                         Math.min(x, absoluteBoardBounds.x + absoluteBoardBounds.width - draggedIcon.getIconWidth()));
            y = Math.max(absoluteBoardBounds.y, 
                         Math.min(y, absoluteBoardBounds.y + absoluteBoardBounds.height - draggedIcon.getIconHeight()));

            g.drawImage(draggedIcon.getImage(), x, y, 
                      draggedIcon.getIconWidth(), draggedIcon.getIconHeight(), null);
        }
    }
}
