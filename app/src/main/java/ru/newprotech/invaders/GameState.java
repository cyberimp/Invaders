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

    public static GameState getInstance() {
        return ourInstance;
    }

    private GameState() {
    }
    

    public void init(){
        state = new StateLoad();
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

    public void change(int new_state) {
        switch (new_state){
            case IGameState.STATE_SAME:
                break;
            case IGameState.STATE_MAIN:
                state = new StateMain();
                break;
            case IGameState.STATE_MENU:
                state = new StateMenu();
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
}
