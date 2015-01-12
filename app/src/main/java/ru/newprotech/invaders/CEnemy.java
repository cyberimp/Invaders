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
public class CEnemy extends CSpritedThinker {
    /**
     * Enemy HP value
     */
    int hp = 10;

    @Override
    public int Think(long delta) {
        if (sprite.get() == null||dead)
            return THINKER_DEAD;
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

    public CEnemy (int res, float x, float y, float vx, float vy){
        CSprite newSprite = CSpriteManager.createSprite(res, x, y);
        newSprite.setVx(vx);
        newSprite.setVy(vy);
        sprite = new WeakReference<>(newSprite);
    }
}
