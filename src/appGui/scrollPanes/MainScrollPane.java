package appGui.scrollPanes;

import utilities.CommonConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class MainScrollPane extends JScrollPane {

    public MainScrollPane() {
        initComponents();
    }
    
    private void initComponents() {
        // Set a custom background color
        this.setBackground(CommonConstants.BODY_BG);
        this.setForeground(Color.white);
        this.getViewport().setBackground(CommonConstants.BODY_BG);
        this.setBorder(BorderFactory.createEmptyBorder());
        
        // Customize the vertical scrollbar
        JScrollBar verticalBar = this.getVerticalScrollBar();
        verticalBar.setUI(new CustomScrollBar());
        verticalBar.setPreferredSize(new Dimension(12, 0));
        
        // Set scrolling speed for vertical scroll bar
        verticalBar.setUnitIncrement(16);

        // Customize the horizontal scrollbar
        JScrollBar horizontalBar = this.getHorizontalScrollBar();
        horizontalBar.setUI(new CustomScrollBar());
        horizontalBar.setPreferredSize(new Dimension(0, 12));
        
        // Set scrolling speed for vertical scroll bar
        horizontalBar.setUnitIncrement(16);
    }
}

