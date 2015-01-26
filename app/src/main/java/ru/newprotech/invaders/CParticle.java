package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by 6003 on 13.01.2015.
 */
public class CParticle implements IThinker {

    private final Paint paint;

    @Override
    public void Draw(Canvas canvas) {
        paint.setAlpha(alpha);
        canvas.drawPoint(x,y, paint);
    }

    @Override
    public int Think(long delta) {
        timer -=delta;
        alpha -= va*delta;
        x+=vx*delta;
        y+=vy*delta;
        if (timer > 0)
            return 0;
        else
            return THINKER_DEAD;
    }

    @Override
    public int Collide(RectF rect) {
        return 0;
    }

    @Override
    public void Die() {

    }

    private float x,y;
    private int alpha;
    private float timer;
    private float vx,vy;
    private float va;

    public CParticle(float x, float y, int color, float timer, float radius) {
        this.timer = (float) (timer*Math.random());
        float angle = (float) (Math.random()*360);
        vx = (float) (radius/this.timer*Math.cos(angle));
        vy = (float) (radius/this.timer*Math.sin(angle));
        va = 255/this.timer;
        this.x = x;
        this.y = y;
        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth((float) (Math.random()*4+.1));
    }
}
