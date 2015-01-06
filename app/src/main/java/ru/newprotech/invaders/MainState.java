package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
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
            //TODO: add multitouch support
            case MotionEvent.ACTION_DOWN:
                if (    Math.abs(event.getX()-hero.getX())<50 &&
                        Math.abs(event.getY()-hero.getY())<50) {
                    controller.setPointer(0,CController.POINTER_MOVE);
                    controller.StartMove(new PointF(event.getX(), event.getY()));
                    result = true;
                }
                else{
                    hero.startAnimation(100,4,7);
                    controller.setPointer(0,CController.POINTER_SHOOT);
                    controller.StartShoot(new PointF(event.getX(), event.getY()));
                    result = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (controller.getPointer(0)==CController.POINTER_MOVE)
                    controller.StartMove(new PointF(event.getX(), event.getY()));
                else
                    controller.StartShoot(new PointF(event.getX(), event.getY()));
                result = true;
                break;
            case MotionEvent.ACTION_UP:
                result=true;

                if (controller.getPointer(0)==CController.POINTER_MOVE)
                    controller.StopMove();
                else {
                    hero.startAnimation(100, 0, 3);
                    controller.StopShoot();
                }
        }
        return result;
    }

    @Override
    public void Draw(Canvas canvas) {
        CBackground back = CBackground.getInstance();
        CSpriteManager spriteManager = CSpriteManager.getInstance();
        CHero hero = CHero.getInstance();
        back.Draw(canvas);
        spriteManager.Draw(canvas);
        hero.Draw(canvas);

    }

    @Override
    public int Think(long delta) {
        CBackground back = CBackground.getInstance();
        CSpriteManager spriteManager = CSpriteManager.getInstance();
        CHero hero = CHero.getInstance();
        CEnemyManager enemyManager = CEnemyManager.getInstance();
        CHeroBulletManager bulletManager = CHeroBulletManager.getInstance();
        back.Think(delta);
        hero.Think(delta);
        spriteManager.Think(delta);
        enemyManager.Think(delta);
        bulletManager.Think(delta);
        return 0;
    }

    @Override
    public boolean Collide(RectF rect) {
        return false;
    }

    @Override
    public void Die() {

    }
    MainState(){
        CEnemyManager.createEnemy(R.drawable.skull,120,-10,0,.05f);
    }
}
