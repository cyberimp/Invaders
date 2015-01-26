package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */

/**
 * Enemy manager
 */
public class CEnemyManager extends CThinkerManager {
    private static CEnemyManager ourInstance = new CEnemyManager();

    public static CEnemyManager getInstance() {
        return ourInstance;
    }

    private CEnemyManager() {
        super();
    }
}
