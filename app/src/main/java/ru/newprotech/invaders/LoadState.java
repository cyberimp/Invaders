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
    private LoadingTask loadingTask;
    private Float Progress;

    @Override
    public boolean TouchHandle(MotionEvent event) {

        return false;
    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setAlpha(TextAlpha);
        canvas.drawText("LOADING", 170, 240, paint);
        paint.setAlpha(255);
        paint.setStrokeWidth(3);
        canvas.drawRect(100, 270, 240, 290, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(105,275,235+(235.f-105.f)/100.f*Progress,285,paint);


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
        Progress = new Float(0.f);
        loadingTask = new LoadingTask();
        loadingTask.execute(Progress);
    }
}
