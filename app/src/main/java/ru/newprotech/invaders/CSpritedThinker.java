package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.lang.ref.WeakReference;

/**
 * Created by 6003 on 12.01.2015.
 */
public class CSpritedThinker implements IThinker {

    WeakReference<CSprite> sprite;
    boolean dead = false;


    @Override
    public void Draw(Canvas canvas) {
        if (sprite.get()!=null)
            sprite.get().Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        if (sprite.get() == null || dead)
            return THINKER_DEAD;
        else
            return 0;
    }

    @Override
    public int Collide(RectF rect) {
        if (sprite.get()!=null)
            return sprite.get().Collide(rect);
        else
            return 0;
    }

    @Override
    public void Die() {
        dead = true;
        if (sprite.get()!=null) {
            sprite.get().Die();
        }

    }

    public void setXY(float x, float y){
        if (sprite.get()!=null)
            sprite.get().setXY(x,y);
    }

    public float getX(){
        if (sprite.get()!=null)
            return sprite.get().getX();
        else
            return 0;

    }

    public float getY(){
        if (sprite.get()!=null)
            return sprite.get().getY();
        else
            return 0;

    }

    public void setVx(float vx){
        if (sprite.get()!=null)
            sprite.get().setVx(vx);
    }

    public void setVy(float vy){
        if (sprite.get()!=null)
            sprite.get().setVy(vy);
    }
    public RectF getRectF(){
        if (sprite.get()!=null)
            return sprite.get().getRectF();
        else
            return null;
    }
}
