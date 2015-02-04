package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */

/**
 * State for loading images and stuff
 */
public class StateLoad implements IGameState {

    private int TextAlpha;
    private int AlphaSpeed;
    private LoadingTask loadingTask;
    private Float Progress;
    private RectF Screen;
    private final Paint paint;

    @Override
    public boolean TouchHandle(MotionEvent event) {

        return false;
    }

    @Override
    public int getState() {
        return STATE_LOADING;
    }

    @Override
    public void endState() {

    }

    @Override
    public void Draw(Canvas canvas) {
        float percent = GameState.getInstance().getProgress();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(TextAlpha);
        paint.setStrokeWidth(0);
        canvas.drawText("LOADING", Screen.centerX(), Screen.centerY(), paint);
        paint.setAlpha(255);
        paint.setStrokeWidth(3);
//        canvas.drawRect(100, 270, 240, 290, paint);
        canvas.drawRect(Screen.centerX()-70, Screen.centerY()+32, Screen.centerX()+70, Screen.centerY()+52, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawRect(105,275,235+(235.f-105.f)/100.f*Progress,285,paint);
        canvas.drawRect(Screen.centerX()-65, Screen.centerY()+37,
                Screen.centerX()-65.f+130.f/100.f*percent,
                Screen.centerY()+47, paint);


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
            return IGameState.STATE_MENU;
        else
            return IGameState.STATE_SAME;
    }

    public StateLoad() {
        TextAlpha = 255;
        AlphaSpeed = -1;
        Progress = new Float(0.f);
        loadingTask = new LoadingTask();
        loadingTask.execute(Progress);
        GameContext gameContext = GameContext.getInstance();
        WindowManager wm = (WindowManager) gameContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Screen = new RectF(0,0,display.getWidth(),display.getHeight());

        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.WHITE);
    }
}
