package com.dama.gui._configurations.player;

import com.dama.gui._configurations.dependencies.Duration;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerTimer {
    private final Timer timer;
    private TimerListener listener;
    private long remainingTime;
    private boolean isPaused;
    private ArrayList<Long> timeIntervals;
    private int currentIntervalIndex;

    public PlayerTimer() {
        this.timer = new Timer();
        this.timeIntervals = new ArrayList<>();
        this.currentIntervalIndex = -1;
        this.remainingTime = 0;
    }
    
    public void setTimerListener(final TimerListener listener) {
        this.listener = listener;
    }
    
    private void notifyUpdate() {
        if (listener != null) {
            listener.onUpdate();
        }
    }

    private void notifyTimeUpdate() {
        if (listener != null) {
            listener.onTimeUpdate(remainingTime);
        }
    }

    private void notifyTurnActive(final boolean isActive) {
        if (listener != null) {
            listener.onTurnActive(isActive);
        }
    }

    public void startTimer(final Duration duration, final int delay) {
        this.remainingTime = duration.getTime();
        if (duration.isInfinite()) return;
        notifyTimeUpdate();
        setNewTimeInterval();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                notifyUpdate();
                if (isPaused) notifyTurnActive(false);
                else notifyTurnActive(true);
                
                if (remainingTime > 0 && !isPaused) {
                    remainingTime -= 1000;
                    notifyTimeUpdate();
                } 
                else if (remainingTime <= 0) {
                    timer.cancel();
                    notifyTimeUpdate();
                }
            }
        }, delay, 1000);
        pauseTimer();
    }

    public void stopTimer() {
        this.timer.cancel();
    }

    public void pauseTimer() {
        this.isPaused = true;
    }

    public void resumeTimer() {
        this.isPaused = false;
    }
    
    public boolean isPaused() {
        return this.isPaused;
    }
    
    public void undoTimer() {
        if (currentIntervalIndex > 0) {
            currentIntervalIndex--;
            this.remainingTime = timeIntervals.get(currentIntervalIndex);
            notifyTimeUpdate();
        }
    }

    public void moveTimeBackward() {
        if (currentIntervalIndex > 0) {
            currentIntervalIndex--;
            this.remainingTime = timeIntervals.get(currentIntervalIndex);
            notifyTimeUpdate();
        }
    }
    
    public void moveTimeForward() {
        if (currentIntervalIndex < timeIntervals.size()) {
            currentIntervalIndex++;
            this.remainingTime = timeIntervals.get(currentIntervalIndex);
            notifyTimeUpdate();
        }
    }

    public void setNewTimeInterval() {
        if (currentIntervalIndex < timeIntervals.size() - 1) {
            timeIntervals.subList(currentIntervalIndex + 1, timeIntervals.size()).clear();
        }
        timeIntervals.add(this.remainingTime);
        currentIntervalIndex = timeIntervals.size() - 1;
    }

    public long getRemainingTime() {
        return remainingTime;
    }
    
    public String getTimerToString() {
        final long minutes = (remainingTime / 1000) / 60;
        final long seconds = (remainingTime / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    
    public interface TimerListener {
        void onUpdate();
        void onTimeUpdate(long remainingTime);
        void onTurnActive(boolean isActive);
    }
}
