package main;

import com.dama.gui.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

public class AppLauncher {

    public static void main(String[] args) {
        
        // Set the Nimbus look and feel to Metal
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
//            MainFrame main = new MainFrame();
//            main.setVisible(true);
//            Directory.insertParent(main);

            JFrame frame = new JFrame();
            frame.setVisible(true);
            frame.setSize(new Dimension(700, 700));
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());
            frame.getContentPane().add(new Table());
            
        });
    }
    
}
