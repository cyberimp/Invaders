package ru.newprotech.invaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by 6003 on 15.01.2015.
 */
public class CMenuItem {
    private RectF rect;
    private Bitmap bitmap;
    private int nextState;

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,null,rect,null);
    }

    public boolean check(float x, float y){
        return rect.contains(x,y);
    }

    public CMenuItem(RectF rect, int res, int nextState) {
        this.rect = rect;
        GameContext context = GameContext.getInstance();
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), res);
        this.nextState = nextState;
    }

    public int getNextState() {
        return nextState;
    }
}
