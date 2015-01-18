package ru.newprotech.invaders;

import android.graphics.RectF;

/**
 * Created by 6003 on 12.01.2015.
 */

/**
 * Separate thread for enemy generation
 */
public class ScriptThread extends Thread {

    public static Object monitor = new Object();
    private static ScriptThread ourInstance = new ScriptThread();

    public static ScriptThread getInstance() {
        return ourInstance;
    }

    private void timerWait (long millis) throws InterruptedException {
        GlobalTimer timer = GlobalTimer.getInstance();
        synchronized (monitor) {
            timer.setWakeup(millis);
            monitor.wait();
        }
    }

    @Override
    public void run() {
        RectF rectF = CBackground.getRectF();
        try {
            //Thread body
            for (int i = 0; i < 5; i++) {
                CEnemyManager.createEnemy(R.drawable.skull, rectF.width()+64, 64, -.05f, 0, 1);
                CEnemyManager.createEnemy(R.drawable.skull, -64, 128, .05f, 0, 0);
                timerWait(2000);
            }
            timerWait(10000);
            for (int i = 0; i < 5; i++) {
                CEnemyManager.createEnemy(R.drawable.grunt, -64, 64, .05f, 0, 1);
                CEnemyManager.createEnemy(R.drawable.grunt, rectF.width()+64, 128, -.05f, 0, 0);
                timerWait(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
