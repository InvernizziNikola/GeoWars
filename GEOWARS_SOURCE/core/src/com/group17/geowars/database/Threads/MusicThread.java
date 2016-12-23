package com.group17.geowars.database.Threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicThread implements Runnable {

    private Thread t;
    public Music music;

    public MusicThread() {
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundmusic.mp3"));
    }

    public boolean finished() {
        if (music != null)
            return true;
        return false;

    }
}
