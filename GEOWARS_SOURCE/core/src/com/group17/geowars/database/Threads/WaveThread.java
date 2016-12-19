package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

/**
 * Created by nikola on 06/12/2016.
 */


/*
* -------------HOW TO GET THE ARRAYLIST-------------

     private HighScoreMenuThread hsT;
  public void getHighScore(String gameMode)
    {
        hsT = new HighScoreMenuThread(gameMode);
        hsT.start();
    }
*
* */
public class WaveThread implements Runnable {


    private ArrayList data = null;
    private Integer WaveNumber = 0;
    private Thread t;

    public WaveThread(Integer WaveNumber)
    {
        this.WaveNumber = WaveNumber;
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

        data = manager.DBselectWaveData(WaveNumber);
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
