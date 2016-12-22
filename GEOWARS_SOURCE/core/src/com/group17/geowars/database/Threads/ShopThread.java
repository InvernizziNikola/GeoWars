package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

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
    Integer i = 0;
    public boolean finished()
    {
        if(dataDrones != null && dataShips !=null||i>300)
            return true;
        i++;
        return false;
    }
}
