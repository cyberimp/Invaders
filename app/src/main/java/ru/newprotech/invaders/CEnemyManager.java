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

    /**
     * Static factory method, creating new enemies
     * @param res Spritesheet resource ID
     * @param x X coordinate of enemy
     * @param y Y coordinate of enemy
     * @param vx X velocity
     * @param vy Y velocity
     * @param hp Hit points
     * @param score Score value
     * @return new enemy reference
     */
    public static CEnemy createEnemy(int res, float x, float y, float vx, float vy, int bonus, int hp, int score){
        CEnemy enemy = new CEnemy(res, x, y, vx, vy, bonus, hp, score);
        ourInstance.Add(enemy);
        return enemy;
    }

    private CEnemyManager() {
    }
}
