package com.dama.gui;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Animation {
    
    // Constructor: Prevent Instantiation
    private Animation() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
    }
    
    /**
     * Animates a JLabel to move from top to bottom.
     *
     * @param panel     The JPanel where the label parent to be animated.
     * @param bounds    The JPanel of the content size.
     * @param label     The JLabel to animate.
     * @param duration  The duration of the animation in milliseconds.
     */
    public static void topToBottomMoveAnim(final JPanel panel, final JLabel label, final Rectangle bounds, final int duration) {
        final int startY = -label.getHeight();
        final int endY = (int) ((bounds.getHeight() - label.getHeight()) / 2);
        
        final int frameRate = 10;
        final int totalFrames = duration / frameRate;
        final int step = (endY - startY) / totalFrames;
        
        System.out.println(label.getWidth());
        System.out.println(bounds.getWidth());
        System.out.println(bounds.getWidth() / 2);
        label.setLocation((int) (bounds.getWidth()), (int) 100);
        panel.add(label);
        panel.revalidate();
        panel.repaint();
        
        // Create a timer to remove the message after a delay
        final Timer animationTimer = new Timer(5, null);
        animationTimer.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        animationTimer.start();
        
    }
    
    /**
     * Animates a JLabel to move from bottom to top and removes it after completion.
     *
     * @param label     The JLabel to animate.
     * @param container The container in which the animation occurs.
     * @param duration  The duration of the animation in milliseconds.
     * @param onFinish  A callback that executes after the animation and removal.
     */
    public static void bottomToTopMoveAnim(final JPanel container, final JLabel label, final int duration, Runnable onFinish) {
        final int startY = (container.getHeight() - label.getHeight()) / 2;
        final int endY = -label.getHeight();

        final int frameRate = 10;
        final int totalFrames = duration / frameRate;
        final int step = (endY - startY) / totalFrames;

        final javax.swing.Timer timer = new javax.swing.Timer(frameRate, null);
        timer.addActionListener(e -> {
            int currentY = label.getY();
            if (currentY <= endY) {
                // Final position and remove the label
                container.remove(label);
                container.revalidate();
                container.repaint();
                timer.stop();

                // Call the onFinish callback if provided
                if (onFinish != null) {
                    onFinish.run();
                }
            } else {
                label.setLocation(label.getX(), currentY + step);
                container.repaint();
            }
        });
        timer.start();
    }
}
