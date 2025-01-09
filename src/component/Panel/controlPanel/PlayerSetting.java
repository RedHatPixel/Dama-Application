package component.Panel.controlPanel;

import com.dama.gui.GameInfo;
import component.Panel.CardLayoutManager;
import component.Panel.CardPanelSingleton;
import utilities.FontManager;
import utilities.FontManager.*;

public class PlayerSetting extends CardPanelSingleton {
    
    public PlayerSetting() {
        super();
        initialize();
        initComponents();
        
        title.setFont(FontManager.getFont(FontName.POPPINS_SEMIBOLD, FontType.POPPINS, title.getFont().getSize()));
        forwardButton.setFont(FontManager.getFont(FontName.POPPINS_BLACK, FontType.POPPINS, forwardButton.getFont().getSize()));
        backButton.setFont(FontManager.getFont(FontName.POPPINS_BLACK, FontType.POPPINS, backButton.getFont().getSize()));
                
        validate();
        repaint();
    }
    
    @Override
    protected void setDirectName() {
        if (CardLayoutManager.isInstanced(SettingContainer.class))
            CardLayoutManager.getInstance(SettingContainer.class).registerPanel(this, getDirectName());
    }
    
    @Override
    public String getDirectName() {
        return "Player Setting";
    }
    
    public void setGameInformation() {
        title.setText(GameInfo.getBotttomPlayerName() + " vs " + GameInfo.getTopPlayerName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        title = new javax.swing.JLabel();
        component.ScrollPane.MainScrollPane scrollableContent = new component.ScrollPane.MainScrollPane();
        restartButton = new component.Button.MainButton();
        quitButton = new component.Button.MainButton();
        backButton = new component.Button.MainButton();
        forwardButton = new component.Button.MainButton();
        giveUpButton = new component.Button.MainButton();

        setBackground(new java.awt.Color(38, 37, 34));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 5, 0, new java.awt.Color(29, 28, 26)));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(330, 600));
        setName("Player Setting"); // NOI18N
        setPreferredSize(new java.awt.Dimension(330, 600));
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        title.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Player vs Opponent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 1.0;
        add(title, gridBagConstraints);

        scrollableContent.setBackground(new java.awt.Color(38, 37, 34));
        scrollableContent.setMinimumSize(scrollableContent.getPreferredSize());
        scrollableContent.setOpaque(false);
        scrollableContent.setPreferredSize(new java.awt.Dimension(100, 400));
        scrollableContent.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(scrollableContent, gridBagConstraints);

        restartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/restart.png"))); // NOI18N
        restartButton.setMaximumSize(new java.awt.Dimension(50, 40));
        restartButton.setMinimumSize(new java.awt.Dimension(50, 40));
        restartButton.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        add(restartButton, gridBagConstraints);

        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/exit.png"))); // NOI18N
        quitButton.setMaximumSize(new java.awt.Dimension(50, 40));
        quitButton.setMinimumSize(new java.awt.Dimension(50, 40));
        quitButton.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        add(quitButton, gridBagConstraints);

        backButton.setText("<");
        backButton.setMaximumSize(new java.awt.Dimension(50, 40));
        backButton.setMinimumSize(new java.awt.Dimension(50, 40));
        backButton.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        add(backButton, gridBagConstraints);

        forwardButton.setText(">");
        forwardButton.setMaximumSize(new java.awt.Dimension(50, 40));
        forwardButton.setMinimumSize(new java.awt.Dimension(50, 40));
        forwardButton.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        add(forwardButton, gridBagConstraints);

        giveUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/controls_icon/give_up.png"))); // NOI18N
        giveUpButton.setMaximumSize(new java.awt.Dimension(50, 40));
        giveUpButton.setMinimumSize(new java.awt.Dimension(50, 40));
        giveUpButton.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        add(giveUpButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button.MainButton backButton;
    private component.Button.MainButton forwardButton;
    private component.Button.MainButton giveUpButton;
    private component.Button.MainButton quitButton;
    private component.Button.MainButton restartButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    
}
