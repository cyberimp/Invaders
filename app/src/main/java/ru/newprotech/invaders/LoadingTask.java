package ru.newprotech.invaders;

import android.content.Context;
import android.os.AsyncTask;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by kinzoxbeato on 28.12.2014.
 */

/**
 * Task for loading resources in separate thread
 */
class LoadingTask extends AsyncTask<Float,Integer,Void> {
    @Override
    protected Void doInBackground(Float... params) {
//        back = new CBackground(R.drawable.nebula,display.getWidth(),display.getHeight());
        CSpriteManager.init();
        CHeroBulletManager.init();
        params[0] = 0.f;
        GameContext gameContext = GameContext.getInstance();
        CBackground back = CBackground.getInstance();
        WindowManager wm = (WindowManager) gameContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        back.LoadBackground(R.drawable.nebula,display.getWidth(),display.getHeight());
        params[0] = 50.f;

        CHero hero = CHero.getInstance();
        CSpritesheetManager ssManager = CSpritesheetManager.getInstance();
        ssManager.loadSheet(R.drawable.warship, 32, 32);
        ssManager.loadSheet(R.drawable.bullet,8,8);
        ssManager.loadSheet(R.drawable.skull,64,64);
        CSprite warship = CSpriteManager.createSprite(R.drawable.warship,160,450);
        warship.setAnimation(100,0,3);
        hero.setSprite(warship);
        params[0] = 100.f;

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
