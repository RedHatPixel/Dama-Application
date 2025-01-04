package component.Panel;

import utilities.CommonConstants;
import utilities.Directory;

public abstract class GamePlay extends javax.swing.JPanel implements Direction {

    public GamePlay() {
        initComponents();
        
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setDirectName();
        
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public void setDirectName() {
        this.setName(Directory.Panel.GAME_PLAY.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        navigator = new component.Panel.subPanel.Navigator();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("BasePanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        getAccessibleContext().setAccessibleName("BasePanel");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Panel.subPanel.Navigator navigator;
    // End of variables declaration//GEN-END:variables
}
