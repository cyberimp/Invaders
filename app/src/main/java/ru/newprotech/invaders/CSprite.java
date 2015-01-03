package ru.newprotech.invaders;

import android.graphics.Canvas;

/**
 * Created by 6003 on 27.12.2014.
 */
public class CSprite implements IThinker {
    private float x,y;

    private float vx,vy;
    private float phi,vphi;
    private int animDelay,startFrame,endFrame;
    private int animWait;
    private int frame;
    private CSpritesheet spritesheet;

    CSprite(CSpritesheet spritesheet) {
        frame = 0;
        x = 160;
        y = 240;
        animWait = 200;
        this.spritesheet = spritesheet;
        animDelay = 0;
        startFrame = 0;
        endFrame = 0;
        phi =0;
        vphi=0;
    }

    public float getPhi() {
        return phi;
    }

    public void setPhi(float phi) {
        this.phi = phi;
    }

    public float getVphi() {
        return vphi;
    }

    public void setVphi(float vphi) {
        this.vphi = vphi;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }


    public void setAnimation( int delay, int start, int end){
        animDelay = delay;
        startFrame = start;
        endFrame = end;

    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.save();
        canvas.rotate(phi,x,y);
        spritesheet.Draw(canvas,frame,x,y);
        canvas.restore();
    }

    @Override
    public int Think(long delta) {
        x+=vx*delta;
        y+=vy*delta;
        phi+=vphi*delta;
        if (phi > 360)
            phi = phi-360;
        if (phi <0 )
            phi= phi+360;
        if(animDelay >0) {
            animWait -= delta;
            if (animWait < 0) {
                animWait = animDelay;
                frame++;
                if (frame > endFrame)
                    frame = startFrame;
            }
        }
        return 0;
    }

    public void stop() {
        vx = 0;
        vy = 0;
        vphi = 0;
    }

    public void stopMove() {
        vx = 0;
        vy = 0;
    }

    public void stopRotate() {
        vphi = 0;
    }
}
