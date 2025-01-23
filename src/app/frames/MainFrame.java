package app.frames;

import app.panels.CardHandlers.FrameCardManager;
import com.dama.engine.sounds.SoundManager;
import utilities.CommonConstants;
import utilities.ImageFiles;

public class MainFrame extends FrameCardManager {

    public MainFrame() {
        initComponents();
        setIconImage(ImageFiles.DAMA_LOGO);
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setSize(CommonConstants.PREFFERED_SIZE);
        setResizable(true);
        setVisible(true);
        if (!SoundManager.Sounds.BACKGROUND_MUSIC.isPlaying()) {
            SoundManager.Sounds.BACKGROUND_MUSIC.play();
            SoundManager.Sounds.BACKGROUND_MUSIC.loopForever();
        }
        validate();
        repaint();
    }
    
    public void closeWindow() {
        final int status = new QuitConfirmation(
                "Are you sure you want to quit?", 
                "Quit Confirmation", this).getReturnStatus();
        if (status == app.frames.QuitConfirmation.RET_YES) {
            this.dispose();
            System.exit(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new app.panels.MainMenu();
        app.panels.GamePlay gamePlay = new app.panels.GamePlay();
        app.panels.Tutorial tutorial = new app.panels.Tutorial();
        about = new app.panels.About();

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
        getContentPane().add(gamePlay, "Game Play");
        gamePlay.getAccessibleContext().setAccessibleName("Game Play");

        getContentPane().add(tutorial, "Tutorial");
        getContentPane().add(about, "About");

        getAccessibleContext().setAccessibleName("MainFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeWindow();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.panels.About about;
    private app.panels.MainMenu mainMenu;
    // End of variables declaration//GEN-END:variables
}
