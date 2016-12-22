package com.group17.geowars.database.Threads;

import com.badlogic.gdx.Gdx;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.screens.MenuScreen;

import java.util.ArrayList;

public class HighScoreMenuThread implements Runnable {


    private ArrayList data = null;
    private String gameMode = "";
    private Thread t;

    public HighScoreMenuThread(String gameMode)
    {
        this.gameMode = gameMode;
    }

    public void start()
    {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    @Override
    public void run() {

        data = DBManager.getInstance().DBselectTOP10Highscore(gameMode);
    }

    public ArrayList getData()
    {
        return data;
    }
    Integer i = 0;
    public boolean finished()
    {
        if(data != null||i>300)
            return true;
        i++;
        return false;
    }
}
