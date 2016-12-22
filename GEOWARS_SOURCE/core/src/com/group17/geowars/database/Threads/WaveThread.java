package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

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

        data = DBManager.getInstance().DBselectWaveData(WaveNumber);
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
