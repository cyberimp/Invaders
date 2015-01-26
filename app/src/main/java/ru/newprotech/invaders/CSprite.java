package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Paint;
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
    private boolean dead=false;
    private CSpritesheet spritesheet;
    private boolean blinking = false;
    float blinkAlpha = 255;
    float alphaSpeed = -1f;
    private final Paint paint;
    private RectF rect;

    private CSprite(CSpritesheet spritesheet) {
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
        paint = new Paint();
        rect = new RectF(spritesheet.getRectF());
    }

    /**
     * Static factory method, creating new sprite
     * @param res Spritesheet resource ID
     * @param x X coordinate for sprite
     * @param y Y coordinate for sprite
     * @return Sprite reference to mess with
     */
    static CSprite createSprite(int res,float x, float y) {
        CSpritesheetManager spritesheetManager = CSpritesheetManager.getInstance();
        CSprite temp=new CSprite(spritesheetManager.getSheet(res));
        temp.setXY(x,y);
        if (CSpriteManager.getInstance().Add(temp))
            return temp;
        else
            return null;
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
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);
    }

    public float getVx() {
        return vx;
    }

    public float getVy() {
        return vy;
    }

    public RectF getRectF(){
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
        paint.setAlpha((int) blinkAlpha);
        spritesheet.Draw(canvas,frame,x,y, paint, 1.f);
        canvas.restore();
    }

    @Override
    public int Think(long delta) {
        if(blinking)
            blinkAlpha+=(float)delta*alphaSpeed;
        if (blinkAlpha<0.f){
            blinkAlpha = 0.f;
            alphaSpeed = 1f;
        }
        if( blinkAlpha>255.f){
            blinkAlpha = 255.f;
            alphaSpeed = -1f;
        }

        x+=vx*delta;
        y+=vy*delta;
        rect.offsetTo(x - rect.width() / 2, y - rect.height() / 2);
        phi+=vphi*delta;
        if (phi > 270)
            phi = phi-360.f;
        if (phi <-90 )
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
        if ( x < -rectF.width()||x>rectF.width()*2.f
                ||y<-rectF.height()||y>rectF.height()*2
                || dead)
            return THINKER_DEAD;
        else
            return 0;
    }

    @Override
    public int Collide(RectF rect) {
//        if (rect == null)
//            return 0;
        if (RectF.intersects(rect,getRectF()))
            return 1;
        else
            return 0;
    }

    @Override
    public void Die() {
        dead = true;
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

    public void startBlink() {
        blinking = true;

    }

    public void stopBlink() {
        blinking = false;
        blinkAlpha = 255;
    }
}
