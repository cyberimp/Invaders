package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 04.01.2015.
 */
public class CWeapon {
    private int coolDown = 100;
    private int weaponTemp = 0;
    private float dx,dy;
    public void Think(long delta){
        if (weaponTemp == 0){
            CHero hero = CHero.getInstance();
            //TODO: place CHeroBullet-emitting code here
//            CSprite bullet = CSpriteManager.createSprite(R.drawable.bullet, hero.getX(), hero.getY());
//            bullet.setVx((float) Math.cos(Math.toRadians(hero.getPhi()-90))*.1f);
//            bullet.setVy((float) Math.sin(Math.toRadians(hero.getPhi()-90))*.1f);
//            bullet.setAnimation(100,0,3);
            CHeroBulletManager.createBullet(R.drawable.bullet,hero.getPhi()-90,.2f,hero.getX(),hero.getY());
            CHeroBulletManager.createBullet(R.drawable.bullet,hero.getPhi()-95,.2f,hero.getX(),hero.getY());
            CHeroBulletManager.createBullet(R.drawable.bullet,hero.getPhi()-85,.2f,hero.getX(),hero.getY());
        }
        weaponTemp+=delta;
        if (weaponTemp > coolDown)
            weaponTemp = 0;
    }

    public CWeapon(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
