package ru.newprotech.invaders;

import java.lang.ref.WeakReference;

/**
 * Created by 6003 on 21.01.2015.
 */
public class CEnemyBullet extends CSpritedThinker{
    @Override
    public int Think(long delta) {
        int result = super.Think(delta);
        if (result==0){
            CHero hero = CHero.getInstance();
            if (!hero.isInvul() && hero.Collide(this.getRectF())!=0) {
                hero.Die();
                sprite.get().Die();
                result = THINKER_DEAD;
            }
        }
        return result;
    }

    CEnemyBullet(float x, float y, float v, int a){
        CSprite newSprite = CSpriteManager.createSprite(R.drawable.lightning, x, y);
        newSprite.setAnimation(500,0,1);
        newSprite.setVx((float) (v * Math.cos(Math.toRadians(a + 90))));
        newSprite.setVy((float) (v * Math.sin(Math.toRadians(a + 90))));
        sprite = new WeakReference<>(newSprite);
    }


}
