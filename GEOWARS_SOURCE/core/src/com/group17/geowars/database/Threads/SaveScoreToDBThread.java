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
    private boolean Succes;

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
        Succes = DBManager.getInstance().DBInsertHighscore(Playername,Score,gameMode);
        while (Succes=false){
            System.out.println("still trying");
            DBManager.getInstance().DBInsertHighscore(Playername,Score,gameMode);
        }
                //User user = w9DA.getInstance().getUser(login, password);
    }

    public boolean Succes()
    {
        return data;
    }

    public boolean finished()
    {
        if(data != null&&Succes) {
            return true;
        }else{
            run();
        return false;
    }
    }
}
