package main;

import utilities.CommonConstants;
import utilities.Configurations;
import utilities.Directory;
import java.awt.CardLayout;

public class MainFrame extends javax.swing.JFrame {

    private String ImageDirectory = "src/resources/images/DamaLogo.png";
    
    public MainFrame() {
        initComponents();
        
        setIconImage(Configurations.loadImage(ImageDirectory));
        setFont(CommonConstants.DEFAULT_FONT);
        
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setSize(CommonConstants.PREFFERED_SIZE);
        setResizable(true);
        
        this.setVisible(true);
        this.revalidate();
        this.repaint();
        
        this.setName(Directory.ParentName.MAIN_FRAME.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new component.Panel.MainMenu();
        tutorial = new component.Panel.Tutorial();

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
        getContentPane().add(mainMenu, "MainMenu");
        getContentPane().add(tutorial, "Tutorial");

        getAccessibleContext().setAccessibleName("MainFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        int status = new component.OptionPane.QuitConfirmation(this, true).getReturnStatus();
        
        if (status == component.OptionPane.QuitConfirmation.RET_YES) {
            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    public void setDirectory(Directory.Panel panel) {
        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), panel.getName());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Panel.MainMenu mainMenu;
    private component.Panel.Tutorial tutorial;
    // End of variables declaration//GEN-END:variables
}
