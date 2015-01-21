package ru.newprotech.invaders;

/**
 * Created by 6003 on 21.01.2015.
 */
public class CScoreManager extends CThinkerManager {
    private static CScoreManager ourInstance = new CScoreManager();

    public static CScoreManager getInstance() {
        return ourInstance;
    }

    private CScoreManager() {
    }

    public static CScore createScore(float x, float y, int value){
        CScore score = new CScore(x,y,value,R.drawable.numbers);
        ourInstance.Add(score);
        return score;

    }
}
