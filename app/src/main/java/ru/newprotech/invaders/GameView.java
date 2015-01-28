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
    public GameView(Context context) {
        super(context);
        GameContext gameContext = GameContext.getInstance();
        gameContext.setCont(context);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        GameState state = GameState.getInstance();
        return state.TouchHandle(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        GameState state = GameState.getInstance();
        FpsCounter fps = FpsCounter.getInstance();
        canvas.drawColor(Color.BLACK);
        state.Draw(canvas);
        fps.Draw(canvas);
        invalidate();
    }
}
