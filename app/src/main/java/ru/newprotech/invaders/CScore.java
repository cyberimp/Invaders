package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by 6003 on 21.01.2015.
 */
public class CScore implements IThinker {
    private int value;
    private float x,y;
    private float alpha = 255;
    private CSpritesheet font;

    @Override
    public void Draw(Canvas canvas) {
        String value = ""+this.value;
        float width = (float)value.length()/2;
        Paint alphaPaint = new Paint();
        alphaPaint.setAlpha((int) alpha);
        for (int x = 0; x < value.length(); x++) {
            font.Draw(canvas,value.charAt(x) - '0',this.x-width+x*8,y,alphaPaint);
        }
    }

    @Override
    public int Think(long delta) {
        alpha -= (float)delta*.5f;
        y-=.01*(float)delta;
        if (alpha<=0.f)
            return THINKER_DEAD;
        else
            return 0;
    }

    @Override
    public int Collide(RectF rect) {
        return 0;
    }

    @Override
    public void Die() {

    }

    public CScore(float x, float y, int score, int font){
        this.x=x;
        this.y=y;
        this.value = score;
        CSpritesheetManager manager = CSpritesheetManager.getInstance();
        this.font = manager.getSheet(font);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
