package com.group17.geowars.database.Threads;

import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.utils.ENEMYTYPE;

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
    private ArrayList<EnemyProfile> enemyProfiles =null;
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
            Enemys = DBManager.getInstance().DBselectEnemy(Name);
        }
        else {
            Enemys = DBManager.getInstance().DBSelectAllEnemys();
        }

        enemyProfiles = new ArrayList<EnemyProfile>();

        Integer CollumCount = 12;
        rows=Enemys.size()/CollumCount;

        for (int i=0;i==(rows-1);i++){

            EnemyProfile ep = new EnemyProfile();

            ep.name = Enemy.get((i*CollumCount)+0).toString();
            ep.type = ENEMYTYPE.valueOf(Enemy.get((i*CollumCount)+1).toString());
            ep.imageName = Enemy.get((i*CollumCount)+2).toString();
            ep.health = (Integer) Enemy.get((i*CollumCount)+3);
            ep.fireDelay = (Integer) Enemy.get((i*CollumCount)+4);
            ep.fireRange = (Integer) Enemy.get((i*CollumCount)+5);
            ep.speed = (Integer) Enemy.get((i*CollumCount)+6);
            ep.difficultyGrade = (Integer) Enemy.get((i*CollumCount)+7);
            ep.Spread = (Integer) Enemy.get((i*CollumCount)+8);
            ep.red = (Integer) Enemy.get((i*CollumCount)+8);
            ep.green = (Integer) Enemy.get((i*CollumCount)+8);
            ep.blue = (Integer) Enemy.get((i*CollumCount)+8);

            enemyProfiles.add(ep);
        }
    }

    public ArrayList getEnemies()
    {
        return enemyProfiles;
    }

    public boolean finished()
    {
        if(enemyProfiles != null)
            return true;
        return false;
    }
}
