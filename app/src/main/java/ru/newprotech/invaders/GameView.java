package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by 6003 on 27.12.2014.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    ThinkerThread thread;
    public GameView(Context context) {
        super(context);
        GameContext gameContext = GameContext.getInstance();
        gameContext.setCont(context);
        getHolder().addCallback(this);
        setFocusable(true);
        thread = new ThinkerThread();
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        GameState state = GameState.getInstance();
        return state.TouchHandle(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        GameState state = GameState.getInstance();
//        FpsCounter fps = FpsCounter.getInstance();
//        canvas.drawColor(Color.BLACK);
//        state.Draw(canvas);
//        fps.Draw(canvas);
//        invalidate();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        switch (visibility){
            case GONE:
                GameState.getInstance().stop_thread();
                thread.interrupt();
                break;
            case INVISIBLE:
                GameState.getInstance().pause();
                thread.setRunning(false);
                break;
            case VISIBLE:
                GameState.getInstance().resume();
                if (!thread.isRunning()) {
                    thread.setRunning(true);
                    thread.unlock();
                }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        GameState.getInstance().init();

        thread.setSurfaceHolder(holder);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.interrupt();

    }

}
