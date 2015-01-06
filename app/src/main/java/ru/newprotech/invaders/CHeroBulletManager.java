package ru.newprotech.invaders;

import android.graphics.RectF;

import java.util.Vector;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CHeroBulletManager extends CThinkerManager{
    private static CHeroBulletManager ourInstance = new CHeroBulletManager();

    public static CHeroBulletManager getInstance() {
        return ourInstance;
    }

    private CHeroBulletManager() {
    }



    public static CHeroBullet createBullet(int res, float angle, float speed, float x, float y){
        CHeroBullet bullet = new CHeroBullet(res,angle,speed,x,y);
        ourInstance.Add(bullet);
        return bullet;
    }



    public static void init(){
        ourInstance.collection = new Vector<>();
    }
}
