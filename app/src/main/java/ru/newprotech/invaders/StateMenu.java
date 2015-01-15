package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by 6003 on 15.01.2015.
 */
public class StateMenu implements IGameState {
    int nextState = 0;
    @Override
    public void Draw(Canvas canvas) {
        CBackground back = CBackground.getInstance();
        CHero hero = CHero.getInstance();
        CMenu menu = CMenu.getInstance();
        back.Draw(canvas);
        hero.Draw(canvas);
        menu.draw(canvas);
    }

    @Override
    public int Think(long delta) {
        return nextState;
    }

    @Override
    public boolean TouchHandle(MotionEvent event) {
        CMenu menu = CMenu.getInstance();
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                nextState = menu.touch(event.getX(),event.getY());
                return true;
            default:
        }
        return false;
    }
}
