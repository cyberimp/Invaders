package ru.newprotech.invaders;

/**
 * Created by 6003 on 13.01.2015.
 */
public class CBonusManager extends CThinkerManager{
    private static CBonusManager ourInstance = new CBonusManager();

    public static CBonusManager getInstance() {
        return ourInstance;
    }

    private CBonusManager() {
        super();
    }

    public static CBonus createBonus(int kind, float x, float y){
        CBonus result;
        switch (kind){
            case CBonus.BONUS_POWER:
                result = CPowerBonus.createBonus(x,y);
                break;
            case CBonus.BONUS_LIFE:
                result = CLifeBonus.createBonus(x,y);
                break;
            default:
                result = CBonus.createBonus(x,y);
        }
        return result;
    }

}
