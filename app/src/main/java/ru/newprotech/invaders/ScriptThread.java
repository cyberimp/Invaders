package ru.newprotech.invaders;

import android.graphics.RectF;

/**
 * Created by 6003 on 12.01.2015.
 */

/**
 * Separate thread for enemy generation
 */
public class ScriptThread extends Thread {
    @Override
    public void run() {
        RectF rectF = CBackground.getRectF();
        try {
            //Thread body
            for (int i = 0; i < 5; i++) {
                CEnemyManager.createEnemy(R.drawable.skull, rectF.width()+64, 64, -.05f, 0);
                CEnemyManager.createEnemy(R.drawable.skull, -64, 128, .05f, 0);
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
