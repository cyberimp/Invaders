package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class MainState implements GameState {
    @Override
    public boolean TouchHandle(MotionEvent event) {
        boolean result = false;
        CHero hero = CHero.getInstance();
        CController controller = CController.getInstance();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (    Math.abs(event.getX()-hero.getX())<100 &&
                        Math.abs(event.getY()-hero.getY())<100) {
                    controller.StartMove(new PointF(event.getX(), event.getY()));
                    result = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                controller.StartMove(new PointF(event.getX(), event.getY()));
                result = true;
                break;
            case MotionEvent.ACTION_UP:
                result=true;
                controller.StopMove();
        }
        return result;
    }

    @Override
    public void Draw(Canvas canvas) {
        CBackground back = CBackground.getInstance();
        CHero hero = CHero.getInstance();
        back.Draw(canvas);
        hero.Draw(canvas);

    }

    @Override
    public int Think(long delta) {
        CBackground back = CBackground.getInstance();
        CHero hero = CHero.getInstance();
        back.Think(delta);
        hero.Think(delta);
        return 0;
    }
}
