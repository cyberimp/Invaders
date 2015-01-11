package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.lang.ref.WeakReference;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */

/**
 * Enemy class
 */
public class CEnemy implements IThinker {
    /**
     * Reference to sprite
     */
    WeakReference<CSprite> sprite;
    /**
     * Enemy HP value
     */
    int hp = 10;

    @Override
    public void Draw(Canvas canvas) {
        if (sprite.get()!=null)
            sprite.get().Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        CHeroBulletManager heroBulletManager = CHeroBulletManager.getInstance();
        if (heroBulletManager.Collide(sprite.get().getRectF()))
            hp--;
        if (hp <= 0) {
             Die();
             return THINKER_DEAD;
        }
        else
            return 0;
    }

    @Override
    public boolean Collide(RectF rect) {
        return sprite.get().Collide(rect);
    }

    @Override
    public void Die() {
        sprite.get().Die();
    }

    public CEnemy (int res, float x, float y, float vx, float vy){
        CSprite newSprite = CSpriteManager.createSprite(res, x, y);
        newSprite.setVx(vx);
        newSprite.setVy(vy);
        sprite = new WeakReference<>(newSprite);
    }
}
