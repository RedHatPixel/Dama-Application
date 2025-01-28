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
import app.panels.loginSignUpPanels.PanelVerifyCode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import javax.swing.JLayeredPane;
import com.db.model.ModelUser;

import utilities.ImageFiles;

public class LoginSignUpFrame extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCover panelCover;
    private LoginAndRegisterPanel loginAndRegisterPanel;
    private PanelVerifyCode verifyCode;
    private PanelLoading loading;
    private boolean isLogin;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    
    public LoginSignUpFrame() {
        initComponents();
        init();
    }
    
    private void init() {
        setVisible(true);
        setIconImage(ImageFiles.DAMA_LOGO);
        layout = new MigLayout("fill, insets 0");
        panelCover = new PanelCover();
        verifyCode = new PanelVerifyCode();
        final ActionListener eventRegister = (ActionEvent e) -> {
            register();
        };
        loginAndRegisterPanel = new LoginAndRegisterPanel(eventRegister);
        loading = new PanelLoading();
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
        bg.setLayer(loading, JLayeredPane.POPUP_LAYER);
        bg.setLayer(verifyCode, JLayeredPane.POPUP_LAYER);
        bg.add(loading, "pos 0 0 100% 100%");
        bg.add(verifyCode, "pos 0 0 100% 100%");
        bg.add(panelCover, "width 40%, pos 0al 0 n 100%");
        bg.add(loginAndRegisterPanel, "width 60%, pos 1al 0 n 100%");
        panelCover.addEvent((ActionEvent e) -> {
            if (!animation.isRunning()) {
                animation.start();
            }
        });
    }
    
    private void register() {
        final ModelUser user = loginAndRegisterPanel.getUser();
        showMessage(Message.MessageType.SUCCESS, "test message");
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
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(38, 37, 34));
        setMinimumSize(new java.awt.Dimension(750, 500));
        setName("loginSignUp"); // NOI18N

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
