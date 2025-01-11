package component.OptionPane;

import static utilities.CommonConstants.IMAGE_DIRECTORY;
import component.Button.MainButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilities.Configurations;
import utilities.FontManager;
import utilities.FontManager.*;

public class QuitGameConfirmation extends JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     * A return status code - returned if OK button has been pressed
     */
    
    public static final int RET_NO = 0;
    public static final int RET_YES = 1;
    
    public QuitGameConfirmation(final Frame parent, final boolean modal) {
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

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel background = new JPanel();
        JLabel question = new JLabel();
        MainButton yesButton = new MainButton();
        MainButton noButton = new MainButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quit Confirmation");
        setIconImage(Configurations.loadImage(IMAGE_DIRECTORY));
        setBackground(new java.awt.Color(43, 42, 40));
        setMinimumSize(new java.awt.Dimension(280, 180));
        setName("quitDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 180));
        setResizable(false);
        setSize(new java.awt.Dimension(240, 180));
        setType(java.awt.Window.Type.POPUP);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                closeDialog();
            }
        });

        background.setBackground(new java.awt.Color(48, 46, 43));
        background.setForeground(new java.awt.Color(255, 255, 255));
        background.setFocusable(false);
        background.setMaximumSize(new java.awt.Dimension(200, 150));
        background.setMinimumSize(new java.awt.Dimension(200, 150));
        background.setLayout(new java.awt.GridBagLayout());

        question.setFont(FontManager.getFont(
                FontName.POPPINS_REGULAR, FontType.POPPINS, 12));
        question.setForeground(new java.awt.Color(255, 255, 255));
        question.setHorizontalAlignment(SwingConstants.CENTER);
        question.setText("Are you sure you want to quit the game?");
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
        yesButton.requestFocus();
        yesButton.addActionListener((ActionEvent evt) -> {
            yesButtonActionPerformed();
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        background.add(yesButton, gridBagConstraints);

        noButton.setText("No");
        noButton.addActionListener((ActionEvent evt) -> {
            noButtonActionPerformed();
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.5;
        background.add(noButton, gridBagConstraints);

        getContentPane().add(background, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void closeDialog() {
        doClose(RET_NO);
    }

    private void noButtonActionPerformed() {
        doClose(RET_NO);
    }

    private void yesButtonActionPerformed() {
        doClose(RET_YES);
    }
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    private int returnStatus = RET_NO;
}
