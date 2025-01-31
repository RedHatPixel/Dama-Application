package app.panels.loginSignUpPanels;

import utilities.CompManager;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelProfile;
import com.db.model.ModelUser;
import com.db.service.ServiceProfile;
import com.db.sounds.NotificationManager;
import java.sql.SQLException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import utilities.FontManager;

public class PanelLoginContinue extends JPanel {

    private ServiceProfile service;
    private ModelProfile profile;
    
    public ModelProfile getProfile() {
        return profile;
    }
    
    public boolean setUserProfile(final ModelUser user) throws SQLException {
        if (service.isConnectionValid()) {
            final ModelProfile subscription = new ModelProfile(
                0, user.getUserID(), user.getUserName(), user.getEmail());
            if (service.checkAccountProfileExist(user.getUserID())) {
                service.getAccountProfile(subscription);
            } else {
                service.insertProfile(subscription);
            }
            profile = subscription;
            return true;
        } else {
            DatabaseConnection.getInstance().reconnect(service);
            profile = null;
            return false;
        }
    }
    
    public PanelLoginContinue() {
        initComponents(); 
        setVisible(false);
        addMouseListener(new MouseAdapter() {});
        init();
    }
    
    private void init() {
        service = new ServiceProfile();
        CompManager.setPoppinsFont(title, FontManager.FontName.POPPINS_BLACK);
        CompManager.setPoppinsFont(txtLabel, FontManager.FontName.POPPINS_MEDIUM);
    }
    
    
    public void addEventButtonContinue(final ActionListener event) {
        continueButton.addActionListener(event);
    }
    
    @Override
    public void setVisible(final boolean bln) {
        super.setVisible(bln);
        if (bln) {
            requestFocus();
            continueButton.requestFocus();
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
        continueButton = new app.buttons.MainButton();
        txtLabel = new javax.swing.JLabel();

        setFocusCycleRoot(true);
        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        panelRound.setMinimumSize(new java.awt.Dimension(450, 200));
        panelRound.setPreferredSize(new java.awt.Dimension(450, 200));
        panelRound.setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("WELCOME TO DAMA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        panelRound.add(title, gridBagConstraints);

        continueButton.setText("Continue");
        continueButton.setFocusable(true);
        continueButton.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        continueButton.setMaximumSize(new java.awt.Dimension(100, 40));
        continueButton.setMinimumSize(new java.awt.Dimension(100, 40));
        continueButton.setPreferredSize(new java.awt.Dimension(100, 40));
        continueButton.setSelected(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        panelRound.add(continueButton, gridBagConstraints);

        txtLabel.setForeground(new java.awt.Color(255, 255, 255));
        txtLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtLabel.setText("Press continue to start the game");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panelRound.add(txtLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(panelRound, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.buttons.MainButton continueButton;
    private app.panels.loginSignUpPanels.PanelRound panelRound;
    private javax.swing.JLabel title;
    private javax.swing.JLabel txtLabel;
    // End of variables declaration//GEN-END:variables
}
