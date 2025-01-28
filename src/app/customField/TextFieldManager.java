package app.customField;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import utilities.FontManager;

public class TextFieldManager {

    private static final Color HINT_COLOR = new Color(169, 169, 169);

    public static void setHint(final JTextField textField, 
                               final Color defaultColor,
                               final String hint) {
        final String undetectableHint = hint + "\u00A0";
        textField.setText(undetectableHint);
        textField.setForeground(HINT_COLOR);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (textField.getText().equals(undetectableHint)) {
                    textField.setText("");
                    textField.setForeground(defaultColor);
                }
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(undetectableHint);
                    textField.setForeground(HINT_COLOR);
                }
            }
        });
    }

    public static void setHint(final JPasswordField passwordField, 
                               final Color defaultColor,
                               final String hint) {
        final String undetectableHint = hint + "\u00A0";
        passwordField.setEchoChar((char) 0);
        passwordField.setText(undetectableHint);
        passwordField.setForeground(HINT_COLOR);

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals(undetectableHint)) {
                    passwordField.setText("");
                    passwordField.setForeground(defaultColor);
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(final FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(undetectableHint);
                    passwordField.setForeground(HINT_COLOR);
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
    }
    
    public static void setPasswordVisionToggle(final JPasswordField passwordField, final JButton toggleButton, final String hint) {
        toggleButton.addActionListener(new ActionListener() {
            private boolean passwordVisible = false;
            private final String undetectableHint = hint + "\u00A0";

            @Override
            public void actionPerformed(ActionEvent e) {
                final String passwordText = String.valueOf(passwordField.getPassword());
                
                if (passwordText.equals(undetectableHint)) {
                    passwordField.setEchoChar((char) 0);
                    passwordVisible = true;
                } else if (passwordVisible) {
                    passwordField.setEchoChar('*');
                    passwordVisible = false;
                } else {
                    passwordField.setEchoChar((char) 0);
                    passwordVisible = true;
                }
            }
        });
    }
    
    public static boolean isHint(final JPasswordField comp, final String hint) {
        return String.valueOf(comp.getPassword()).trim().equals(hint + "\u00A0");
    }
    
    public static boolean isHint(final JTextField comp, final String hint) {
        return comp.getText().trim().equals(hint + "\u00A0");
    }
    
    public static void setFont(final Component comp) {
        comp.setFont(FontManager.getFont(
                        FontManager.FontName.POPPINS_MEDIUM, 
                FontManager.FontType.POPPINS, comp.getFont().getSize()));
    }
    
    // Constructor: Prevent Instantiation
    private TextFieldManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
}
