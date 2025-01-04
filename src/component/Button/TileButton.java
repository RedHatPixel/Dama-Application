package component.Button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public class TileButton extends javax.swing.JButton {
    
    private Dimension MAX_SIZE = new Dimension(100, 100);
    private Dimension MIN_SIZE = new Dimension(100, 100);
    private Dimension PREFFERED_SIZE = new Dimension(100, 100);
    private Color backgroundColor;
    
    public TileButton(Color bg, int row, int col) {
        backgroundColor = bg;
        
        initComponent();
    }

    private void initComponent() {
        
        setForeground(Color.WHITE);
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder());
        setText("");
        
        // Options
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setOpaque(true);
        
        // Alignment
        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.CENTER);
        
        setVerticalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        
        setMaximumSize(MAX_SIZE);
        setMinimumSize(MIN_SIZE);
        setPreferredSize(PREFFERED_SIZE);
        setRequestFocusEnabled(false);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBackground(backgroundColor.brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
            }
        });
    }
}
