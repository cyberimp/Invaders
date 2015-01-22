package ru.newprotech.invaders;

import android.graphics.RectF;
import android.util.Log;

import java.util.Vector;

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
            Vector<CEnemy> a = new Vector<>();
            CEnemy temp;
            //Thread body
            for (int i = 0; i < 5; i++) {
                temp = CEnemy.createEnemy(R.drawable.skull, rectF.width()+64, 64, -.05f, 0, 1, 10, 100);
                a.add(temp);
                temp = CEnemy.createEnemy(R.drawable.skull, -64, 128, .05f, 0, 0, 10, 100);
                a.add(temp);
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot();
            }
            timerWait(10000);
            for (int i = 0; i < 5; i++) {
                temp = CEnemy.createEnemy(R.drawable.grunt, -64, 64, .05f, 0, 1, 10, 100);
                a.add(temp);
                temp = CEnemy.createEnemy(R.drawable.grunt, rectF.width()+64, 128, -.05f, 0, 0, 10, 100);
                a.add(temp);
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("thread","Thread ended");
            this.interrupt();
        }
    }

}
