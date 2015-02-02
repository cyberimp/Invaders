package ru.newprotech.invaders;

import android.content.Context;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
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
        CSpriteManager spriteManager=CSpriteManager.getInstance();
        spriteManager.Clear();
        CMusicManager musicManager = CMusicManager.getInstance();
//        CHeroBulletManager.init();
        params[0] = 0.f;
        try {
            musicManager.loadSound(R.raw.bullet);
            musicManager.loadSound(R.raw.explode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        ssManager.loadSheet(R.drawable.lightning,8,8);
        ssManager.loadSheet(R.drawable.numbers,8,8);
        ssManager.loadSheet(R.drawable.skull,64,64);
        ssManager.loadSheet(R.drawable.grunt,64,64);
        ssManager.loadSheet(R.drawable.power,16,16);
        ssManager.loadSheet(R.drawable.gameover,185,187);
        CSprite warship = CSprite.createSprite(R.drawable.warship,0,0);
        warship.setAnimation(100,0,3);
        CMenu.init(new RectF(20,100,display.getWidth()-20,380),3);
        CMenu.createItem(R.drawable.new_game,IGameState.STATE_MAIN);
        CMenu.createItem(R.drawable.tutorial,IGameState.STATE_GAMEOVER);
        CMenu.createItem(R.drawable.options,0);
        hero.setSprite(warship);
        hero.init();
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
