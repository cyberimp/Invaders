package ru.newprotech.invaders;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends ActionBarActivity {

//    ThinkerThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        super.onCreate(savedInstanceState);
        GameContext cont = GameContext.getInstance();
        cont.setCont(this.getApplicationContext());
        GameView gameView = new GameView(this);
        setContentView(gameView);
        GameState state = GameState.getInstance();
        state.init();
 //       thread = new ThinkerThread();
//        thread.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GameState state = GameState.getInstance();
//        state.init();
        state.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        ScriptThread thread = ScriptThread.getInstance();
//        thread.interrupt();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GameState state = GameState.getInstance();
        state.pause();
//        thread.setRunning(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameState state = GameState.getInstance();
        state.resume();
 //       if (!thread.isRunning()) {
//            thread.setRunning(true);
//            thread.unlock();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        GameState state = GameState.getInstance();
        if (state.getState() == IGameState.STATE_LOADING || state.getState() == IGameState.STATE_MENU)
            super.onBackPressed();
        else
            state.change(IGameState.STATE_MENU);
    }
}
