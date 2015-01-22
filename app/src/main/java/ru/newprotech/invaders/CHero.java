package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class CHero implements IThinker{
    private static CHero ourInstance = new CHero();

    public static CHero getInstance() {
        return ourInstance;
    }

    CWeapon weapon;

    long invul=0;

    private CHero() {
        sprite=null;
        weapon = new CWeapon(0,0);
    }

    private CSprite sprite;

    @Override
    public void Draw(Canvas canvas) {
        if(sprite!=null)
            sprite.Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        if (invul >0){
            invul -= delta;
            if (invul<0) {
                invul = 0;
                sprite.stopBlink();
            }
        }

        CController controller = CController.getInstance();
        if (controller.isMove()){
            PointF point = controller.getMoveHere();
            if (Math.abs(point.x - sprite.getX())>1 || Math.abs(point.y - sprite.getY())>1) {
                float rate = (float) Math.hypot(point.x - sprite.getX(), point.y - sprite.getY());
                sprite.setVx((point.x - sprite.getX()) * .4f/rate);
                sprite.setVy((point.y - sprite.getY()) * .4f/rate);
            }
            else
                sprite.stopMove();
        }
        else
            sprite.stopMove();
        if (controller.isShoot()){
            PointF point = controller.getShootHere();
            float angle = (float) Math.toDegrees(Math.atan2(point.y - sprite.getY(),
                                                            point.x - sprite.getX()))+90;
            if (Math.abs(angle-sprite.getPhi()) > .1)
                if (Math.abs(angle-sprite.getPhi()) <180)
                    sprite.setVphi(Math.signum(angle-sprite.getPhi())*.1f);
                else
                    sprite.setVphi(-Math.signum(angle-sprite.getPhi())*.1f);
            else
                sprite.stopRotate();
            weapon.Think(delta);
        }
        else
            sprite.stopRotate();
//        sprite.Think(delta);
        return 0;
    }

    @Override
    public int Collide(RectF rect) {
        if (!sprite.getRectF().intersect(rect))
            return 0;
        else
            return 1;
    }

    @Override
    public void Die() {
        CParticleManager.createExplosion(getX(),getY(), Color.WHITE);
        init();
    }

    public void startAnimation(int delay, int start, int end){
        sprite.setAnimation(delay,start,end);
    }

    public void setSprite(CSprite sprite) {
        this.sprite = sprite;
//        sprite.setVphi(.1f);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getPhi() {
        return sprite.getPhi();
    }

    public void PowerUp() {
        weapon.PowerUp();
    }

    public void init() {
        GameContext gameContext = GameContext.getInstance();
        WindowManager wm = (WindowManager) gameContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        CController controller = CController.getInstance();
        controller.halt();
        invul = 3000;
        weapon.init();
        sprite.setXY(display.getWidth() / 2, display.getHeight() - 64);
        sprite.setPhi(0);
        sprite.startBlink();
    }

    public boolean isInvul() {
        return (invul>0);
    }
}
