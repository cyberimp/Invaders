package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by 6003 on 23.01.2015.
 */
public class StateGameover implements IGameState {
    @Override
    public void Draw(Canvas canvas) {
        Paint paint= new Paint();
        paint.setColor(Color.WHITE);
        CBackground back = CBackground.getInstance();
        RectF rectF = CBackground.getRectF();
        back.Draw(canvas);
        CSpritesheetManager.getInstance().getSheet(R.drawable.gameover).Draw(
                canvas,0,rectF.centerX(),rectF.centerY(),paint, 1.f);

    }

    @Override
    public int Think(long delta) {
        return 0;
    }

    @Override
    public boolean TouchHandle(MotionEvent event) {
        return false;
    }

    @Override
    public int getState() {
        return STATE_GAMEOVER;
    }
}
