package com.group17.geowars.database.Threads;

import com.badlogic.gdx.Gdx;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.screens.MenuScreen;

import java.util.ArrayList;

/**
 * Created by nikola on 06/12/2016.
 */


/*
* -------------HOW TO GET THE ARRAYLIST-------------
*  public void getHighScore(String gameMode)
    {
        if(loading)
            return;

        loading = true;
        hsT = new HighScoreMenuThread(gameMode);
        hsT.start();
    }
*
* */
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
        DBManager manager = new DBManager();

        data = manager.DBselectTOP10Highscore(gameMode);
    }

    public ArrayList getData()
    {
        return data;
    }

    public boolean finished()
    {
        if(data != null)
            return true;
        return false;
    }
}
