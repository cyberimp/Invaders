package ru.newprotech.invaders;

import android.os.SystemClock;

/**
 * Created by kinzoxbeato on 31.12.2014.
 */
public class GlobalTimer {
    private static GlobalTimer ourInstance = new GlobalTimer();

    private boolean running;
    private long lastCheck;
    public static GlobalTimer getInstance() {
        return ourInstance;
    }
    public long getDelta(){
        long result = SystemClock.currentThreadTimeMillis() - lastCheck;
        lastCheck = SystemClock.currentThreadTimeMillis();
        return result;
    }

    public void start(){
        running = true;
    }
    public void stop(){
        running = false;
    }

    private GlobalTimer() {
        lastCheck = SystemClock.currentThreadTimeMillis();
    }
}