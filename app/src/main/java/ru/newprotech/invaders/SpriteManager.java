package ru.newprotech.invaders;

import java.util.Vector;

/**
 * Created by kinzoxbeato on 03.01.2015.
 */
public class SpriteManager extends ThinkerManager {
    private static SpriteManager ourInstance = new SpriteManager();

    public static SpriteManager getInstance() {
        return ourInstance;
    }

    public CSprite createSprite(int res,float x, float y){
        CSpritesheetManager spritesheetManager = CSpritesheetManager.getInstance();
        CSprite temp=new CSprite(spritesheetManager.getSheet(res));
        temp.setXY(x,y);
        if (this.Add(temp))
            return temp;
        else
            return null;

    }

    private boolean Add(CSprite sprite) {
        return this.collection.add(sprite);
    }

    private SpriteManager() {
        this.collection = new Vector<>();
    }
}
