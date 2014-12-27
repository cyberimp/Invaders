package ru.newprotech.invaders;

import android.graphics.Canvas;

/**
 * Created by 6003 on 27.12.2014.
 */
public class CSprite implements IThinker {
    private float x,y;

    private float vx,vy;
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
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public void setVy(float vy) {
        this.vy = vy;
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
        spritesheet.Draw(canvas,frame,x,y);
    }

    @Override
    public int Think(long delta) {
        x+=vx*delta;
        y+=vy*delta;
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
}
