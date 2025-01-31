package app.frames;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.core.animation.timing.Interpolator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
import org.jdesktop.core.animation.timing.sources.ScheduledExecutorTimingSource;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import app.panels.loginSignUpPanels.LoginAndRegisterPanel;
import app.panels.loginSignUpPanels.Message;
import app.panels.loginSignUpPanels.PanelCover;
import app.panels.loginSignUpPanels.PanelLoading;
import app.panels.loginSignUpPanels.PanelLoginContinue;
import app.panels.loginSignUpPanels.PanelReconnection;
import app.panels.loginSignUpPanels.PanelVerifyCode;
import app.dialog.QuitConfirmation;
import com.db.connection.DatabaseConnection;
import com.db.model.ModelLogin;
import com.db.model.ModelMessage;
import com.db.model.ModelProfile;
import com.db.model.ModelUser;
import com.db.service.ServiceMail;
import com.db.service.ServiceUser;
import com.db.sounds.NotificationManager;
import com.db.token.SessionManager;
import com.db.validator.EmailValidator;
import com.db.validator.NameValidator;
import com.db.validator.PasswordValidator;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import utilities.ImageFiles;

public class LoginSignUpFrame extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###");
    private MigLayout layout;
    private PanelCover panelCover;
    private LoginAndRegisterPanel loginAndRegisterPanel;
    private PanelVerifyCode verifyCode;
    private PanelLoading loading;
    private PanelReconnection reconnection;
    private PanelLoginContinue continues;
    private ScheduledExecutorService scheduler;
    private ServiceUser service;
    private boolean isLogin;
    
    public LoginSignUpFrame() {
        initComponents();
        init();
        startConnectionCheck();
    }
    
    private void init() {
        setVisible(true);
        setIconImage(ImageFiles.DAMA_LOGO);
        service = new ServiceUser();
        layout = new MigLayout("fill, insets 0");
        panelCover = new PanelCover();
        verifyCode = new PanelVerifyCode();
        continues = new PanelLoginContinue();
        final ActionListener eventRegister = (ActionEvent e) -> { register(); };
        final ActionListener eventLogin = (ActionEvent e) -> { login(); };
        loginAndRegisterPanel = new LoginAndRegisterPanel(eventRegister, eventLogin);
        loading = new PanelLoading();
        reconnection = new PanelReconnection();
        final TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(final Animator source, final double fraction) {
                double fractionCover;
                double fractionLogin;
                double size = 40;
                if (fraction <= .5f) size += fraction * 30;
                else size += 30 - fraction * 30;
                if (isLogin) {
                    LoginSignUpFrame.this.setTitle("Login");
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= .5f) {
                        panelCover.registerRight((1f - fraction) * 100);
                    } else {
                        panelCover.loginRight(fraction * 100);
                    }
                } else {
                    LoginSignUpFrame.this.setTitle("Sign Up");
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= .5f) {
                        panelCover.registerLeft(fraction * 100);
                    } else {
                        panelCover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= .5f) {
                    loginAndRegisterPanel.showRegister(isLogin);
                }
                fractionCover = Double.parseDouble(df.format(fractionCover));
                fractionLogin = Double.parseDouble(df.format(fractionLogin));
                layout.setComponentConstraints(panelCover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegisterPanel, "width 60%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }
            
            @Override
            public void end(final Animator source) {
                isLogin = !isLogin;
            }
            
        };
        final TimingSource timingSource = new ScheduledExecutorTimingSource(1000, TimeUnit.NANOSECONDS);
        final Interpolator interpolator = new AccelerationInterpolator(.5f, .5f);
        final Animator.Builder builder = new Animator.Builder(timingSource);
        builder.addTarget(target);
        builder.setDisposeTimingSource(false);
        builder.setDebugName("SlidingAnimation");
        builder.setRepeatBehavior(Animator.RepeatBehavior.REVERSE);
        builder.setInterpolator(interpolator);
        builder.setDuration(1000, TimeUnit.MILLISECONDS);
        timingSource.init();
        final Animator animation = builder.build();
        bg.setLayout(layout);
        bg.setLayer(reconnection, JLayeredPane.POPUP_LAYER);
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.setLayer(continues, JLayeredPane.POPUP_LAYER);
        bg.add(continues, "pos 0 0 100% 100%");
        bg.add(reconnection, "pos 0 0 100% 100%");
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(panelCover, "width 40%, pos 0al 0 n 100%");
        bg.add(loginAndRegisterPanel, "width 60%, pos 1al 0 n 100%");
        panelCover.addEvent((ActionEvent e) -> {
            if (!animation.isRunning()) {
                animation.start();
            }
        });
        verifyCode.addEventButtonOK((ActionEvent e) -> {
            try {
                final ModelUser user = loginAndRegisterPanel.getUser();
                if (service.verifyCodeWithUser(user.getUserID(), verifyCode.getInputCode())) {
                    service.doneVerify(user.getUserID());
                    verifyCode.setVisible(false);
                    showMessage(Message.MessageType.SUCCESS, "Register Success");
                    NotificationManager.Sounds.CORRECT_NOTIF.play();
                } else {
                    showMessage(Message.MessageType.ERROR, "Verification code was incorrect");
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                }
            } catch (SQLException x) {
                showMessage(Message.MessageType.ERROR, "Error");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            }
        });
        verifyCode.addEventButtonCancel((ActionEvent e) -> {
            try {
                final ModelUser user = loginAndRegisterPanel.getUser();
                service.deleteNonVerified(user.getUserID());
                showMessage(Message.MessageType.ERROR, "Register was incomplete");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } catch (SQLException x) {
                showMessage(Message.MessageType.ERROR, "Error");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            }
            
        });
        continues.addEventButtonContinue((ActionEvent e) -> {
            final ModelProfile profile = continues.getProfile();
            if (profile != null) {
                new MainFrame(profile);
                SessionManager.saveSession(profile);
                try {
                    service.deleteAllNonVerified();
                } catch (SQLException x) {
                    System.err.println("Something went wrong on database -> LoginSignUpFrame in line 181");
                    System.err.println("Error: " + x.getMessage());
                }
                dispose();
            } else {
                showMessage(Message.MessageType.ERROR, "Account was not found");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            }
        });
        if (!NotificationManager.Sounds.BACKGROUND_MUSIC.isPlaying()) 
            NotificationManager.Sounds.BACKGROUND_MUSIC.play();
    }
    
    private void login() {
        final ModelLogin loginData = loginAndRegisterPanel.getLogin();
        loginData.setEmail(EmailValidator.normalizeEmail(loginData.getEmail()));
        try {
            if (!EmailValidator.isValidEmail(loginData.getEmail())) {
                showMessage(Message.MessageType.ERROR, EmailValidator.getMessage());
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else if (loginData.getPassword().isBlank()) {
                showMessage(Message.MessageType.ERROR, "Password is empty");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else {
                final ModelUser user = service.login(loginData);
                if (user != null) {
                    if (continues.setUserProfile(user)) {
                        continues.setVisible(true);
                        showMessage(Message.MessageType.SUCCESS, "Login Successfully");
                        NotificationManager.Sounds.CORRECT_NOTIF.play();
                    } else {
                        showMessage(Message.MessageType.ERROR, "Connection to profile interrupted");
                        NotificationManager.Sounds.WRONG_NOTIF.play();
                    }
                } else {
                    showMessage(Message.MessageType.ERROR, "Email or Password is incorrect");
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                }
            }
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
            NotificationManager.Sounds.WRONG_NOTIF.play();
            loginAndRegisterPanel.resetLogin();
        }
    }
    
    private void register() {
        final ModelUser user = loginAndRegisterPanel.getUser();
        user.setEmail(EmailValidator.normalizeEmail(user.getEmail()));
        try {
            if (!NameValidator.isValidUserName(user.getUserName())) {
                showMessage(Message.MessageType.ERROR, NameValidator.getMessage());
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else if (!EmailValidator.isValidEmail(user.getEmail())) {
                showMessage(Message.MessageType.ERROR, EmailValidator.getMessage());
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else if (!PasswordValidator.isValidPassword(user.getPassword())) {
                showMessage(Message.MessageType.ERROR, PasswordValidator.getMessage());
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else if (service.checkDuplicateUser(user.getUserName())) {
                showMessage(Message.MessageType.ERROR, "User name already exist");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else if (service.checkDuplicateEmail(user.getEmail())) {
                showMessage(Message.MessageType.ERROR, "Email already exist");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            } else {
                service.insertUser(user);
                sendMail(user);
            }
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Error Register");
            NotificationManager.Sounds.WRONG_NOTIF.play();
            loginAndRegisterPanel.resetRegister();
        }
    }
    
    private void sendMail(final ModelUser user) {
        new Thread(() -> {
            try {
                loading.setVisible(true);
                final ModelMessage msg = new ServiceMail().sendMail(user.getEmail(), user.getVerifyCode());
                if (msg.isSuccess()) {
                    loading.setVisible(false);
                    verifyCode.setVisible(true);
                } else {
                    loading.setVisible(false);
                    showMessage(Message.MessageType.ERROR, msg.getMessage());
                    NotificationManager.Sounds.WRONG_NOTIF.play();
                }
            } catch (Exception e) {
                showMessage(Message.MessageType.ERROR, "Error");
                NotificationManager.Sounds.WRONG_NOTIF.play();
            }
            loginAndRegisterPanel.resetRegister();
        }).start();
    }
    
    private void showMessage(final Message.MessageType messageType, final String message) {
        final Message msg = new Message();
        msg.showMessage(messageType, message);
        final TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin(final Animator source) {
                if (!msg.isShow()) {
                    bg.add(msg, "pos 0.5al -30", 0);
                    msg.setVisible(true);
                    bg.repaint();
                }
            }
            
            @Override
            public void timingEvent(final Animator source, final double fraction) {
                double frac;
                if (msg.isShow()) {
                    frac = 40 * (1f - fraction);
                } else {
                    frac = 40 * fraction;
                }
                layout.setComponentConstraints(msg, "pos 0.5al " + (int) (frac - 30));
                bg.repaint();
                bg.revalidate();
            }
            
            @Override
            public void end(final Animator source) {
                if (msg.isShow()) {
                    bg.remove(msg);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    msg.setShow(true);
                }
            }
        };
        final TimingSource timingSource = new ScheduledExecutorTimingSource(1000, TimeUnit.NANOSECONDS);
        final Interpolator interpolator = new AccelerationInterpolator(.5f, .5f);
        final Animator.Builder builder = new Animator.Builder(timingSource);
        builder.addTarget(target);
        builder.setDebugName("DropAndUpAnimation");
        builder.setRepeatBehavior(Animator.RepeatBehavior.REVERSE);
        builder.setInterpolator(interpolator);
        builder.setDuration(300, TimeUnit.MILLISECONDS);
        timingSource.init();
        final Animator animation = builder.build();
        animation.start();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                animation.start();
            } catch (InterruptedException e) {
                System.err.println("Something went wrong -> Animation line 173");
                System.err.println("Error: " + e.getMessage());
            }
        }).start();
    }
    
    public void closeWindow() {
        final int status = new QuitConfirmation(
                "Are you sure you want to quit?", 
                "Quit Confirmation", this).getReturnStatus();
        if (status == QuitConfirmation.RET_YES) {
            dispose();
            new MainFrame(null);
        }
    }
    
    private void startConnectionCheck() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                boolean isConnected = service.isConnectionValid();
                SwingUtilities.invokeLater(() -> {
                    reconnection.setVisible(continues.isVisible() ? false : !isConnected);
                });
                if (!isConnected) {
                    DatabaseConnection.getInstance().reconnect(service);
                }
            }, 0, 5, TimeUnit.SECONDS);
        }
    }

    private void stopConnectionCheck() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }
    
    @Override
    public void dispose() {
        super.dispose();
        stopConnectionCheck();
        NotificationManager.Sounds.BACKGROUND_MUSIC.stop();
        try {
            if (service.isConnectionValid())
                service.deleteAllNonVerified();
        } catch (SQLException e) {
            System.err.println("Something went wrong while closing -> LoginSignUpFrame in line 301");
            System.err.println("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(38, 37, 34));
        setMinimumSize(new java.awt.Dimension(750, 500));
        setName("loginSignUp"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bg.setBackground(new java.awt.Color(38, 37, 34));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);
        bg.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        closeWindow();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
