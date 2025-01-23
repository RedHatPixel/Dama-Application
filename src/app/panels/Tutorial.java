package app.panels;

import app.panels.CardHandlers.CardLayoutManager;
import app.panels.CardHandlers.CardPanelRegistry;
import app.frames.MainFrame;
import utilities.CommonConstants;

public class Tutorial extends CardPanelRegistry {

    public Tutorial() {
        initComponents();
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        initialize();
        validate();
        repaint();
    }
    
    @Override
    protected void configurePanel() {
        if (!CardLayoutManager.DESIGN_TIME)
            CardLayoutManager.getInstance(MainFrame.class).registerPanel(this, getName());
    }

    @Override
    public String getPanelName() {
        return "Tutorial";
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        app.panels.mainSubPanel.Navigator navigator = new app.panels.mainSubPanel.Navigator();
        app.scrollPanes.MainScrollPane mainScrollPane = new app.scrollPanes.MainScrollPane();
        tutorialPage1 = new app.panels.mainSubPanel.TutorialPage();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("Tutorial"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        mainScrollPane.setViewportView(tutorialPage1);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Tutorial");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private app.panels.mainSubPanel.TutorialPage tutorialPage1;
    // End of variables declaration//GEN-END:variables

}
