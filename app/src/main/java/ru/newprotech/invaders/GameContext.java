package ru.newprotech.invaders;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by 6003 on 27.12.2014.
 */
public class GameContext {
    private static GameContext ourInstance = new GameContext();
    private Context cont;

    public static GameContext getInstance() {
        return ourInstance;
    }


    private GameContext() {
    }

    public void setCont(Context cont) {
        this.cont = cont;
    }
    public Resources getResources(){
        return this.cont.getResources();
    }

    public Object getSystemService(String name) {
        return cont.getSystemService(name);
    }
}
