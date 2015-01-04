package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by 6003 on 27.12.2014.
 */
public class CSpritesheet {
    private Bitmap sheet;
    private int frame_x,frame_y;
    private int frames_count;
    private int row_size;

    public RectF getRectF(){
        return new RectF(0,0,frame_x,frame_y);
    }

    public CSpritesheet(int resource,int size_x, int size_y){
        GameContext context = GameContext.getInstance();
        sheet = BitmapFactory.decodeResource(context.getResources(),resource);
        frame_x = size_x;
        frame_y = size_y;
        row_size = sheet.getWidth()/frame_x;
        frames_count = sheet.getHeight()/frame_y*row_size;
    }

    public void Draw(Canvas canvas,int frame_no,float x,float y){
        if (frame_no > frames_count)
            return;
        RectF dstRect = new RectF();
        Rect srcRect = new Rect();
        float halfX = (float)frame_x / 2.f;
        float halfY = (float)frame_y / 2.f;
        dstRect.set(x-halfX,y-halfY,x+halfX,y+halfY);
        int srcX = frame_no%row_size;
        int srcY = frame_no/row_size;
        srcRect.set(srcX*frame_x,srcY*frame_y,(srcX+1)*frame_x,(srcY+1)*frame_y);
        canvas.drawBitmap(sheet,srcRect,dstRect,null);
    }
}
