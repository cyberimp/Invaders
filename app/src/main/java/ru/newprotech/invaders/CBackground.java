package ru.newprotech.invaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by 6003 on 27.12.2014.
 */
public class CBackground implements IThinker {
    private Bitmap image;
    private float deltaY;
    private float screenY = 0;
    private RectF screenSize;

    private static CBackground Instance = new CBackground();

    public static CBackground getInstance() {
        return Instance;
    }
    
    public void LoadBackground(int res,int x, int y){
        BitmapFactory.Options options = new BitmapFactory.Options();
        GameContext context = GameContext.getInstance();
        Bitmap back = BitmapFactory.decodeResource(context.getResources(),res,options);
        image = Bitmap.createScaledBitmap(back, x, y, true);
        screenSize = new RectF(0,0,x,y);
        CStarManager.Fill();
    }

    @Override
    public void Draw(Canvas canvas) {
        CStarManager starManager = CStarManager.getInstance();
        screenY = canvas.getHeight();
        RectF screenRect = new RectF(canvas.getClipBounds());
        screenRect.offset(0,deltaY);
        canvas.drawBitmap(image, null, screenRect, null);
        screenRect.offset(0, -screenRect.height());
        canvas.drawBitmap(image, null, screenRect, null);
        starManager.Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        CStarManager starManager = CStarManager.getInstance();
        starManager.Think(delta);
        deltaY+=.1*delta;
        if (deltaY>screenY)
            deltaY = 0;
        return 0;
    }

    @Override
    public boolean Collide(RectF rect) {
        return true;
    }

    @Override
    public void Die() {

    }

    public static RectF getRectF() {
        return Instance.screenSize;
    }
}
