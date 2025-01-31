package app.panels.loginSignUpPanels;

import utilities.CompManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import utilities.FontManager;

public class PanelLoading extends JPanel {

    public PanelLoading() {
        initComponents(); 
        setVisible(false); 
        addMouseListener(new MouseAdapter() {});
        CompManager.setPoppinsFont(loading, FontManager.FontName.POPPINS_SEMIBOLD);
    }
    
    @Override
    public void setVisible(final boolean bln) {
        super.setVisible(bln);
        if (bln) {
            PanelLoading.this.requestFocus();
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(50, 50, 50));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loading = new javax.swing.JLabel();

        setFocusCycleRoot(true);
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        loading.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        loading.setForeground(new java.awt.Color(255, 255, 255));
        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/gif/Loading.gif"))); // NOI18N
        loading.setText("Prompting to message your google account.");
        loading.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loading.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        add(loading, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel loading;
    // End of variables declaration//GEN-END:variables
}
