package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by 6003 on 27.12.2014.
 */
public class GameView extends View {
    private GameState state;
//    CBackground back;
//    CSpritesheetManager spritesheetManager;
//    CSprite warship;
//    long timer;
    public GameView(Context context) {
        super(context);
        GameContext gameContext = GameContext.getInstance();
        gameContext.setCont(context);
//        CSpritesheet warshipSS = new CSpritesheet(R.drawable.warship,32,32);
//        warship = new CSprite(warshipSS);
//        warship.setAnimation(100,0,3);
//        timer = System.currentTimeMillis();
        state = new LoadState();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return state.TouchHandle(event);
//        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        FpsCounter fps = FpsCounter.getInstance();
        GlobalTimer timer = GlobalTimer.getInstance();
        long delta = timer.getDelta();
        fps.Think(delta);
        canvas.drawColor(Color.BLACK);
        switch (state.Think(delta)){
            case GameState.STATE_SAME:
                break;
            case GameState.STATE_MAIN:
                state = new MainState();
                break;
        }
        state.Draw(canvas);
//        back.Think(delta);
//        warship.Think(delta);
//        back.Draw(canvas);
//        warship.Draw(canvas);
        fps.Draw(canvas);
        invalidate();
    }
}
