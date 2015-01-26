package ru.newprotech.invaders;

/**
 * Created by 6003 on 21.01.2015.
 */
public class CEnemyBulletManager extends CThinkerManager {
    private static CEnemyBulletManager ourInstance = new CEnemyBulletManager();

    public static CEnemyBulletManager getInstance() {
        return ourInstance;
    }

    private CEnemyBulletManager() {
    }

}
