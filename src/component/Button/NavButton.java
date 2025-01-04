package component.Button;

import utilities.CommonConstants;
import utilities.FontManager;
import utilities.FontManager.*;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public class NavButton extends javax.swing.JButton {
    
    private Dimension MAX_SIZE = new Dimension(3000, 40);
    private Dimension MIN_SIZE = new Dimension(150, 40);
    private Dimension PREFFERED_SIZE = new Dimension(150, 40);
    private int FONT_SIZE = 18;
    
    public NavButton() {
        initComponent();
    }

    private void initComponent() {
        
        // Set Background and Foreground
        setBackground(CommonConstants.BUTTON_BG);
        setForeground(new java.awt.Color(255, 255, 255));
        
        // Set the font style, text content and ToolTipText when hover
        setFont(FontManager.getFont(FontName.POPPINS_EXTRABOLD, FontType.POPPINS, FONT_SIZE));
        setText("");
        
        // Set the Internal Padding
        setBorder(BorderFactory.createEmptyBorder(2, 14, 2, 3));
        
        // Options
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFocusable(false);
        setOpaque(true);
        
        // Alignment
        setHorizontalAlignment(SwingConstants.LEFT);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        
        setVerticalAlignment(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        
        setMaximumSize(MAX_SIZE);
        setMinimumSize(MIN_SIZE);
        setPreferredSize(PREFFERED_SIZE);
        setRequestFocusEnabled(false);
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBackground(CommonConstants.BUTTON_BG.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(CommonConstants.BUTTON_BG);
            }
        });
    }
}
