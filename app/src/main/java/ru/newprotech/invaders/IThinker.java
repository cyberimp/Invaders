package ru.newprotech.invaders;

import android.graphics.Canvas;

/**
 * Created by 6003 on 27.12.2014.
 */
interface IThinker {
    public void Draw(Canvas canvas);
    public int Think(long delta);
}
