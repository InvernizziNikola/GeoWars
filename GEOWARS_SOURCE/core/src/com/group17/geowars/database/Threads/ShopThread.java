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
public class ShopThread implements Runnable {


    private ArrayList dataShips = null;
    private ArrayList dataDrones = null;
    private Thread t;

    public ShopThread()
    {

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
        dataShips = DBManager.getInstance().DBselectAllShips();
        dataDrones = DBManager.getInstance().DBselectAllDrones();



    }

    public ArrayList getShipData()
    {


        return dataShips;
    }
    public ArrayList getDronesData()
    {


        return dataDrones;
    }

    public boolean finished()
    {
        if(dataDrones != null && dataShips !=null)
            return true;
        return false;
    }
}
