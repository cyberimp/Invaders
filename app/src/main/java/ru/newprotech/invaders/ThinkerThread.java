package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;


/**
 * Created by 6003 on 26.01.2015.
 */
public class ThinkerThread extends Thread {
    private boolean running = false;

    public ThinkerThread() {
        this.running = true;
    }

    private SurfaceHolder surfaceHolder;

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

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
        Canvas canvas;
        try {
            while (true) {
                canvas = null;
                delta = timer.getDelta();
                fps.Think(delta);
                int new_state = state.Think(delta);
                if (new_state != IGameState.STATE_SAME)
                    state.change(new_state);
                if (surfaceHolder!= null)
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder){
                        canvas.drawColor(Color.BLACK);
                        state.Draw(canvas);
                        fps.Draw(canvas);

                    }
                }finally{
                    if (canvas!=null)
                        surfaceHolder.unlockCanvasAndPost(canvas);
                }
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

