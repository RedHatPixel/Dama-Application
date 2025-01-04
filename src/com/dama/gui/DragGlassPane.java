package com.dama.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Inner Class: DragGlassPane for overlaying dragged icons
final class DragGlassPane extends JPanel {
    
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
        setLayout(null);
        setBorder(null);
        setOpaque(false);
        setDoubleBuffered(true);
        validate();
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
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isDragging && draggedIcon != null && mouseLocation != null) {
            int x = mouseLocation.x - (draggedIcon.getIconWidth() / 2);
            int y = mouseLocation.y - (draggedIcon.getIconHeight() / 2);
            
            // Clamp the x and y positions within the bounds of the DragGlassPane
            Rectangle bounds = boardPanel.getBounds();
            x = Math.max(bounds.x, Math.min(x, bounds.x + bounds.width - draggedIcon.getIconWidth()));
            y = Math.max(bounds.y, Math.min(y, bounds.y + bounds.height - draggedIcon.getIconHeight()));
            
            g.drawImage(draggedIcon.getImage(), x, y, 
                      draggedIcon.getIconWidth(), draggedIcon.getIconHeight(), null);
        }
    }
}
