package app.panels.loginSignUpPanels;

import app.customField.CompManager;
import app.customField.TextFieldManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import utilities.FontManager;

public class PanelVerifyCode extends JPanel {

    public PanelVerifyCode() {
        initComponents(); 
        setVisible(false);
        addMouseListener(new MouseAdapter() {});
        init();
    }
    
    private void init() {
        CompManager.setPoppinsFont(title, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(txtLabel, FontManager.FontName.POPPINS_MEDIUM);
        TextFieldManager.setFont(userCode);
    }
    
    public String getInputCode() {
        return userCode.getText().trim();
    }
    
    public void addEventButtonOK(final ActionListener event) {
        okButton.addActionListener(event);
    }
    
    @Override
    public void setVisible(final boolean bln) {
        super.setVisible(bln);
        if (bln) {
            userCode.requestFocus();
            userCode.setText("");
        }
    }
    
    @Override
    public void paintComponent(final Graphics g) {
        final Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(50, 50, 50));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelRound = new app.panels.loginSignUpPanels.PanelRound();
        title = new javax.swing.JLabel();
        txtLabel = new javax.swing.JLabel();
        userCode = new javax.swing.JTextField();
        okButton = new app.buttons.MainButton();
        cancelButton = new app.buttons.MainButton();

        setFocusCycleRoot(true);
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        panelRound.setMinimumSize(new java.awt.Dimension(450, 200));
        panelRound.setPreferredSize(new java.awt.Dimension(450, 200));
        panelRound.setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        panelRound.add(title, gridBagConstraints);

        txtLabel.setForeground(new java.awt.Color(255, 255, 255));
        txtLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLabel.setText("Check your mail to get verify code");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        panelRound.add(txtLabel, gridBagConstraints);

        userCode.setBackground(new java.awt.Color(38, 37, 34));
        userCode.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userCode.setForeground(new java.awt.Color(255, 255, 255));
        userCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userCode.setActionCommand("<Not Set>");
        userCode.setAutoscrolls(false);
        userCode.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userCode.setCaretColor(new java.awt.Color(102, 102, 102));
        userCode.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userCode.setMinimumSize(new java.awt.Dimension(280, 40));
        userCode.setOpaque(true);
        userCode.setPreferredSize(new java.awt.Dimension(280, 40));
        userCode.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userCode.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 100);
        panelRound.add(userCode, gridBagConstraints);

        okButton.setText("OK");
        okButton.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        okButton.setMaximumSize(new java.awt.Dimension(90, 35));
        okButton.setMinimumSize(new java.awt.Dimension(90, 35));
        okButton.setPreferredSize(new java.awt.Dimension(90, 35));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelRound.add(okButton, gridBagConstraints);

        cancelButton.setText("CANCEL");
        cancelButton.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        cancelButton.setMaximumSize(new java.awt.Dimension(90, 35));
        cancelButton.setMinimumSize(new java.awt.Dimension(90, 35));
        cancelButton.setPreferredSize(new java.awt.Dimension(90, 35));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelRound.add(cancelButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(panelRound, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.buttons.MainButton cancelButton;
    private app.buttons.MainButton okButton;
    private app.panels.loginSignUpPanels.PanelRound panelRound;
    private javax.swing.JLabel title;
    private javax.swing.JLabel txtLabel;
    private javax.swing.JTextField userCode;
    // End of variables declaration//GEN-END:variables
}
