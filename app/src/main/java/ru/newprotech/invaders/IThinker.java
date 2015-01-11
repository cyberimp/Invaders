package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by 6003 on 27.12.2014.
 */

/**
 * Thinker interface
 * Small bits, that can move and draw itself
 */
interface IThinker {
    public static final int THINKER_DEAD=1;
    public void Draw(Canvas canvas);
    public int Think(long delta);
    public boolean Collide(RectF rect);
    void Die();
}
