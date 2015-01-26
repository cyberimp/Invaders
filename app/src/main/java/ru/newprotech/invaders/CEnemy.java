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

    /**
     * Static factory method, creating new enemies
     * @param res Spritesheet resource ID
     * @param x X coordinate of enemy
     * @param y Y coordinate of enemy
     * @param vx X velocity
     * @param vy Y velocity
     * @param hp Hit points
     * @param score Score value
     * @return new enemy reference
     */
    public static CEnemy createEnemy(int res, float x, float y,
                                     float vx, float vy, int bonus, int hp, int score) {
        CEnemy enemy = new CEnemy(res, x, y, vx, vy, bonus, hp, score);
        CEnemyManager.getInstance().Add(enemy);
        return enemy;
    }

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
     * You should use {@link CEnemy#createEnemy(int, float, float, float, float, int, int, int)}
     * @param res Spritesheet resource ID
     * @param x X coord
     * @param y Y coord
     * @param vx X speed
     * @param vy Y speed
     * @param bonus Bonus ID
     * @param hp hit points
     * @param score score value of enemy
     */
    private CEnemy(int res, float x, float y, float vx, float vy, int bonus, int hp, int score){
        CSprite newSprite = CSprite.createSprite(res, x, y);
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
                CBonusManager.createBonus(bonus, getX(), getY());
            CScoreManager.createScore(getX(),getY(),score);
        }
        super.Die();
    }

    public CEnemyBullet Shoot(float a){
        if (!dead) {
            return CEnemyBullet.createBullet(getX(), getY(), 0.2f, a);
        }
        else return null;
    }

}
