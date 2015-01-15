package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 6003 on 27.12.2014.
 */
public class GameView extends View {
    private IGameState state;
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
        state = new StateLoad();

    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
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
            case IGameState.STATE_SAME:
                break;
            case IGameState.STATE_MAIN:
                state = new StateMain();
                break;
            case IGameState.STATE_MENU:
                state = new StateMenu();
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
