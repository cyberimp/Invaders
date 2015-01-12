package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.SparseArray;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class CThinkerManager implements IThinker{

    Vector<IThinker> collection;
    @Override
    public synchronized void Draw(Canvas canvas) {
        for (IThinker i:collection) {
            if (i!=null)
                i.Draw(canvas);
        }
    }

    @Override
    public synchronized int Think(long delta) {
        Vector<IThinker> deletable = new Vector<>();
        for (IThinker i:collection) {
            if (i!=null)
                if(i.Think(delta)==THINKER_DEAD){
                    deletable.add(i);
                }
        }
        collection.removeAll(deletable);
        return 0;
    }

    @Override
    public synchronized boolean Collide(RectF rect){
        boolean result = false;
        for (IThinker i:collection) {
            if(i.Collide(rect)) {
                result = true;
                i.Die();
            }
        }
        return result;
    }

    @Override
    public void Die() {

    }

    public synchronized boolean Add(IThinker thinker) {
        return this.collection.add(thinker);
    }

    public RectF getRectF() {
        return null;
    }

    CThinkerManager(){
        collection = new Vector<>();
    }
}
