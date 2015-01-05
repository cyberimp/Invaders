package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

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

    public RectF getRectF(){
        RectF rect = spritesheet.getRectF();
        rect.offsetTo(x-rect.width()/2,y-rect.height()/2);
        return rect;
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
        //TODO: fix ship not rotating to left from 0
        x+=vx*delta;
        y+=vy*delta;
        phi+=vphi*delta;
        if (phi > 360)
            phi = phi-360.f;
        if (phi <0 )
            phi= phi+360.f;
        if(animDelay >0) {
            animWait -= delta;
            if (animWait < 0) {
                animWait = animDelay;
                frame++;
                if (frame > endFrame)
                    frame = startFrame;
            }
        }
        RectF rectF = CBackground.getRectF();
        if ( x < -rectF.width()||x>rectF.width()*2.f||y<-rectF.height()||y>rectF.height()*2)
            return THINKER_DEAD;
        else
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
