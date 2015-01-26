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
    private final Paint paint;

    public static CGuiPanel getInstance() {
        return ourInstance;
    }

    private CGuiPanel() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    public void Draw(Canvas canvas) {
        GameState state = GameState.getInstance();
        canvas.drawText("score:" + state.getScore(), 0.f, 32.f, paint);
        CSpritesheet live = CSpritesheetManager.getInstance().getSheet(R.drawable.warship);
        int livescount = CHero.getInstance().getLives();
        for (int i = 0; i < livescount; i++) {
            live.Draw(canvas,1,8.f+16*i,48.f, paint,0.5f);
        }
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
