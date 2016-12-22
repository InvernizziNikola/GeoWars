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
    private ArrayList BuildedArray=null;
    private ArrayList BuildedSingleArray=null;
    private ArrayList Enemys=null;
    private String Name = null;
    private Integer rows;
    private Thread t;

    public LoadEnemyThread(String Name)
    {
        this.Name = Name;
    }
    public LoadEnemyThread(){}

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
        String Name = Enemy.get(0).toString();
        String Type = Enemy.get(1).toString();
        String Image = Enemy.get(2).toString();
        Integer MaxHP = (Integer) Enemy.get(3);
        Integer FireDelay = (Integer) Enemy.get(4);
        Integer FireRange = (Integer) Enemy.get(5);
        Integer Speed = (Integer) Enemy.get(6);
        Integer DifficultyGrade = (Integer) Enemy.get(7);
        Integer Spread = (Integer) Enemy.get(8);
        BuildedSingleArray.add(Name);
        BuildedSingleArray.add(Type);
        BuildedSingleArray.add(Image);
        BuildedSingleArray.add(MaxHP);
        BuildedSingleArray.add(FireDelay);
        BuildedSingleArray.add(FireRange);
        BuildedSingleArray.add(Speed);
        BuildedSingleArray.add(DifficultyGrade);
        BuildedSingleArray.add(Spread);
        return BuildedSingleArray;
    }
    public ArrayList getEnemys()
    {
        rows=Enemys.size()/9;
        for (int i=0;i==(rows-1);i++){
            String Name = Enemy.get((i*9)+0).toString();
            String Type = Enemy.get((i*9)+1).toString();
            String Image = Enemy.get((i*9)+2).toString();
            Integer MaxHP = (Integer) Enemy.get((i*9)+3);
            Integer FireDelay = (Integer) Enemy.get((i*9)+4);
            Integer FireRange = (Integer) Enemy.get((i*9)+5);
            Integer Speed = (Integer) Enemy.get((i*9)+6);
            Integer DifficultyGrade = (Integer) Enemy.get((i*9)+7);
            Integer Spread = (Integer) Enemy.get((i*9)+8);
            BuildedArray.add(Name);
            BuildedArray.add(Type);
            BuildedArray.add(Image);
            BuildedArray.add(MaxHP);
            BuildedArray.add(FireDelay);
            BuildedArray.add(FireRange);
            BuildedArray.add(Speed);
            BuildedArray.add(DifficultyGrade);
            BuildedArray.add(Spread);
        }
        return BuildedArray;
    }


    public boolean finished()
    {
        if(Enemy != null&&Enemys != null)
            return true;
        return false;
    }
}
