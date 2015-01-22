package ru.newprotech.invaders;

import android.graphics.Color;

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
    int score;

    @Override
    public int Think(long delta) {
        if (sprite.get() == null||dead)
            return THINKER_DEAD;
        CHeroBulletManager heroBulletManager = CHeroBulletManager.getInstance();
        hp -= heroBulletManager.Collide(getRectF());
        CHero hero = CHero.getInstance();
        if (!hero.isInvul() && hero.Collide(getRectF()) > 0) {
            hp -= 5;
            hero.Die();
        }

        if (hp <= 0) {
             Die();
             return THINKER_DEAD;
        }
        else
            return 0;
    }

    /**
     * Creating enemy.
     * You should use {@link CEnemyManager#createEnemy(int, float, float, float, float, int, int, int)}
     * @param res Spritesheet resource ID
     * @param x X coord
     * @param y Y coord
     * @param vx X speed
     * @param vy Y speed
     * @param bonus Bonus ID
     * @param hp hit points
     * @param score score value of enemy
     */
    public CEnemy(int res, float x, float y, float vx, float vy, int bonus, int hp, int score){
        CSprite newSprite = CSpriteManager.createSprite(res, x, y);
        newSprite.setVx(vx);
        newSprite.setVy(vy);
        sprite = new WeakReference<>(newSprite);
        this.bonus = bonus;
        this.hp = hp;
        this.score = score;
    }

    @Override
    public void Die() {
        if (sprite.get() != null) {
            CParticleManager.createExplosion(getX(), getY(), Color.WHITE);
            if (bonus>0)
                CBonusManager.createBonus(getX(), getY());
            CScoreManager.createScore(getX(),getY(),score);
        }
        super.Die();
    }

    public CEnemyBullet Shoot(){
        if (!dead) {
            return CEnemyBulletManager.createBullet(getX(), getY(), 0.2f, 0);
        }
        else return null;
    }

}
