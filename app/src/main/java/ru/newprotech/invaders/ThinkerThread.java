package ru.newprotech.invaders;

import java.util.Objects;

/**
 * Created by 6003 on 26.01.2015.
 */
public class ThinkerThread extends Thread {
    private boolean running = true;

    private final Object monitor = new Object();
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void unlock(){
        synchronized (monitor) {
            monitor.notify();
        }
    }

    @Override
    public void run() {
        GameState state = GameState.getInstance();
        FpsCounter fps = FpsCounter.getInstance();
        GlobalTimer timer = GlobalTimer.getInstance();
        long delta;
        try {
            while (true) {
                delta = timer.getDelta();
                fps.Think(delta);
                int new_state = state.Think(delta);
                if (new_state != IGameState.STATE_SAME)
                    state.change(new_state);
                synchronized (monitor) {
                    if (!running)
                        monitor.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

