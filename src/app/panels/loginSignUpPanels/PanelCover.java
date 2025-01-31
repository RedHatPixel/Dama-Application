 package app.panels.loginSignUpPanels;

import app.buttons.MainButton;
import utilities.CompManager;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import utilities.FontManager;
import utilities.ImageFiles;

public class PanelCover extends JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###");
    private boolean isLogin;
    private MigLayout layout;
    private MainButton nextButton;
    private ActionListener event;
    private Image backgroundImage;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        backgroundImage = ImageFiles.LOGINSIGNUP_BG;
        init();
    }
    
    private void init() {
        CompManager.setPoppinsFont(title, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(description1, FontManager.FontName.POPPINS_MEDIUM);
        CompManager.setPoppinsFont(description2, FontManager.FontName.POPPINS_MEDIUM);
        nextButton = new MainButton();
        nextButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        nextButton.setText("SIGN IN");
        nextButton.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        removeAll();
        add(title);
        add(description1);
        add(description2);
        add(nextButton, "w 60%, h 40");
        revalidate();
        repaint();
    }
    
    public void addEvent(final ActionListener event) {
        this.event = event;
    }
    
    public void registerLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description2, "pad 0 -" + v + "% 0 0");
    }
    
    public void registerRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description2, "pad 0 -" + v + "% 0 0");
    }
    
    public void loginLeft(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description2, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    public void loginRight(double v) {
        v = Double.parseDouble(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description2, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    private void login(final boolean login) {
        if (isLogin != login) {
            if (login) {
                title.setText("START DAMA!");
                description1.setText("Start your game and connect with us");
                description2.setText("For a more continuous game strike.");
                nextButton.setText("SIGN UP");
            } else {
                title.setText("WELCOME TO DAMA!");
                description1.setText("Create your very own account");
                description2.setText("and be a part of our community.");
                nextButton.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }
    
    @Override
    protected void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        final GradientPaint gra = new GradientPaint(0, getHeight() / 3, new Color(0, 0, 0, 120),
                0, getHeight(), new Color(0, 0, 0, 180));
        g2d.setPaint(gra);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        title = new javax.swing.JLabel();
        description1 = new javax.swing.JLabel();
        description2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("WELCOME TO DAMA!");

        description1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        description1.setForeground(new java.awt.Color(255, 255, 255));
        description1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description1.setText("Create your very own account");

        description2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        description2.setForeground(new java.awt.Color(255, 255, 255));
        description2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        description2.setText("and be a part of our community.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description1)
                            .addComponent(description2))
                        .addGap(0, 48, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(description1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(description2)
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel description1;
    private javax.swing.JLabel description2;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
