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

}
