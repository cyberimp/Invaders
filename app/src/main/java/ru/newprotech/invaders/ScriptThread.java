package ru.newprotech.invaders;

import android.graphics.RectF;

/**
 * Created by 6003 on 12.01.2015.
 */

/**
 * Separate thread for enemy generation
 */
public class ScriptThread extends Thread {

    public static final Object monitor = new Object();

    /**
     * Asks timer to wait for given time or more
     * @param millis Time to wait in millis
     * @throws InterruptedException
     */
    private void timerWait (long millis) throws InterruptedException{
        GlobalTimer timer = GlobalTimer.getInstance();
        synchronized (monitor) {
            timer.setWakeup(millis);
//            try {
                monitor.wait();
//            } catch (InterruptedException e) {
//                throw e;
//            }
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
            this.interrupt();
            e.printStackTrace();
        }
    }

}
