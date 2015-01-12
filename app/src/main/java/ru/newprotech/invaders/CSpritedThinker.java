package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.lang.ref.WeakReference;

/**
 * Created by 6003 on 12.01.2015.
 */
public class CSpritedThinker implements IThinker {

    WeakReference<CSprite> sprite;
    boolean dead = false;


    @Override
    public void Draw(Canvas canvas) {
        if (sprite.get()!=null)
            sprite.get().Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        if (sprite.get() == null || dead)
            return THINKER_DEAD;
        else
            return 0;
    }

    @Override
    public boolean Collide(RectF rect) {
        if (sprite.get()!=null) {
            RectF temp = sprite.get().getRectF();
            return temp.intersect(rect);
        }
        else
            return false;
    }

    @Override
    public void Die() {
        dead = true;
        if (sprite.get()!=null) {
            sprite.get().Die();
        }

    }
}
