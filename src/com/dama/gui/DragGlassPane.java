package com.dama.gui;

import com.dama.gui.Table.Status;
import java.awt.AlphaComposite;
import utilities.FontManager;
import java.util.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// Inner Class: DragGlassPane for overlaying dragged icons
final class DragGlassPane extends JPanel {
    
    // Define Variables
    private final BoardPanel boardPanel;
    private Point mouseLocation;
    private ImageIcon draggedIcon;
    private boolean isDragging;

    // Construction: Define the container of dragged icon
    DragGlassPane(final BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
        this.draggedIcon = null;
        this.mouseLocation = null;
        this.isDragging = true;
        initComponents();
    }
    
    // Configurations: Display the Drag Icon designs
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(null);
        setOpaque(false);
        setDoubleBuffered(true);
        validate();
        repaint();
    }
    
    /**
     * Show the start message of the game
     * USED: for the system(TilePanel) only -> same package only
     */
    void showGameStart(final Table table) {
        final String playerOneName = table.getTopPlayerPanel().getPlayerName();
        final String playerTwoName = table.getBottomPlayerPanel().getPlayerName();
        
        final String message = "<html><div style='text-align: center;'>" +
                           playerOneName + "<br>vs<br>" + playerTwoName + 
                           "</div></html>";
        
        removeAll();
        final FadeLabel title = new FadeLabel(message);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setOpaque(false);
        title.setForeground(Color.WHITE);
        title.setFont(FontManager.getFont(
                FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, 32));
        add(title);
        
        // Create a timer to show the message after a delay
        new Timer().schedule(new TimerTask() {
            private float alpha = 0f;
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    alpha += 0.05f;
                    title.setAlpha(alpha);
                    if (alpha >= 1f) {
                        this.cancel();
                    }
                });
            }
        }, 0, 30);
        
        // Create a timer to show the message after a delay
        new Timer().schedule(new TimerTask() {
            private float alpha = 1f;
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    alpha -= 0.05f;
                    title.setAlpha(alpha);
                    if (alpha <= 0f) {
                        this.cancel();
                        removeAll();
                        revalidate();
                        repaint();
                    }
                });
            }
        }, 2000, 30);
        
        
    }
    
    /**
     * Show the ending message of the game
     * USED: for the system(TilePanel) only -> same package only
     */
    void showGameEnd(final Status status, final Table table) {
        final PlayerPanel winner = status.getWinner(
                table.getTopPlayerPanel(), table.getBottomPlayerPanel());
        
        final String message = winner != null ? winner.getPlayerName() + " wins" : "Game Draw";
        
        removeAll();
        final FadeLabel title = new FadeLabel(message);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setOpaque(false);
        title.setForeground(Color.WHITE);
        title.setFont(FontManager.getFont(
                FontManager.FontName.POPPINS_BLACK, FontManager.FontType.POPPINS, 32));
        add(title, BorderLayout.CENTER);
        
        // Create a timer to show the message after a delay
        new Timer().schedule(new TimerTask() {
            private float alpha = 0f;
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    alpha += 0.05f;
                    title.setAlpha(alpha);
                    if (alpha >= 1f) {
                        this.cancel();
                    }
                });
            }
        }, 0, 30);
        
        revalidate();
        repaint();
    }
    
    /**
     * Set the new location of the dragged icon
     * USED: for the system(TilePanel) only -> same package only
     * @param location Point
     */
    void setPointLocation(final Point location) {
        mouseLocation = location;
        revalidate();
        repaint();
    }

    /**
     * Set the new dragged icon
     * USED: for the system(TilePanel) only -> same package only
     * @param icon ImageIcon
     */
    void setDraggedIcon(final ImageIcon icon) {
        draggedIcon = icon;
    }
    
    /**
     * Set the new condition of dragging
     * USED: for the system(TilePanel) only -> same package only
     * @param isDragged Boolean
     */
    void setDragging(final boolean isDragged) {
        isDragging = isDragged;
    }
    
    /**
     * Get the condition if the piece is dragging
     * USED: for the system(TilePanel) only -> same package only
     * @return Boolean
     */
    boolean isDragging() {
        return isDragging;
    }
    
    class FadeLabel extends JLabel {
        private float alpha = 0f; // Default to fully transparent

        private FadeLabel(String text) {
            super(text);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
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
