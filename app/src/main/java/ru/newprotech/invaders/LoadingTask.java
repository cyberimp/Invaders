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
        GameState state = GameState.getInstance();
//        back = new CBackground(R.drawable.nebula,display.getWidth(),display.getHeight());
        CSpriteManager spriteManager=CSpriteManager.getInstance();
        spriteManager.Clear();
        state.setProgress(10.f);
        CMusicManager musicManager = CMusicManager.getInstance();
        musicManager.init();
//        CHeroBulletManager.init();
        try {
            musicManager.loadSound(R.raw.bullet);
            state.setProgress(20.f);
            musicManager.loadSound(R.raw.explode);
            state.setProgress(30.f);
            musicManager.loadMusic(R.raw.dubmood_hybrid_funky_stars);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state.setProgress(40.f);
        GameContext gameContext = GameContext.getInstance();
        CBackground back = CBackground.getInstance();
        WindowManager wm = (WindowManager) gameContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        back.LoadBackground(R.drawable.nebula,display.getWidth(),display.getHeight());
        state.setProgress(50.f);

        CHero hero = CHero.getInstance();
        CSpritesheetManager ssManager = CSpritesheetManager.getInstance();
        ssManager.loadSheet(R.drawable.warship, 32, 32);
        state.setProgress(60.f);
        ssManager.loadSheet(R.drawable.bullet,8,8);
        ssManager.loadSheet(R.drawable.lightning,8,8);
        ssManager.loadSheet(R.drawable.numbers,8,8);
        ssManager.loadSheet(R.drawable.skull,64,64);
        ssManager.loadSheet(R.drawable.grunt,64,64);
        ssManager.loadSheet(R.drawable.power,16,16);
        ssManager.loadSheet(R.drawable.gameover,185,187);
        state.setProgress(70.f);
        CSprite warship = CSprite.createSprite(R.drawable.warship,0,0);
        warship.setAnimation(100,0,3);
        CMenu.init(new RectF(20,100,display.getWidth()-20,380),3);
        CMenu.createItem(R.drawable.new_game,IGameState.STATE_MAIN);
        CMenu.createItem(R.drawable.tutorial,IGameState.STATE_GAMEOVER);
        CMenu.createItem(R.drawable.options,0);
        hero.setSprite(warship);
        hero.init();
        state.setProgress(100.f);

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
