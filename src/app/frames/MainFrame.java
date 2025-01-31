package app.frames;

import app.dialog.QuitConfirmation;
import app.panels.CardHandlers.CardLayoutManager;
import app.panels.CardHandlers.CardPanelRegistry;
import app.panels.MainDirectory;
import app.panels.loginSignUpPanels.Message;
import app.panels.popupPanel.PanelChangeProfile;
import com.dama.engine.sounds.SoundManager;
import com.db.model.ModelHistory;
import com.db.model.ModelProfile;
import com.db.service.ServiceHistory;
import com.db.sounds.NotificationManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.Interpolator;
import org.jdesktop.core.animation.timing.TimingSource;
import org.jdesktop.core.animation.timing.TimingTarget;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
import org.jdesktop.core.animation.timing.sources.ScheduledExecutorTimingSource;
import utilities.CommonConstants;
import utilities.ImageFiles;

public class MainFrame extends JFrame {
    static final boolean DESIGN_TIME = java.beans.Beans.isDesignTime();
    private static MainFrame instance;
    
    public static boolean isInstance() {
        return instance != null;
    }
    
    public static MainFrame getInstance() {
        return instance;
    }
    
    public ModelProfile getUserProfile() {
        return this.userProfile;
    }
    
    public void setUserProfile(final ModelProfile profile) {
        this.userProfile = profile;
    }
    
    public boolean isLogin() {
        return this.userProfile != null;
    }
    
    public ServiceHistory getServiceHistory() {
        return this.service_history;
    }
    
    public List<ModelHistory> getLocalHistory() {
        return Collections.unmodifiableList(localHistory);
    }
    
    public void addDataFromLocalHistory(final ModelHistory data) {
        localHistory.add(data);
    }
    
    private final List<ModelHistory> localHistory;
    private final ServiceHistory service_history;
    private final MainDirectory directory;
    private final MigLayout layout;
    public final PanelChangeProfile changeProfile;
    private ModelProfile userProfile;
    
    public MainFrame(final ModelProfile user) {
        if (instance != null && !DESIGN_TIME) {
            throw new IllegalArgumentException("Panel class already registered: " + getName());
        }
        instance = (MainFrame) this;
        userProfile = user;
        service_history = new ServiceHistory();
        localHistory = new ArrayList<>();
        directory = new MainDirectory();
        changeProfile = new PanelChangeProfile();
        layout = new MigLayout("fill, insets 50");
        initComponents();
        init();
    }
    
    private void init() {
        setIconImage(ImageFiles.DAMA_LOGO);
        setFont(CommonConstants.DEFAULT_FONT);
        setMaximumSize(CommonConstants.MAX_SIZE);
        setMinimumSize(CommonConstants.MIN_SIZE);
        setPreferredSize(CommonConstants.PREFFERED_SIZE);
        setSize(CommonConstants.PREFFERED_SIZE);
        setResizable(true);
        setVisible(true);
        bg.setLayout(layout);
        bg.setLayer(changeProfile, JLayeredPane.POPUP_LAYER);
        bg.add(changeProfile, "pos 0 0 100% 100%");
        bg.add(directory, "pos 0 0 100% 100%");
        if (!SoundManager.Sounds.BACKGROUND_MUSIC.isPlaying()) {
            SoundManager.Sounds.BACKGROUND_MUSIC.play();
            SoundManager.Sounds.BACKGROUND_MUSIC.loopForever();
        }
        if (userProfile != null) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    showMessage(Message.MessageType.SUCCESS, "Welcome " + userProfile.getUserName() + " to Dama");
                    NotificationManager.Sounds.CORRECT_NOTIF.play();
                } catch (InterruptedException e) {}
            }).start();
        }
        try {
            if (userProfile != null)
                localHistory.addAll(service_history.getLatestHistory(userProfile.getUserID(), 20));
        } catch (SQLException e) {
            showMessage(Message.MessageType.ERROR, "Can't load the local history.");
            NotificationManager.Sounds.WRONG_NOTIF.play();
        }
        revalidate();
        repaint();
    }
    
    public void showMessage(final Message.MessageType messageType, final String message) {
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
        if (status == app.dialog.QuitConfirmation.RET_YES) {
            dispose();
            System.exit(0);
        }
    }
    
    @Override
    public void dispose() {
        super.dispose();
        SoundManager.Sounds.BACKGROUND_MUSIC.stop();
        CardPanelRegistry.clearInstances();
        CardLayoutManager.clearInstances();
        instance = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Dama");
        setMinimumSize(new java.awt.Dimension(950, 600));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(950, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bg.setBackground(new java.awt.Color(48, 46, 43));
        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("MainFrame");

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
