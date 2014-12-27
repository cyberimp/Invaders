package ru.newprotech.invaders;

import java.util.HashMap;

/**
 * Created by 6003 on 27.12.2014.
 */
public class CSpritesheetManager {
    private HashMap<Integer, CSpritesheet> collection;

    {
        collection = new HashMap<>();
    }

    public CSpritesheet getSheet(int Resource){
        if (collection.containsKey(Resource))
            return collection.get(Resource);
        else
            return null;
    }
    public CSpritesheet loadSheet(int res,int sizeX,int sizeY){
        if (collection.containsKey(res))
            return null;
        CSpritesheet spritesheet = new CSpritesheet(res,sizeX,sizeY);
        collection.put(res,spritesheet);
        return spritesheet;
    }
}
