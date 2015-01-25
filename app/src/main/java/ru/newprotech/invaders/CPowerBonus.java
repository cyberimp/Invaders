package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 25.01.2015.
 */
public class CPowerBonus extends CBonus {
    static CBonus createBonus(float x, float y) {
        CPowerBonus bonus = new CPowerBonus(x, y, R.drawable.power);
        CBonusManager bonusManager = CBonusManager.getInstance();
        bonusManager.Add(bonus);
        return bonus;
    }

    @Override
    protected void pickUp() {
        CHero.getInstance().PowerUp();
    }

    private CPowerBonus(float x, float y, int res) {
        super(x, y, res);
    }
}
