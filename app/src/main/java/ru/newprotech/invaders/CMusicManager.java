package ru.newprotech.invaders;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;


/**
 * Created by 6003 on 02.02.2015.
 */
public class CMusicManager {
    private static CMusicManager instance = new CMusicManager();
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;
    private HashMap<Integer,Integer> collection;
    private final Object loadMonitor;
    private boolean musicPlaying = false;

    public static CMusicManager getInstance() {
        return instance;
    }

    CMusicManager(){
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        collection = new HashMap<>();
        loadMonitor = new Object();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int mySoundId, int status) {
                synchronized (loadMonitor) {
                    loadMonitor.notify();
                }
            }
        });
    }
    public synchronized void loadSound(int res) throws InterruptedException {
        if (!collection.containsKey(res)){
            int temp = soundPool.load(GameContext.getContext(),res,1);
            synchronized (loadMonitor) {
                loadMonitor.wait();
            }
            collection.put(res,temp);
        }

    }

    public synchronized void playMusic(){
        musicPlaying = true;
        mediaPlayer.start();
    }

    public synchronized void stopMusic(){
        if (musicPlaying) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
        musicPlaying = false;
    }

    public synchronized void playSound(int res){
        if (collection.containsKey(res)){
            soundPool.play(collection.get(res),1.f,1.f,1,0,1.f);
        }
    }

    public synchronized void loadMusic(int res){
        mediaPlayer = MediaPlayer.create(GameContext.getContext(),res);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
    }

    public synchronized void pauseAll(){
        soundPool.autoPause();
        if (musicPlaying)
            mediaPlayer.pause();
    }

    public synchronized void resumeAll(){
        soundPool.autoResume();
        if (musicPlaying)
            mediaPlayer.start();
    }

}
