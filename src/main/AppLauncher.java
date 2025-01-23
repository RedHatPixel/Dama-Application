package main;

import app.frames.Login;
import app.frames.MainFrame;

public class AppLauncher {
    
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
//            new MainFrame();
        new Login();
        });
    }
}
