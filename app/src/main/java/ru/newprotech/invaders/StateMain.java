package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */

/**
 * Main game state
 */
public class StateMain implements IGameState {
    @Override
    public boolean TouchHandle(MotionEvent event) {
        boolean result = false;
        CHero hero = CHero.getInstance();
        CController controller = CController.getInstance();
//        int action = event.getAction();
        int actionCode = event.getActionMasked();
        int pid = event.getActionIndex();
        int fingerID = event.getPointerId(pid);

        switch (actionCode){
            //TODO: add multitouch support
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (    Math.abs(event.getX(pid)-hero.getX())<50 &&
                        Math.abs(event.getY(pid)-hero.getY())<50) {
                    if (controller.getFinger(CController.POINTER_MOVE) == -1) {
                        controller.setFinger(CController.POINTER_MOVE, fingerID);
                        controller.StartMove(new PointF(event.getX(pid), event.getY(pid)));
                        result = true;
                    }
                    else
                        result = false;
                }
                else{
                    if (controller.getFinger(CController.POINTER_SHOOT) == -1) {
                        controller.setFinger(CController.POINTER_SHOOT, fingerID);
                        hero.startAnimation(100, 4, 7);
                        controller.StartShoot(new PointF(event.getX(pid), event.getY(pid)));
                        result = true;
                    }
                    else
                        result = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (controller.getFinger(CController.POINTER_MOVE)==fingerID) {
                    controller.StartMove(new PointF(event.getX(pid), event.getY(pid)));
                    result = true;
                }
                else if (controller.getFinger(CController.POINTER_SHOOT)==fingerID) {
                    controller.StartShoot(new PointF(event.getX(pid), event.getY(pid)));
                    result = true;
                }
                else
                    result = false;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (controller.getFinger(CController.POINTER_MOVE)==fingerID) {
                    controller.StopMove();
                    controller.setFinger(CController.POINTER_MOVE, -1);
                    result = true;
                }
                else if (controller.getFinger(CController.POINTER_SHOOT)==fingerID) {
                    hero.startAnimation(100, 0, 3);
                    controller.setFinger(CController.POINTER_SHOOT, -1);
                    controller.StopShoot();
                    result = true;
                }
                else
                    result = false;
        }
        return result;
    }

    @Override
    public int getState() {
        return STATE_MAIN;
    }

    @Override
    public void Draw(Canvas canvas) {

        CGameManager gameManager = CGameManager.getInstance();
        gameManager.Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        CSpriteManager spriteManager = CSpriteManager.getInstance();
        CGameManager gameManager = CGameManager.getInstance();
        spriteManager.Think(delta);
        gameManager.Think(delta);
        return 0;
    }

    StateMain(){
        CHero hero = CHero.getInstance();
        GameState state = GameState.getInstance();
        CGameManager gameManager = CGameManager.getInstance();
        CSpriteManager spriteManager = CSpriteManager.getInstance();
        spriteManager.Clear();
        gameManager.Clear();
        hero.init();
        state.start_thread();
//        if (thread.getState() == Thread.State.NEW  || thread.getState() == Thread.State.RUNNABLE)
    }
}
