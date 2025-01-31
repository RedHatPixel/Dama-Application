package utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import utilities.FontManager;
import utilities.ImageFiles;

public class TextFieldManager {

    private static final Color HINT_COLOR = new Color(169, 169, 169);
    
    public static void setHint(final JTextField textField, 
                               final Color defaultColor,
                               final String hint) {
        final String undetectableHint = hint + "\u00A0";
        for (final FocusListener fl : textField.getFocusListeners()) {
            if (fl instanceof HintFocusListener)
                textField.removeFocusListener(fl);
        }
        textField.setText(undetectableHint);
        textField.setForeground(HINT_COLOR);
        textField.addFocusListener(new HintFocusListener(undetectableHint, defaultColor, textField));
    }

    public static void setHint(final JPasswordField passwordField, 
                               final Color defaultColor,
                               final String hint,
                               final JButton toggleButton) {
        final String undetectableHint = hint + "\u00A0";
        for (final FocusListener fl : passwordField.getFocusListeners()) {
            if (fl instanceof HintFocusListener)
                passwordField.removeFocusListener(fl);
        }
        passwordField.setEchoChar((char) 0);
        passwordField.setText(undetectableHint);
        passwordField.setForeground(HINT_COLOR);
        passwordField.addFocusListener(new HintFocusListener(undetectableHint, defaultColor, passwordField, toggleButton));
    }
    
    public static void setPasswordVisionToggle(final JPasswordField passwordField, final JButton toggleButton, final String hint) {
        toggleButton.addActionListener(new ActionListener() {
            private boolean passwordVisible = false;
            private final String undetectableHint = hint + "\u00A0";
            
            @Override
            public void actionPerformed(ActionEvent e) {
                final String passwordText = String.valueOf(passwordField.getPassword());
                if (passwordVisible) {
                    if (!passwordText.equals(undetectableHint)) passwordField.setEchoChar('*');
                    else passwordField.setEchoChar((char) 0);
                    passwordVisible = false;
                } else {
                    passwordField.setEchoChar((char) 0);
                    passwordVisible = true;
                }
                toggleButton.setIcon(passwordVisible ? ImageFiles.VISIBLE : ImageFiles.INVISIBLE);
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
    
    private static class HintFocusListener extends FocusAdapter {
        private final String undetectableHint;
        private final Color defaultColor;
        private final JTextField textField;
        private final JPasswordField passwordField;
        private final JButton toggleButton;

        public HintFocusListener(final String undetectableHint, 
                                 final Color defaultColor, 
                                 final JTextField textField) {
            this.undetectableHint = undetectableHint;
            this.defaultColor = defaultColor;
            this.textField = textField;
            this.passwordField = null;
            this.toggleButton = null;
        }
        
        public HintFocusListener(final String undetectableHint, 
                                 final Color defaultColor, 
                                 final JPasswordField passwordField,
                                 final JButton toggleButton) {
            this.undetectableHint = undetectableHint;
            this.defaultColor = defaultColor;
            this.textField = null;
            this.passwordField = passwordField;
            this.toggleButton = toggleButton;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (textField != null) {
                if (textField.getText().equals(undetectableHint)) {
                    textField.setText("");
                    textField.setForeground(defaultColor);
                }
            } else if (passwordField != null) {
                if (String.valueOf(passwordField.getPassword()).equals(undetectableHint)) {
                    passwordField.setText("");
                    passwordField.setForeground(defaultColor);
                    final boolean passwordVisible = toggleButton.getIcon().equals(ImageFiles.VISIBLE);
                    passwordField.setEchoChar(passwordVisible ? (char) 0 : '*');
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textField != null) {
                if (textField.getText().isEmpty()) {
                    textField.setText(undetectableHint);
                    textField.setForeground(HINT_COLOR);
                }
            } else if (passwordField != null) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(undetectableHint);
                    passwordField.setForeground(HINT_COLOR);
                    passwordField.setEchoChar((char) 0);
                }
            }
        }
    }
}
