package ru.newprotech.invaders;

import java.lang.ref.WeakReference;

/**
 * Created by kinzoxbeato on 06.01.2015.
 */
public class CHeroBullet extends CSpritedThinker{
    private CHeroBullet(int res, float angle, float speed, float x, float y){
        CSprite bullet = CSprite.createSprite(res, x, y);
        bullet.setVx((float) Math.cos(Math.toRadians(angle))*speed);
        bullet.setVy((float) Math.sin(Math.toRadians(angle))*speed);
        bullet.setAnimation(100,0,3);
        sprite = new WeakReference<>(bullet);
    }

    static CHeroBullet createBullet(int res, float angle, float speed, float x, float y) {
        CHeroBullet bullet = new CHeroBullet(res, angle, speed, x, y);
        CHeroBulletManager.getInstance().Add(bullet);
        return bullet;
    }
}
