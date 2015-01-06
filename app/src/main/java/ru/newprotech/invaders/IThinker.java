package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by 6003 on 27.12.2014.
 */
interface IThinker {
    public static final int THINKER_DEAD=1;
    public void Draw(Canvas canvas);
    public int Think(long delta);
    public boolean Collide(RectF rect);
    void Die();
}
