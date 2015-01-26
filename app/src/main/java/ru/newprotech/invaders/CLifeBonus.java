package ru.newprotech.invaders;

/**
 * Created by 6003 on 26.01.2015.
 */
public class CLifeBonus extends CBonus {

    static CBonus createBonus(float x, float y) {
        CLifeBonus bonus = new CLifeBonus(x, y, R.drawable.power);
        CBonusManager bonusManager = CBonusManager.getInstance();
        bonusManager.Add(bonus);
        return bonus;
    }


    @Override
    protected void pickUp() {
        super.pickUp();
        CHero.getInstance().incLives();
    }

    public CLifeBonus(float x, float y, int res) {
        super(x, y, res);
        sprite.get().setFrame(1);
    }
}
