package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by 6003 on 23.01.2015.
 */
public class CGuiPanel implements IThinker {
    private static CGuiPanel ourInstance = new CGuiPanel();

    public static CGuiPanel getInstance() {
        return ourInstance;
    }

    private CGuiPanel() {
    }

    @Override
    public void Draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        GameState state = GameState.getInstance();
        canvas.drawText("score:" + state.getScore(), 0.f, 32.f, paint);
    }

    @Override
    public int Think(long delta) {
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
