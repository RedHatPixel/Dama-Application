package component.Panel;

import utilities.CommonConstants;
import utilities.Direction;
import utilities.Directory;

public class Tutorial extends javax.swing.JPanel implements Direction {

    public Tutorial() {
        initComponents();
        
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setDirectName();
        
        this.revalidate();
        this.repaint();
    }

    @Override
    public void setDirectName() {
        this.setName(Directory.Panel.TUTORIAL.getName());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        component.Panel.subPanel.Navigator navigator = new component.Panel.subPanel.Navigator();
        component.ScrollPane.MainScrollPane mainScrollPane = new component.ScrollPane.MainScrollPane();
        javax.swing.JPanel mainBody = new javax.swing.JPanel();
        Introduction = new javax.swing.JPanel();

        setBackground(new java.awt.Color(48, 46, 43));
        setForeground(new java.awt.Color(255, 255, 255));
        setFocusable(false);
        setMaximumSize(new java.awt.Dimension(1200, 700));
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("Tutorial"); // NOI18N
        setPreferredSize(new java.awt.Dimension(950, 600));
        setLayout(new java.awt.BorderLayout());
        add(navigator, java.awt.BorderLayout.WEST);

        mainBody.setBackground(new java.awt.Color(48, 46, 43));
        mainBody.setForeground(new java.awt.Color(255, 255, 255));
        mainBody.setName(""); // NOI18N
        mainBody.setPreferredSize(new java.awt.Dimension(540, 550));
        mainBody.setLayout(new javax.swing.BoxLayout(mainBody, javax.swing.BoxLayout.PAGE_AXIS));

        Introduction.setBackground(new java.awt.Color(48, 46, 43));
        Introduction.setForeground(new java.awt.Color(255, 255, 255));
        Introduction.setOpaque(false);

        javax.swing.GroupLayout IntroductionLayout = new javax.swing.GroupLayout(Introduction);
        Introduction.setLayout(IntroductionLayout);
        IntroductionLayout.setHorizontalGroup(
            IntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        IntroductionLayout.setVerticalGroup(
            IntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mainBody.add(Introduction);

        mainScrollPane.setViewportView(mainBody);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("Tutorial");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Introduction;
    // End of variables declaration//GEN-END:variables

}
