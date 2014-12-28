package ru.newprotech.invaders;

import android.graphics.Point;
import android.graphics.PointF;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */
public class CController {
    private static CController ourInstance = new CController();
    private PointF MoveHere,ShootHere;
    private boolean Move, Shoot;
    public static CController getInstance() {
        return ourInstance;
    }

    private CController() {
        MoveHere = new PointF(0,0);
        ShootHere = new PointF(0,0);
        Move = false;
        Shoot = false;
    }

    public void StopMove(){
        Move = false;
    }

    public void StopShoot(){
        Shoot = false;
    }

    public void StartMove(PointF where){
        Move = true;
        MoveHere = where;
    }

    public void StartShoot(PointF where){
        Shoot = true;
        ShootHere = where;
    }


    public PointF getMoveHere() {
        return MoveHere;
    }

    public PointF getShootHere() {
        return ShootHere;
    }

    public boolean isMove() {
        return Move;
    }

    public boolean isShoot() {
        return Shoot;
    }
}
