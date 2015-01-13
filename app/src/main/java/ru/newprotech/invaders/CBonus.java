package ru.newprotech.invaders;

import java.lang.ref.WeakReference;

/**
 * Created by 6003 on 13.01.2015.
 */
public class CBonus extends CSpritedThinker {
    @Override
    public int Think(long delta) {
        int result = super.Think(delta);
        if (result==0){
            CHero hero = CHero.getInstance();
            if (hero.Collide(this.getRectF())!=0) {
                hero.PowerUp();
                sprite.get().Die();
                result = THINKER_DEAD;
            }
        }
        return result;
    }

    CBonus(float x, float y){
        CSprite newSprite = CSpriteManager.createSprite(R.drawable.power, x, y);
        newSprite.setVx(0);
        newSprite.setVy(.1f);
        sprite = new WeakReference<>(newSprite);

    }
}
