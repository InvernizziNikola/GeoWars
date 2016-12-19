package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

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
public class SaveScoreToDBThread implements Runnable {


    private Boolean data = false;
    private String gameMode = "";
    private String Playername = "";
    private Integer Score=0;
    private Thread t;

    public SaveScoreToDBThread(String Playername,Integer Score,String gameMode)
    {
        this.Score = Score;
        this.gameMode = gameMode;
        this.Playername = Playername;
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

        data = manager.DBInsertHighscore(Playername,Score,gameMode);
    }

    public boolean Succes()
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
