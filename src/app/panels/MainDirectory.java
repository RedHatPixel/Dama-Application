package app.panels;

import app.panels.CardHandlers.PanelCardManager;

public class MainDirectory extends PanelCardManager {

    public MainDirectory() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new app.panels.MainMenu();
        gamePlay = new app.panels.GamePlay();
        tutorial = new app.panels.Tutorial();
        about = new app.panels.About();
        account = new app.panels.Account();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setDoubleBuffered(false);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(850, 550));
        setName("Main Directory"); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(850, 550));
        setLayout(new java.awt.CardLayout());
        add(mainMenu, "Main Menu");
        add(gamePlay, "Game Play");
        gamePlay.getAccessibleContext().setAccessibleName("Game Play");

        add(tutorial, "Tutorial");
        add(about, "About");
        add(account, "Account");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.panels.About about;
    private app.panels.Account account;
    private app.panels.GamePlay gamePlay;
    private app.panels.MainMenu mainMenu;
    private app.panels.Tutorial tutorial;
    // End of variables declaration//GEN-END:variables
}
