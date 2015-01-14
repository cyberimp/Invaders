package ru.newprotech.invaders;

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
    int bonus;

    @Override
    public int Think(long delta) {
        if (sprite.get() == null||dead)
            return THINKER_DEAD;
        CHeroBulletManager heroBulletManager = CHeroBulletManager.getInstance();
        hp -= heroBulletManager.Collide(sprite.get().getRectF());

        if (hp <= 0) {
             Die();
             return THINKER_DEAD;
        }
        else
            return 0;
    }

    public CEnemy(int res, float x, float y, float vx, float vy, int bonus){
        CSprite newSprite = CSpriteManager.createSprite(res, x, y);
        newSprite.setVx(vx);
        newSprite.setVy(vy);
        sprite = new WeakReference<>(newSprite);
        this.bonus = bonus;
    }

    @Override
    public void Die() {
        if (sprite.get() != null) {
            CParticleManager.createExplosion(sprite.get().getX(), sprite.get().getY());
            if (bonus>0)
                CBonusManager.createBonus(sprite.get().getX(), sprite.get().getY());
        }
        super.Die();
    }
}
