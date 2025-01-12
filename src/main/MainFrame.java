package main;

import component.OptionPane.QuitConfirmation;
import component.Panel.CardHandlers.FrameCardManager;
import utilities.CommonConstants;
import utilities.Configurations;

public class MainFrame extends FrameCardManager {

    public MainFrame() {
        initComponents();
        setIconImage(Configurations.loadImage(CommonConstants.IMAGE_DIRECTORY));
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setSize(CommonConstants.PREFFERED_SIZE);
        setResizable(true);
        setVisible(true);
        validate();
        repaint();
    }
    
    public void closeWindow() {
        final int status = new QuitConfirmation(
                "Are you sure you want to quit?", 
                "Quit Confirmation", this).getReturnStatus();
        if (status == component.OptionPane.QuitConfirmation.RET_YES) {
            this.dispose();
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        component.Panel.MainMenu mainMenu = new component.Panel.MainMenu();
        component.Panel.Tutorial tutorial = new component.Panel.Tutorial();
        component.Panel.GamePlay gamePlay = new component.Panel.GamePlay();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Dama");
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(950, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());
        getContentPane().add(mainMenu, "Main Menu");
        getContentPane().add(tutorial, "Tutorial");
        getContentPane().add(gamePlay, "Game Play");
        gamePlay.getAccessibleContext().setAccessibleName("Game Play");

        getAccessibleContext().setAccessibleName("MainFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeWindow();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
