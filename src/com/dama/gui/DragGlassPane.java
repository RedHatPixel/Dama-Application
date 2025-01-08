package com.dama.gui;

import com.dama.gui.Table.Status;
import java.awt.AlphaComposite;
import utilities.FontManager;
import utilities.FontManager.*;
import java.util.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private final FadeLabel title;
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
     */
    void showGameStart(final Table table) {
        removeAnimation();
        final String playerOneName = table.getTopPlayerPanel().getPlayerName();
        final String playerTwoName = table.getBottomPlayerPanel().getPlayerName();
        final String message = "<html><div style='text-align: center;'>" +
                           playerOneName + "<br>vs<br>" + playerTwoName + 
                           "</div></html>";
        title.setText(message);
        setBoardLocation();
        
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
                        title.setText("");
                        this.cancel();
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
        removeAnimation();
        final PlayerPanel winner = status.getWinner(
                table.getTopPlayerPanel(), table.getBottomPlayerPanel());
        final String message = winner != null ? winner.getPlayerName() + " wins" : "Game Draw";
        title.setText(message);
        setBoardLocation();
        
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
    }
    
    /**
     * To remove any animation or background from the board
     * USED: for the controls(BoardPanel) only -> same package only
     */
    void removeAnimation() {
        removeAll();
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
     * Set the new location of the board
     * USED: for the system(TilePanel) only -> same package only
     * @param bounds Rectangle
     */
    void setBoardLocation() {
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
