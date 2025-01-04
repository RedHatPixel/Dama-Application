package component.OptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class QuitConfirmation extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_NO = 0;
    public static final int RET_YES = 1;
    
    private static final int FONT_SIZE = 14;
    public boolean clicked = false;

    /**
     * Creates new form QuitConfirmation
     * @param parent
     * @param modal
     */
    public QuitConfirmation(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setVisible(true);

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_NO);
            }
        });
    }
    
    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel background = new javax.swing.JPanel();
        question = new javax.swing.JLabel();
        yesButton = new component.Button.MainButton();
        noButton = new component.Button.MainButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quit Confirmation");
        setBackground(new java.awt.Color(43, 42, 40));
        setMinimumSize(new java.awt.Dimension(240, 180));
        setName("quitDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(240, 180));
        setResizable(false);
        setSize(new java.awt.Dimension(240, 180));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        background.setBackground(new java.awt.Color(48, 46, 43));
        background.setForeground(new java.awt.Color(255, 255, 255));
        background.setFocusable(false);
        background.setMaximumSize(new java.awt.Dimension(200, 150));
        background.setMinimumSize(new java.awt.Dimension(200, 150));
        background.setLayout(new java.awt.GridBagLayout());

        question.setFont(getFont());
        question.setForeground(new java.awt.Color(255, 255, 255));
        question.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        question.setText("Are you sure you want to quit?");
        question.setMaximumSize(new java.awt.Dimension(172, 70));
        question.setMinimumSize(new java.awt.Dimension(172, 70));
        question.setPreferredSize(new java.awt.Dimension(172, 70));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        background.add(question, gridBagConstraints);

        yesButton.setText("Yes");
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        background.add(yesButton, gridBagConstraints);

        noButton.setText("No");
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        background.add(noButton, gridBagConstraints);

        getContentPane().add(background, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_NO);
    }//GEN-LAST:event_closeDialog

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        doClose(RET_NO);
    }//GEN-LAST:event_noButtonActionPerformed

    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
    doClose(RET_YES);
    }//GEN-LAST:event_yesButtonActionPerformed
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Button.MainButton noButton;
    private javax.swing.JLabel question;
    private component.Button.MainButton yesButton;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_NO;
}
