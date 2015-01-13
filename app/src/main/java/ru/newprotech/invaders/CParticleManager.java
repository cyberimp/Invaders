package ru.newprotech.invaders;

import android.graphics.Color;

/**
 * Created by 6003 on 13.01.2015.
 */
public class CParticleManager extends CThinkerManager {
    private static CParticleManager ourInstance = new CParticleManager();

    public static CParticleManager getInstance() {
        return ourInstance;
    }

    private CParticleManager() {
    }

    public static void createExplosion(float x, float y){
        for (int i = 0; i < 100; i++) {
            CParticle particle = new CParticle(x,y, Color.YELLOW,1000,64);
            ourInstance.Add(particle);
        }
    }
}
