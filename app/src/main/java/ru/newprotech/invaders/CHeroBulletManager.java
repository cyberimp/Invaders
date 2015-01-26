package ru.newprotech.invaders;

import java.util.Vector;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CHeroBulletManager extends CThinkerManager{
    private static CHeroBulletManager ourInstance = new CHeroBulletManager();

    public static CHeroBulletManager getInstance() {
        return ourInstance;
    }

    private CHeroBulletManager() {
    }

}
