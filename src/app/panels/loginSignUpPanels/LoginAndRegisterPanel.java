package app.panels.loginSignUpPanels;

import app.customField.CompManager;
import app.customField.TextFieldManager;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.db.model.ModelUser;
import utilities.FontManager;

public class LoginAndRegisterPanel extends JLayeredPane {

    public ModelUser getUser() {
        return user;
    }
    
    private ModelUser user;
    
    public LoginAndRegisterPanel(final ActionListener eventRegister) {
        initComponents();
        initRegister(eventRegister);
        initLogin();
    }
    
    private void initRegister(final ActionListener eventRegister) {
        final String nameHint = "Name";
        final String emailHint = "Email";
        final String passwordHint = "Password";
        CompManager.setPoppinsFont(titleAcc, FontManager.FontName.POPPINS_BLACK);
        TextFieldManager.setHint(userName, userName.getForeground(), nameHint);
        TextFieldManager.setHint(userEmail, userEmail.getForeground(), emailHint);
        TextFieldManager.setHint(userPassword, userPassword.getForeground(), passwordHint);
        TextFieldManager.setPasswordVisionToggle(userPassword, toggleVision, passwordHint);
        TextFieldManager.setFont(userName);
        TextFieldManager.setFont(userEmail);
        TextFieldManager.setFont(userPassword);
        signUpButton.addActionListener(eventRegister);
        signUpButton.addActionListener((ActionEvent e) -> { 
            final String name = TextFieldManager.isHint(userName, nameHint) ?
                                "" : userName.getText().trim();
            final String email = TextFieldManager.isHint(userEmail, emailHint) ?
                                "" : userEmail.getText().trim();
            final String password = TextFieldManager.isHint(userPassword, passwordHint) ?
                                "" : String.valueOf(userPassword.getPassword());
            user = new ModelUser(0, name, email, password);
        });
    }
    
    private void initLogin() {
        CompManager.setPoppinsFont(titleSignin, FontManager.FontName.POPPINS_BLACK);
        TextFieldManager.setHint(userEmail1, userEmail1.getForeground(), "Email");
        TextFieldManager.setHint(userPassword1, userPassword1.getForeground(), "Password");
        TextFieldManager.setPasswordVisionToggle(userPassword1, toggleVision1, "Password");
        TextFieldManager.setFont(userEmail1);
        TextFieldManager.setFont(userPassword1);
        CompManager.setPoppinsFont(forgetButton, FontManager.FontName.POPPINS_LIGHT);
    }
    
    public void showRegister(final boolean show) {
        final CardLayout card = (CardLayout) this.getLayout();
        if (show) {
            card.show(this, register.getName());
        } else {
            card.show(this, login.getName());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        register = new javax.swing.JPanel();
        titleAcc = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        userEmail = new javax.swing.JTextField();
        userPassword = new javax.swing.JPasswordField();
        javax.swing.JLabel nameLabel = new javax.swing.JLabel();
        javax.swing.JLabel emailLabel = new javax.swing.JLabel();
        javax.swing.JLabel passwordLabel = new javax.swing.JLabel();
        toggleVision = new javax.swing.JButton();
        signUpButton = new app.buttons.MainButton();
        javax.swing.JLabel rightSpace = new javax.swing.JLabel();
        javax.swing.JLabel leftSpace = new javax.swing.JLabel();
        login = new javax.swing.JPanel();
        titleSignin = new javax.swing.JLabel();
        javax.swing.JLabel emailLabel1 = new javax.swing.JLabel();
        userEmail1 = new javax.swing.JTextField();
        javax.swing.JLabel passwordLabel1 = new javax.swing.JLabel();
        userPassword1 = new javax.swing.JPasswordField();
        toggleVision1 = new javax.swing.JButton();
        loginButton = new app.buttons.MainButton();
        javax.swing.JLabel leftSpace1 = new javax.swing.JLabel();
        javax.swing.JLabel rightSpace1 = new javax.swing.JLabel();
        forgetButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(48, 46, 43));
        setMinimumSize(new java.awt.Dimension(400, 450));
        setName("LoginAndRegister"); // NOI18N
        setLayout(new java.awt.CardLayout());

        register.setBackground(new java.awt.Color(38, 37, 34));
        register.setMinimumSize(new java.awt.Dimension(400, 450));
        register.setName("register"); // NOI18N
        register.setPreferredSize(new java.awt.Dimension(400, 450));
        register.setLayout(new java.awt.GridBagLayout());

        titleAcc.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        titleAcc.setForeground(new java.awt.Color(255, 255, 255));
        titleAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleAcc.setText("Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        register.add(titleAcc, gridBagConstraints);

        userName.setBackground(new java.awt.Color(38, 37, 34));
        userName.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        userName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userName.setToolTipText("Enter your username");
        userName.setActionCommand("<Not Set>");
        userName.setAutoscrolls(false);
        userName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userName.setCaretColor(new java.awt.Color(102, 102, 102));
        userName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userName.setMinimumSize(new java.awt.Dimension(280, 40));
        userName.setOpaque(true);
        userName.setPreferredSize(new java.awt.Dimension(280, 40));
        userName.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userName.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(userName, gridBagConstraints);

        userEmail.setBackground(new java.awt.Color(38, 37, 34));
        userEmail.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userEmail.setForeground(new java.awt.Color(255, 255, 255));
        userEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userEmail.setToolTipText("Enter your email");
        userEmail.setActionCommand("<Not Set>");
        userEmail.setAutoscrolls(false);
        userEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userEmail.setCaretColor(new java.awt.Color(102, 102, 102));
        userEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userEmail.setMinimumSize(new java.awt.Dimension(280, 40));
        userEmail.setOpaque(true);
        userEmail.setPreferredSize(new java.awt.Dimension(280, 40));
        userEmail.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userEmail.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(userEmail, gridBagConstraints);

        userPassword.setBackground(new java.awt.Color(38, 37, 34));
        userPassword.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userPassword.setForeground(new java.awt.Color(255, 255, 255));
        userPassword.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userPassword.setToolTipText("Enter your password");
        userPassword.setAutoscrolls(false);
        userPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userPassword.setMinimumSize(new java.awt.Dimension(280, 40));
        userPassword.setName(""); // NOI18N
        userPassword.setOpaque(true);
        userPassword.setPreferredSize(new java.awt.Dimension(280, 40));
        userPassword.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userPassword.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(userPassword, gridBagConstraints);

        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/username.png"))); // NOI18N
        nameLabel.setLabelFor(userName);
        nameLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nameLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        nameLabel.setMinimumSize(new java.awt.Dimension(40, 40));
        nameLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        nameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(nameLabel, gridBagConstraints);

        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/email.png"))); // NOI18N
        emailLabel.setLabelFor(userEmail);
        emailLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        emailLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        emailLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        emailLabel.setMinimumSize(new java.awt.Dimension(40, 40));
        emailLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        emailLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(emailLabel, gridBagConstraints);

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/password.png"))); // NOI18N
        passwordLabel.setLabelFor(userPassword);
        passwordLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        passwordLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        passwordLabel.setMaximumSize(new java.awt.Dimension(40, 40));
        passwordLabel.setMinimumSize(new java.awt.Dimension(40, 40));
        passwordLabel.setPreferredSize(new java.awt.Dimension(40, 40));
        passwordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(passwordLabel, gridBagConstraints);

        toggleVision.setBackground(new java.awt.Color(38, 37, 34));
        toggleVision.setForeground(new java.awt.Color(255, 255, 255));
        toggleVision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/visible-eye.png"))); // NOI18N
        toggleVision.setToolTipText("toggle password vision");
        toggleVision.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        toggleVision.setContentAreaFilled(false);
        toggleVision.setDefaultCapable(false);
        toggleVision.setFocusPainted(false);
        toggleVision.setFocusable(false);
        toggleVision.setMaximumSize(new java.awt.Dimension(40, 40));
        toggleVision.setMinimumSize(new java.awt.Dimension(40, 40));
        toggleVision.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleVision.setRolloverEnabled(false);
        toggleVision.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        register.add(toggleVision, gridBagConstraints);

        signUpButton.setText("SIGN UP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 20, 20);
        register.add(signUpButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        register.add(rightSpace, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        register.add(leftSpace, gridBagConstraints);

        add(register, "register");

        login.setBackground(new java.awt.Color(38, 37, 34));
        login.setMinimumSize(new java.awt.Dimension(400, 450));
        login.setName("login"); // NOI18N
        login.setLayout(new java.awt.GridBagLayout());

        titleSignin.setFont(new java.awt.Font("Segoe UI Black", 1, 30)); // NOI18N
        titleSignin.setForeground(new java.awt.Color(255, 255, 255));
        titleSignin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSignin.setText("Sign In");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        login.add(titleSignin, gridBagConstraints);

        emailLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/email.png"))); // NOI18N
        emailLabel1.setLabelFor(userEmail1);
        emailLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        emailLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        emailLabel1.setMaximumSize(new java.awt.Dimension(40, 40));
        emailLabel1.setMinimumSize(new java.awt.Dimension(40, 40));
        emailLabel1.setPreferredSize(new java.awt.Dimension(40, 40));
        emailLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailLabel1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        login.add(emailLabel1, gridBagConstraints);

        userEmail1.setBackground(new java.awt.Color(38, 37, 34));
        userEmail1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userEmail1.setForeground(new java.awt.Color(255, 255, 255));
        userEmail1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userEmail1.setToolTipText("Enter your email");
        userEmail1.setActionCommand("<Not Set>");
        userEmail1.setAutoscrolls(false);
        userEmail1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userEmail1.setCaretColor(new java.awt.Color(102, 102, 102));
        userEmail1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        userEmail1.setMinimumSize(new java.awt.Dimension(280, 40));
        userEmail1.setOpaque(true);
        userEmail1.setPreferredSize(new java.awt.Dimension(280, 40));
        userEmail1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userEmail1.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        login.add(userEmail1, gridBagConstraints);

        passwordLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/password.png"))); // NOI18N
        passwordLabel1.setLabelFor(userPassword1);
        passwordLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        passwordLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        passwordLabel1.setMaximumSize(new java.awt.Dimension(40, 40));
        passwordLabel1.setMinimumSize(new java.awt.Dimension(40, 40));
        passwordLabel1.setPreferredSize(new java.awt.Dimension(40, 40));
        passwordLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordLabel1MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        login.add(passwordLabel1, gridBagConstraints);

        userPassword1.setBackground(new java.awt.Color(38, 37, 34));
        userPassword1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        userPassword1.setForeground(new java.awt.Color(255, 255, 255));
        userPassword1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        userPassword1.setToolTipText("Enter your password");
        userPassword1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        userPassword1.setMinimumSize(new java.awt.Dimension(280, 40));
        userPassword1.setName(""); // NOI18N
        userPassword1.setOpaque(true);
        userPassword1.setPreferredSize(new java.awt.Dimension(280, 40));
        userPassword1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        userPassword1.setSelectionColor(new java.awt.Color(48, 46, 43));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        login.add(userPassword1, gridBagConstraints);

        toggleVision1.setBackground(new java.awt.Color(38, 37, 34));
        toggleVision1.setForeground(new java.awt.Color(255, 255, 255));
        toggleVision1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/registers_icon/visible-eye.png"))); // NOI18N
        toggleVision1.setToolTipText("toggle password vision");
        toggleVision1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        toggleVision1.setContentAreaFilled(false);
        toggleVision1.setDefaultCapable(false);
        toggleVision1.setFocusPainted(false);
        toggleVision1.setFocusable(false);
        toggleVision1.setMaximumSize(new java.awt.Dimension(40, 40));
        toggleVision1.setMinimumSize(new java.awt.Dimension(40, 40));
        toggleVision1.setPreferredSize(new java.awt.Dimension(40, 40));
        toggleVision1.setRolloverEnabled(false);
        toggleVision1.setVerifyInputWhenFocusTarget(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        login.add(toggleVision1, gridBagConstraints);

        loginButton.setText("SIGN IN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        login.add(loginButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        login.add(leftSpace1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        login.add(rightSpace1, gridBagConstraints);

        forgetButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        forgetButton.setForeground(new java.awt.Color(255, 255, 255));
        forgetButton.setText("Forget your password?");
        forgetButton.setBorder(null);
        forgetButton.setBorderPainted(false);
        forgetButton.setContentAreaFilled(false);
        forgetButton.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 20);
        login.add(forgetButton, gridBagConstraints);

        add(login, "login");
    }// </editor-fold>//GEN-END:initComponents

    private void nameLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLabelMouseClicked
        userName.requestFocus();
    }//GEN-LAST:event_nameLabelMouseClicked

    private void emailLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailLabelMouseClicked
        userEmail.requestFocus();
    }//GEN-LAST:event_emailLabelMouseClicked

    private void passwordLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordLabelMouseClicked
        userPassword.requestFocus();
    }//GEN-LAST:event_passwordLabelMouseClicked

    private void emailLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailLabel1MouseClicked
        userEmail1.requestFocus();
    }//GEN-LAST:event_emailLabel1MouseClicked

    private void passwordLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordLabel1MouseClicked
        userPassword1.requestFocus();
    }//GEN-LAST:event_passwordLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton forgetButton;
    private javax.swing.JPanel login;
    private app.buttons.MainButton loginButton;
    private javax.swing.JPanel register;
    private app.buttons.MainButton signUpButton;
    private javax.swing.JLabel titleAcc;
    private javax.swing.JLabel titleSignin;
    private javax.swing.JButton toggleVision;
    private javax.swing.JButton toggleVision1;
    private javax.swing.JTextField userEmail;
    private javax.swing.JTextField userEmail1;
    private javax.swing.JTextField userName;
    private javax.swing.JPasswordField userPassword;
    private javax.swing.JPasswordField userPassword1;
    // End of variables declaration//GEN-END:variables
}
