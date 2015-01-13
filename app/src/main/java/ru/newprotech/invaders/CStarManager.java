package ru.newprotech.invaders;

/**
 * Created by kinzoxbeato on 07.01.2015.
 */
public class CStarManager extends CThinkerManager {
    private static CStarManager ourInstance = new CStarManager();

    public static CStarManager getInstance() {
        return ourInstance;
    }

    private CStarManager() {
    }

    public static void Fill(){
        ourInstance.Clear();
        for (int i = 0; i < 100; i++) {
            CStar star= new CStar();
            ourInstance.Add(star);
        }
    }
}
