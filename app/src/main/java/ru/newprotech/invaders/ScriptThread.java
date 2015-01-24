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
                temp = CEnemy.createEnemy(R.drawable.skull, rectF.width()+64, 64, -.05f, 0, 1, 10, 200);
                a.add(temp);
                temp = CEnemy.createEnemy(R.drawable.skull, -64, 128, .05f, 0, 0, 10, 100);
                a.add(temp);
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }
            for (int i = 0; i < 1; i++) {
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }
            timerWait(5000);
            a.clear();
            for (int i = 0; i < 5; i++) {
                temp = CEnemy.createEnemy(R.drawable.grunt, -64, 64, .05f, 0, 1, 10, 200);
                a.add(temp);
                temp = CEnemy.createEnemy(R.drawable.grunt, rectF.width()+64, 128, -.05f, 0, 0, 10, 100);
                a.add(temp);
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }
            for (int i = 0; i < 1; i++) {
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }
            timerWait(5000);
            temp = CEnemy.createEnemy(R.drawable.skull, rectF.centerX(), -64, 0, 0.02f, 1, 500, 1000);
            timerWait(6000);
            temp.setVx(0);
            temp.setVy(0);
            for (int j = 0; j < 10; j++) {

                for (int i = 0; i < 6; i++) {
                    temp.Shoot(0.f);
                    temp.Shoot(45.f);
                    temp.Shoot(-45.f);
                    timerWait(100);
                }
                timerWait(500);
            }
            temp.setVx(1);
            timerWait(1000);
            for (int i = 0; i < 7; i++) {
                temp = CEnemy.createEnemy(R.drawable.skull, rectF.width()+64, 64, -.05f, 0, 1, 10, 200);
                a.add(temp);
                temp = CEnemy.createEnemy(R.drawable.skull, -64, 128, .05f, 0, 0, 10, 100);
                a.add(temp);
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }
            for (int i = 0; i < 1; i++) {
                timerWait(2000);
                for(CEnemy enemy:a)
                    enemy.Shoot(0.f);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("thread","Thread ended");
            this.interrupt();
        }
    }

}
