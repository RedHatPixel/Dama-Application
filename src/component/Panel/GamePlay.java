package component.Panel;

import com.dama.gui.Table;
import utilities.CommonConstants;
import utilities.Directory;

public class GamePlay extends javax.swing.JPanel implements Direction {

    public GamePlay() {
        initComponents();
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setDirectName();
        
        this.validate();
        this.repaint();
    }
    
    @Override
    public void setDirectName() {
        this.setName(Directory.Panel.GAME_PLAY.getName());
    }
    
    public static void setNewTable(final Table table) {
        GamePlay.table = table;
        if (tableContainer != null) {
            tableContainer.removeAll();
            tableContainer.add(table, java.awt.BorderLayout.CENTER);
            tableContainer.revalidate();
            tableContainer.repaint();
        }
    }
    
    public static Table getTable() {
        return GamePlay.table;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        navigator = new component.Panel.subPanel.Navigator();
        mainBody = new javax.swing.JPanel();
        tableContainer = new javax.swing.JPanel();
        table = new com.dama.gui.Table();
        javax.swing.JPanel settingBG = new javax.swing.JPanel();
        component.Panel.controlPanel.SettingContainer settingContainer = new component.Panel.controlPanel.SettingContainer();
        component.Panel.controlPanel.MultiplayerSetting multiplayerSetting = new component.Panel.controlPanel.MultiplayerSetting();
        component.Panel.controlPanel.PlayerSetting playerSetting = new component.Panel.controlPanel.PlayerSetting();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("BasePanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        mainBody.setFocusable(false);
        mainBody.setName("mainBody"); // NOI18N
        mainBody.setOpaque(false);
        mainBody.setLayout(new java.awt.BorderLayout());

        tableContainer.setOpaque(false);
        tableContainer.setLayout(new java.awt.BorderLayout());
        tableContainer.add(table, java.awt.BorderLayout.CENTER);

        mainBody.add(tableContainer, java.awt.BorderLayout.CENTER);

        settingBG.setForeground(new java.awt.Color(255, 255, 255));
        settingBG.setFocusable(false);
        settingBG.setMinimumSize(new java.awt.Dimension(350, 10));
        settingBG.setOpaque(false);
        settingBG.setPreferredSize(new java.awt.Dimension(350, 10));
        settingBG.setRequestFocusEnabled(false);
        settingBG.setLayout(new java.awt.GridBagLayout());

        multiplayerSetting.setOpaque(false);
        settingContainer.add(multiplayerSetting, "Multiplayer Setting");
        settingContainer.add(playerSetting, "Player Setting");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        settingBG.add(settingContainer, gridBagConstraints);

        mainBody.add(settingBG, java.awt.BorderLayout.EAST);

        add(mainBody, java.awt.BorderLayout.CENTER);
        mainBody.getAccessibleContext().setAccessibleName("");

        getAccessibleContext().setAccessibleName("BasePanel");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainBody;
    private component.Panel.subPanel.Navigator navigator;
    private static com.dama.gui.Table table;
    private static javax.swing.JPanel tableContainer;
    // End of variables declaration//GEN-END:variables
}
