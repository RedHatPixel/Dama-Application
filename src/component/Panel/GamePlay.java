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
        javax.swing.JPanel mainBody = new javax.swing.JPanel();
        game = new javax.swing.JPanel();
        setting = new javax.swing.JPanel();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("BasePanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        mainBody.setDoubleBuffered(false);
        mainBody.setEnabled(false);
        mainBody.setFocusable(false);
        mainBody.setName("mainBody"); // NOI18N
        mainBody.setOpaque(false);
        mainBody.setLayout(new javax.swing.BoxLayout(mainBody, javax.swing.BoxLayout.LINE_AXIS));

        game.setDoubleBuffered(false);
        game.setEnabled(false);
        game.setFocusable(false);
        game.setMinimumSize(new java.awt.Dimension(270, 10));
        game.setOpaque(false);
        game.setPreferredSize(new java.awt.Dimension(270, 10));
        mainBody.add(game);

        setting.setDoubleBuffered(false);
        setting.setEnabled(false);
        setting.setFocusable(false);
        setting.setPreferredSize(new java.awt.Dimension(1, 10));
        mainBody.add(setting);

        add(mainBody, java.awt.BorderLayout.CENTER);
        mainBody.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleName("BasePanel");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel game;
    private component.Panel.subPanel.Navigator navigator;
    private javax.swing.JPanel setting;
    // End of variables declaration//GEN-END:variables
}
