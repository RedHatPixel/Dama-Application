package com.dama.gui.controlPanel;
import app.panels.CardHandlers.PanelCardManager;

public class SettingContainer extends PanelCardManager {
    public SettingContainer() {
        super();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        com.dama.gui.controlPanel.MultiplayerSetting multiplayerSetting = new com.dama.gui.controlPanel.MultiplayerSetting();
        playerSetting = new com.dama.gui.controlPanel.PlayerSetting();

        setBackground(new java.awt.Color(38, 37, 34));
        setForeground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        setMinimumSize(new java.awt.Dimension(350, 600));
        setName("Setting Container"); // NOI18N
        setPreferredSize(new java.awt.Dimension(350, 600));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.CardLayout());
        add(multiplayerSetting, "Multiplayer Setting");
        multiplayerSetting.getAccessibleContext().setAccessibleName("");

        add(playerSetting, "Player Setting");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dama.gui.controlPanel.PlayerSetting playerSetting;
    // End of variables declaration//GEN-END:variables
}
