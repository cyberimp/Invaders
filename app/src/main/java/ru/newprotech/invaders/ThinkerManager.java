package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.util.SparseArray;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class ThinkerManager implements IThinker{

    SparseArray<IThinker> collection;
    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public int Think(long delta) {
        return 0;
    }
}
