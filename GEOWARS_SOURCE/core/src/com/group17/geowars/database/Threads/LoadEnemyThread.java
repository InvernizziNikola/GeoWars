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
public class LoadEnemyThread implements Runnable {


    private ArrayList Enemy = null;
    private ArrayList Enemys=null;
    private String Name = null;
    private Thread t;

    public LoadEnemyThread(String Name)
    {
        this.Name = Name;
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
        if(Name!=null){
            Enemy = DBManager.getInstance().DBselectEnemy(Name);
        }

        Enemys = DBManager.getInstance().DBSelectAllEnemys();

    }

    public ArrayList getEnemy()
    {
        return Enemy;
    }
    public ArrayList getEnemys()
    {
        return Enemys;
    }


    public boolean finished()
    {
        if(Enemy != null&&Enemys != null)
            return true;
        return false;
    }
}
