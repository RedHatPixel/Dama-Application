package app.frames;

import javax.swing.*;
import utilities.CommonConstants;
import utilities.ImageFiles;

public class Login extends JFrame {

    public Login() {
        initComponents();
        setIconImage(ImageFiles.DAMA_LOGO);
        setFont(CommonConstants.DEFAULT_FONT);
        setResizable(false);
        setVisible(true);
        validate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel bg = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(650, 500));
        setMinimumSize(new java.awt.Dimension(650, 500));
        setName("login"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(650, 500));
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        jLabel1.setText("jLabel1");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1);

        bg.setBackground(new java.awt.Color(48, 46, 43));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(bg);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
