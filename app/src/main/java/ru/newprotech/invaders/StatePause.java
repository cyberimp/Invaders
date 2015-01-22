package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 18.01.2015.
 */
public class StatePause implements IGameState {

    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public int Think(long delta) {
        return 0;
    }

    @Override
    public boolean TouchHandle(MotionEvent event) {
        return false;
    }

    @Override
    public int getState() {
        return STATE_PAUSE;
    }
}
