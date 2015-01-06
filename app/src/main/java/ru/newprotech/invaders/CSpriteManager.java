package ru.newprotech.invaders;

import java.util.Vector;

/**
 * Created by kinzoxbeato on 03.01.2015.
 */
public class CSpriteManager extends CThinkerManager {
    private static CSpriteManager ourInstance = new CSpriteManager();

    public static CSpriteManager getInstance() {
        return ourInstance;
    }

    public static CSprite createSprite(int res,float x, float y){
        CSpritesheetManager spritesheetManager = CSpritesheetManager.getInstance();
        CSprite temp=new CSprite(spritesheetManager.getSheet(res));
        temp.setXY(x,y);
        if (ourInstance.Add(temp))
            return temp;
        else
            return null;

    }


    private CSpriteManager() {
        this.collection = new Vector<>();
    }
    public static void init(){
        ourInstance.collection = new Vector<>();
    }
}
