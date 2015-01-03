package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.util.SparseArray;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class ThinkerManager implements IThinker{

    Vector<IThinker> collection;
    @Override
    public void Draw(Canvas canvas) {
        for (IThinker i:collection) {
            if (i!=null)
                i.Draw(canvas);
        }
    }

    @Override
    public int Think(long delta) {
        for (IThinker i:collection) {
            if (i!=null)
                i.Think(delta);
        }
        return 0;
    }

    ThinkerManager(){
        collection = new Vector<>();
    }
}
