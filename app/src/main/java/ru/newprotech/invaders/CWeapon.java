package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 04.01.2015.
 */
public class CWeapon {
    private int coolDown = 100;
    private int weaponTemp = 0;
    private int power=1;
    private float dx,dy;
    public void Think(long delta){
        if (weaponTemp == 0){
            CHero hero = CHero.getInstance();
            CHeroBullet.createBullet(R.drawable.bullet,hero.getPhi()-90,.2f,
                    hero.getX(),hero.getY());
            for (int i = 0; i < power; i++) {
                CHeroBullet.createBullet(R.drawable.bullet,hero.getPhi()-90 - i*2,.2f,
                        hero.getX(),hero.getY());
                CHeroBullet.createBullet(R.drawable.bullet,hero.getPhi()-90 + i*2,.2f,
                        hero.getX(),hero.getY());
            }
        }
        weaponTemp+=delta;
        if (weaponTemp > coolDown)
            weaponTemp = 0;
    }

    public CWeapon(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void PowerUp() {
        power++;
    }

    public void init() {
        power = 1;
    }

    public int getPower() {
        return power;
    }
}
