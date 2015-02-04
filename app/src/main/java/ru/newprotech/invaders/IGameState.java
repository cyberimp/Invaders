package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */

/**
 * Abstract game state
 */
interface IGameState {
    final static int STATE_SAME = 0;
    final static int STATE_LOADING = 1;
    final static int STATE_MENU = 2;
    final static int STATE_MAIN = 3;
    final static int STATE_GAMEOVER = 4;
    final static int STATE_PAUSE = 5;

    /**
     * Draws needed elements on screen (View layer)
     * @param canvas Target canvas
     */
    public void Draw(Canvas canvas);

    /**
     * Moves elements for current state (Model layer)
     * @param delta Time in milliseconds
     * @return Next state code
     */
    public int Think(long delta);

    /**
     * Handles touch events for current state (Controller layer)
     * @param event Motion event
     * @return Event was processed
     */
    public boolean TouchHandle(MotionEvent event);

    public int getState();
    public void endState();
}
