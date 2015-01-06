package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.lang.ref.WeakReference;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CEnemy implements IThinker {

    WeakReference<CSprite> sprite;

    @Override
    public void Draw(Canvas canvas) {
        if (sprite.get()!=null)
            sprite.get().Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        CHeroBulletManager heroBulletManager = CHeroBulletManager.getInstance();
        if (heroBulletManager.Collide(sprite.get().getRectF())){
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
