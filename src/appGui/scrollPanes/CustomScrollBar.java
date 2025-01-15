package appGui.scrollPanes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JButton;
import javax.swing.JComponent;

public class CustomScrollBar extends BasicScrollBarUI {
    
    private Color scrollColor = new Color(38,37,34);
    private Color thumbShadow = new Color(29, 28, 26);
    
    private Color backgroundColor = new Color(48, 46, 43);
    private Color higlightColor = new Color(59,57,54);

    @Override
    protected void configureScrollBarColors() {
        
        thumbColor = scrollColor; // Scroll thumb color
        thumbDarkShadowColor = thumbShadow; // Thumb shadow color
        
        trackColor = backgroundColor; // Scrollbar background
        trackHighlightColor = higlightColor; // Highlight color
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    // Create an invisible button for the ends of the scrollbar
    private JButton createZeroButton() {
        
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    public void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(thumbColor);
        g.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 0, 0); // Rounded corners
    }

    @Override
    public void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }
}

