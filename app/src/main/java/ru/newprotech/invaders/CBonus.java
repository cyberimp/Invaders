package ru.newprotech.invaders;

import java.lang.ref.WeakReference;

/**
 * Created by 6003 on 13.01.2015.
 */
public class CBonus extends CSpritedThinker {

    static CBonus createBonus(float x, float y) {
        CBonus bonus = new CBonus(x, y);
        CBonusManager bonusManager = CBonusManager.getInstance();
        bonusManager.Add(bonus);
        return bonus;
    }

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

    private CBonus(float x, float y){
        CSprite newSprite = CSprite.createSprite(R.drawable.power, x, y);
        newSprite.setVx(0);
        newSprite.setVy(.1f);
        sprite = new WeakReference<>(newSprite);

    }
}
