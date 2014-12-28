package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class LoadState implements GameState {

    private int TextAlpha;
    private int AlphaSpeed;
    LoadingTask loadingTask;

    @Override
    public void TouchHandle(MotionEvent event) {

    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setAlpha(TextAlpha);
        canvas.drawColor(Color.BLACK);
        canvas.drawText("LOADING",170,240,paint);

    }

    @Override
    public int Think(long delta) {
        TextAlpha += AlphaSpeed*delta;
        if (TextAlpha > 255) {
            TextAlpha = 255;
            AlphaSpeed = -1;
        }
        if (TextAlpha < 0) {
            TextAlpha = 0;
            AlphaSpeed = 1;
        }
        if (loadingTask.getStatus() == AsyncTask.Status.FINISHED)
            return GameState.STATE_MAIN;
        else
            return GameState.STATE_SAME;
    }

    public LoadState() {
        TextAlpha = 255;
        AlphaSpeed = -1;
        loadingTask = new LoadingTask();
        loadingTask.execute();
    }
}
