package app.dialog;

import app.buttons.MainButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import utilities.FontManager;
import utilities.FontManager.*;
import utilities.ImageFiles;

public class NullConfirmation extends JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    
    private final String message;
    private final String title;
    
    public NullConfirmation(final String message, final String title, final Frame parent, final boolean modal) {
        super(parent, true);
        this.message = message;
        this.title = title;
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
                doClose();
            }
        });
    }
    
    public NullConfirmation(final String message, final String title, final Frame parent) {
        this(message, title, parent, true);
    }

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel background = new JPanel();
        JLabel question = new JLabel();
        MainButton yesButton = new MainButton();

        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(title);
        setIconImage(ImageFiles.DAMA_LOGO);
        setBackground(new java.awt.Color(43, 42, 40));
        setName("quitDialog"); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 180));
        setMinimumSize(getPreferredSize());
        setSize(getPreferredSize());
        setResizable(false);
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
        question.setHorizontalTextPosition(SwingConstants.CENTER);
        question.setText(message);
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

        yesButton.setText("Ok");
        yesButton.requestFocus();
        yesButton.addActionListener((ActionEvent evt) -> {
            yesButtonActionPerformed();
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.NONE;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        background.add(yesButton, gridBagConstraints);

        getContentPane().add(background, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void closeDialog() {
        doClose();
    }

    private void yesButtonActionPerformed() {
        doClose();
    }
    
    private void doClose() {
        setVisible(false);
        dispose();
    }
}
