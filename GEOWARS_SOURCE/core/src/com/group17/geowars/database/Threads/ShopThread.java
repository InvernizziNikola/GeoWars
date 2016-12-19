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


    private ArrayList data = null;
    private String Shipname = "";
    private Thread t;

    public ShopThread(String Shipname)
    {
        this.Shipname = Shipname;
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
        data = manager.DBselectShip(Shipname);



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
