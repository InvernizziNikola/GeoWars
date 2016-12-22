package com.group17.geowars.DataObjects;

import com.group17.geowars.database.Threads.HighScoreMenuThread;
import com.group17.geowars.database.Threads.LoadEnemyThread;
import com.group17.geowars.utils.ENEMYTYPE;

import java.util.ArrayList;

/**
 * Created by nikola on 22/12/2016.
 */
public class EnemyProfile {

    public String name;
    public ENEMYTYPE type;
    public String imageName;
    public int health;
    public float fireDelay;
    public float fireRange;
    public float speed;
    private ArrayList Enemys;
    private LoadEnemyThread LET;

    public EnemyProfile()
    {

        this.name = name;
        this.type = ENEMYTYPE.valueOf(type);
        this.imageName = imageName;
        this.health = health;
        this.fireDelay = fireDelay;
        this.fireRange = fireRange;
        this.speed = speed;
    }
    public void getEnemyData()
    {
        /*if(loading)
            return;

        loading = true;*/
        LET = new LoadEnemyThread();
        LET.start();
        Enemys=LET.getEnemys();
        Enemys.get(0);//naam
        //Enemys.get(X)

    }
}
