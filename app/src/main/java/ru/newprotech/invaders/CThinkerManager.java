package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

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
    public synchronized int Collide(RectF rect){
        int result = 0;
        for (IThinker i:collection) {
            int collide = i.Collide(rect);
            result+=collide;
            if(collide>0)
                i.Die();
        }
        return result;
    }

    @Override
    public void Die() {

    }

    public synchronized void Clear(){
        collection = new Vector<>();
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
