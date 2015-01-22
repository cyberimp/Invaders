package ru.newprotech.invaders;

/**
 * Created by 6003 on 22.01.2015.
 */
public class CGameManager extends CThinkerManager {
    private static CGameManager ourInstance = new CGameManager();

    public static CGameManager getInstance() {
        return ourInstance;
    }

    private CGameManager() {
//        back.Draw(canvas);
//        particleManager.Draw(canvas);
//        enemyBulletManager.Draw(canvas);
//        heroBulletManager.Draw(canvas);
//        bonusManager.Draw(canvas);
//        enemyManager.Draw(canvas);
//        hero.Draw(canvas);
//        scoreManager.Draw(canvas);
        Add(CBackground.getInstance());
        Add(CParticleManager.getInstance());
        Add(CEnemyBulletManager.getInstance());
        Add(CHeroBulletManager.getInstance());
        Add(CBonusManager.getInstance());
        Add(CEnemyManager.getInstance());
        Add(CHero.getInstance());
        Add(CScoreManager.getInstance());
    }

    @Override
    public synchronized void Clear() {
        for (IThinker i:collection)
            i.Die();
    }
}
