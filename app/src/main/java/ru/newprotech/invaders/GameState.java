package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
interface GameState extends IThinker{
    static int STATE_SAME = 0;
    static int STATE_LOADING = 1;
    static int STATE_MENU = 3;
    static int STATE_MAIN = 3;
    static int STATE_GAMEOVER = 3;


    public boolean TouchHandle(MotionEvent event);
}
