package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by kinzoxbeato on 07.01.2015.
 */
public class CStar implements IThinker {

    float x,y,size;
    private final Paint paint;

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawPoint(x,y, paint);
    }

    @Override
    public int Think(long delta) {
        y+=(size+1)*delta*.05f;
        if (y> CBackground.getRectF().height())
            Die();
        return 0;
    }

    @Override
    public int Collide(RectF rect) {
        return 0;
    }

    @Override
    public void Die() {
        x = (float) (Math.random()*CBackground.getRectF().width());
        y = 0;
        size = (float) (4*Math.random()+.1);
        paint.setStrokeWidth(size);
    }


    CStar(){
        x = (float) (Math.random()*CBackground.getRectF().width());
        y = (float) (Math.random()*CBackground.getRectF().height());
        size = (float) (4*Math.random());
        paint = new Paint();
        paint.setStrokeWidth(size);
        paint.setColor(Color.LTGRAY);
    }
}
