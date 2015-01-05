package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 04.01.2015.
 */
public class CWeapon {
    private int coolDown = 100;
    private int weaponTemp = 0;
    public void Think(long delta){
        if (weaponTemp == 0){
            CHero hero = CHero.getInstance();
            CSprite bullet = CSpriteManager.createSprite(R.drawable.bullet, hero.getX(), hero.getY());
            //TODO: lots of math, maybe add methods for r-phy speed?
            bullet.setVx((float) Math.cos(Math.toRadians(hero.getPhi()-90))*.1f);
            bullet.setVy((float) Math.sin(Math.toRadians(hero.getPhi()-90))*.1f);
            bullet.setAnimation(100,0,3);
        }
        weaponTemp+=delta;
        if (weaponTemp > coolDown)
            weaponTemp = 0;
    }
}
