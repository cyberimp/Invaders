package ru.newprotech.invaders;

import android.graphics.Canvas;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class CHero implements IThinker{
    private static CHero ourInstance = new CHero();

    public static CHero getInstance() {
        return ourInstance;
    }

    private CHero() {
    }

    private CSprite sprite;

    @Override
    public void Draw(Canvas canvas) {
        if(sprite!=null)
            sprite.Draw(canvas);
    }

    @Override
    public int Think(long delta) {
        CController controller = CController.getInstance();
        sprite.Think(delta);
        return 0;
    }

    public void setSprite(CSprite sprite) {
        this.sprite = sprite;
    }

    public float getX() {
        return 0;
    }
}
