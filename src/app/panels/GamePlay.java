package app.panels;

import app.panels.CardHandlers.CardLayoutManager;
import app.panels.CardHandlers.CardPanelRegistry;
import com.dama.gui.gamePanel.TablePanel;
import com.dama.gui._configurations.game.GameBuilder;

import utilities.CommonConstants;

public class GamePlay extends CardPanelRegistry {

    private TablePanel tablePanel;
    
    public GamePlay() {
        initComponents();
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        tablePanel = GameBuilder.createDefaultUnplayableTablePanel();
        tableContainer.add(tablePanel);
        initialize();
        validate();
        repaint();
    }
    
    @Override
    protected void configurePanel() {
        if (!CardLayoutManager.DESIGN_TIME)
            CardLayoutManager.getInstance(MainDirectory.class).registerPanel(this, getName());
    }

    @Override
    public String getPanelName() {
        return "Game Play";
    }
    
    public void setUnPlayableTable() {
        if (tableContainer != null) {
            tablePanel = GameBuilder.createDefaultUnplayableTablePanel();
            tableContainer.removeAll();
            tableContainer.add(tablePanel);
            tableContainer.revalidate();
            tableContainer.repaint();
        }
    }
    
    public void makeNewTable(final TablePanel table) {
        if (tableContainer != null) {
            tablePanel = table;
            tableContainer.removeAll();
            tableContainer.add(tablePanel);
            tableContainer.revalidate();
            tableContainer.repaint();
        }
    }
    
    public TablePanel getTable() {
        return tablePanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        navigator = new app.panels.mainSubPanel.Navigator();
        mainBody = new javax.swing.JPanel();
        tableContainer = new javax.swing.JPanel();
        javax.swing.JPanel settingBG = new javax.swing.JPanel();
        com.dama.gui.controlPanel.SettingContainer settingContainer = new com.dama.gui.controlPanel.SettingContainer();

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
        mainBody.add(tableContainer, java.awt.BorderLayout.CENTER);

        settingBG.setForeground(new java.awt.Color(255, 255, 255));
        settingBG.setFocusable(false);
        settingBG.setMinimumSize(new java.awt.Dimension(350, 10));
        settingBG.setOpaque(false);
        settingBG.setPreferredSize(new java.awt.Dimension(350, 10));
        settingBG.setRequestFocusEnabled(false);
        settingBG.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
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
    private app.panels.mainSubPanel.Navigator navigator;
    private javax.swing.JPanel tableContainer;
    // End of variables declaration//GEN-END:variables
}
