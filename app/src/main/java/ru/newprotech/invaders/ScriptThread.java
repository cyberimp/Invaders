package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 6003 on 12.01.2015.
 */
public class ScriptThread extends Thread {
    @Override
    public void run() {
        RectF rectF = CBackground.getRectF();
        try {
            //Thread body
            while (true) {
                CEnemyManager.createEnemy(R.drawable.skull, rectF.centerX(), -10, 0, .05f);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
