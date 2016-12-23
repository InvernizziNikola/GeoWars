package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.math.BigInteger;
import java.util.ArrayList;

public class SaveScoreToDBThread implements Runnable {


    private Boolean data = false;
    private String gameMode = "";
    private String Playername = "";
    private BigInteger Score= BigInteger.valueOf(0);
    private Thread t;
    private boolean Succes;

    public SaveScoreToDBThread(String Playername, BigInteger Score, String gameMode)
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
            data = DBManager.getInstance().DBInsertHighscore(Playername,Score,gameMode);
        }
    }

    public boolean Succes()
    {
        return Succes;
    }
    public boolean finished()
    {
        if(data != null)
            return true;
        return false;

    }
}
