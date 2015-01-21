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

    public static CEnemyBullet createBullet(float x,float y){
        CEnemyBullet bullet = new CEnemyBullet(x,y);
        ourInstance.Add(bullet);
        return bullet;
    }

}
