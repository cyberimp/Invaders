package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 31.12.2014.
 */
public class GlobalTimer {
    private static GlobalTimer ourInstance = new GlobalTimer();
    private long threadTimer = 0;
    private boolean running;
    private long lastCheck;
    public static GlobalTimer getInstance() {
        return ourInstance;
    }
    public long getDelta(){
//        ScriptThread thread = ScriptThread.getInstance();
            long result = System.currentTimeMillis() - lastCheck;
//        lastCheck = SystemClock.currentThreadTimeMillis();
            lastCheck = System.currentTimeMillis();
            if (threadTimer > 0) {
                threadTimer -= result;
                if (threadTimer < 0)
                    synchronized (ScriptThread.monitor) {
                        ScriptThread.monitor.notify();
                    }
            }
            if (running)
                return result;
            else
                return 0;
        }

    public void start(){
        running = true;
        lastCheck = System.currentTimeMillis();
    }
    public void stop(){
        running = false;
    }

    private GlobalTimer() {

//        lastCheck = SystemClock.currentThreadTimeMillis();
        lastCheck = System.currentTimeMillis();
    }

    public void setWakeup(long millis) {
        threadTimer = millis;
    }
}
