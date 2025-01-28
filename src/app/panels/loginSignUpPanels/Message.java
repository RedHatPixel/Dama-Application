package app.panels.loginSignUpPanels;

import app.customField.CompManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import utilities.FontManager;
import utilities.ImageFiles;

public class Message extends javax.swing.JPanel {

    private MessageType messageType = MessageType.SUCCESS;
    private boolean show;
    
    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
        CompManager.setPoppinsFont(lbMessage, FontManager.FontName.POPPINS_MEDIUM);
    }
    
    public void showMessage(final MessageType messageType, final String message) {
        this.messageType = messageType;
        lbMessage.setText(message);
        if (messageType == MessageType.SUCCESS) {
            lbMessage.setIcon(ImageFiles.CHECK);
        } else {
            lbMessage.setIcon(ImageFiles.PREVENT);
        }
    }
    
    public boolean isShow() {
        return this.show;
    }
    
    public void setShow(final boolean show) {
        this.show = show;
    }
    
    public static enum MessageType {
        SUCCESS, ERROR
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        if (messageType == MessageType.SUCCESS) {
            g2d.setColor(new Color(15, 174, 37));
        } else {
            g2d.setColor(new Color(180, 52, 53));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1 );
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbMessage = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(300, 38));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(300, 38));
        setLayout(new java.awt.BorderLayout());

        lbMessage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(255, 255, 255));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Message");
        add(lbMessage, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbMessage;
    // End of variables declaration//GEN-END:variables
}
