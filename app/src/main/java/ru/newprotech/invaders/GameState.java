package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 18.01.2015.
 */

/**
 * State machine
 */
public class GameState implements IGameState {
    private static GameState ourInstance = new GameState();
    private static IGameState state;
    private static IGameState prev_state=null;

    private int score;

    private ScriptThread thread;

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
    }
    

    public void init(){
        if (thread!= null)
            thread.interrupt();
        else
            thread = new ScriptThread();
        state = new StateLoad();
        prev_state = null;
    }
    
    @Override
    public void Draw(Canvas canvas) {
        state.Draw(canvas);    
    }

    @Override
    public int Think(long delta) {
        return state.Think(delta);
    }

    @Override
    public boolean TouchHandle(MotionEvent event) {
        return state.TouchHandle(event);
    }

    @Override
    public int getState() {
        return state.getState();
    }

    public void change(int new_state) {
        switch (new_state){
            case STATE_SAME:
                break;
            case STATE_MAIN:
                state = new StateMain();
                break;
            case STATE_MENU:
                state = new StateMenu();
                break;
            case STATE_GAMEOVER:
                state = new StateGameover();
                break;
        }

    }

    public void pause(){
        prev_state = state;
        state = new StatePause();
        GlobalTimer timer = GlobalTimer.getInstance();
        timer.stop();
    }

    public void resume(){
        if(prev_state!=null)
            state = prev_state;
        GlobalTimer timer = GlobalTimer.getInstance();
        timer.start();
    }

    public void start_thread(){
        if (thread.getState()!= Thread.State.NEW)
        {
            thread.interrupt();
            thread = new ScriptThread();
        }
        thread.start();

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(int increment){
        this.score+=increment;
    }
}
