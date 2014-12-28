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
    public void TouchHandle(MotionEvent event) {
        CHero hero = CHero.getInstance();
        CController controller = CController.getInstance();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if ((event.getX()-hero.getX())<5)
                    controller.StartMove(new PointF(event.getX(),event.getX()));
        }
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
