package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by kinzoxbeato on 31.12.2014.
 */
public class FpsCounter implements IThinker {
    private static FpsCounter ourInstance = new FpsCounter();
    private final Paint paint;

    public static FpsCounter getInstance() {
        return ourInstance;
    }

    private float fps=0;
    private int frames=0;
    private int time_counter=0;

    private FpsCounter() {
        paint = new Paint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.WHITE);
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawText("fps:"+fps,0,16, paint);
    }

    @Override
    public int Think(long delta) {
        time_counter += delta;
        frames++;
        if (time_counter > 1000){
            fps = (float)frames/(float)time_counter*1000.f;
            time_counter = 0;
            frames = 0;
        }
        return 0;
    }

    @Override
    public int Collide(RectF rect) {
        return 0;
    }

    @Override
    public void Die() {

    }
}
