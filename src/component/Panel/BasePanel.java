package component.Panel;

import component.Panel.CardHandlers.CardPanelRegistry;
import utilities.CommonConstants;

public abstract class BasePanel extends CardPanelRegistry {

    public BasePanel() {
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
        System.err.println("An abstract class should not be initialize.");
    }

    @Override
    public String getPanelName() {
        return "Base Panel";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("BasePanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        getAccessibleContext().setAccessibleName("BasePanel");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
