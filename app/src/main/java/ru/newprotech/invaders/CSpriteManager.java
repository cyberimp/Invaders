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



    private CSpriteManager() {
        this.collection = new Vector<>();
    }

    @Override
    public void Die() {
        CHero hero = CHero.getInstance();
        CSprite heroSprite= hero.getSprite();
        this.Clear();
        Add(heroSprite);
    }

}
