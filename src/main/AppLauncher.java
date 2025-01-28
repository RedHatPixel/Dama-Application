package main;

import app.frames.LoginSignUpFrame;
import app.frames.MainFrame;

public class AppLauncher {
    
    public static void main(String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
//            new MainFrame();
            new LoginSignUpFrame();
        });
    }
}
