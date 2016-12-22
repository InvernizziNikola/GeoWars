package com.group17.geowars.database.Threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class MusicThread implements Runnable {

    private Thread t;
    protected Sound sound;

    public MusicThread(){}

    public void start()
    {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    @Override
    public void run() {
        Music music = Gdx.audio.newMusic(Gdx.files.internal("sounds/backgroundmusic.mp3"));
        music.play();
        music.setLooping(true);
    }


    Integer i =0;

}
