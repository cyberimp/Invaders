package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CEnemyManager extends CThinkerManager {
    private static CEnemyManager ourInstance = new CEnemyManager();

    public static CEnemyManager getInstance() {
        return ourInstance;
    }

    public static CEnemy createEnemy(int res, float x, float y, float vx, float vy){
        CEnemy enemy = new CEnemy(res, x, y, vx, vy);
        ourInstance.Add(enemy);
        return enemy;
    }

    private CEnemyManager() {
    }
}
