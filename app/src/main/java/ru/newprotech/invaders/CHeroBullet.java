package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.lang.ref.WeakReference;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CHeroBullet implements IThinker {
    WeakReference<CSprite> sprite;
    boolean dead = false;
    @Override
    public void Draw(Canvas canvas) {
        if (sprite.get()!=null)
            sprite.get().Draw(canvas);
    }

    @Override
    public boolean Collide(RectF rectF){
        if (sprite.get()!=null) {
            RectF temp = sprite.get().getRectF();
            return temp.intersect(rectF);
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

    @Override
    public int Think(long delta) {

        if (sprite.get() == null || dead)
            return THINKER_DEAD;
        else
            return 0;
    }

    CHeroBullet (int res, float angle, float speed, float x, float y){
        CSprite bullet = CSpriteManager.createSprite(res, x, y);
        bullet.setVx((float) Math.cos(Math.toRadians(angle))*speed);
        bullet.setVy((float) Math.sin(Math.toRadians(angle))*speed);
        bullet.setAnimation(100,0,3);
        sprite = new WeakReference<>(bullet);
    }
}
