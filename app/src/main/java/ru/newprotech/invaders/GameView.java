package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by 6003 on 27.12.2014.
 */
public class GameView extends View {

    CBackground back;
    CSpritesheetManager spritesheetManager;
    CSprite warship;
    long timer;
    int frameStart;
    public GameView(Context context) {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        GameContext gameContext = GameContext.getInstance();
        gameContext.setCont(context);
        back = new CBackground(R.drawable.nebula,display.getWidth(),display.getHeight());
        CSpritesheet warshipSS = new CSpritesheet(R.drawable.warship,32,32);
        warship = new CSprite(warshipSS);
        warship.setAnimation(100,0,3);
        timer = System.currentTimeMillis();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long delta = 0;
        if (timer > 0)
            delta = System.currentTimeMillis() - timer;
        timer = System.currentTimeMillis();
        canvas.drawColor(Color.BLACK);
        back.Think(delta);
        warship.Think(delta);
        back.Draw(canvas);
        warship.Draw(canvas);
        invalidate();
    }
}
