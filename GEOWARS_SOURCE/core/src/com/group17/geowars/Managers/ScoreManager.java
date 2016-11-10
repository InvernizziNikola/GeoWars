package com.group17.geowars.managers;

import com.group17.geowars.database.EnemyLoot;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by nikola on 07/11/2016.
 */

public class ScoreManager {



    private int score;
    private Dictionary<Integer,EnemyLoot> lootTable;

    public ScoreManager ()
    {
        init();
    }


    public void init() {
        score=0;
        lootTable = new Hashtable<Integer, EnemyLoot>();
        lootTable.put(0,new EnemyLoot(1,1,1));

    }

    public int getScore(int id)
    {
        return score;

    }

    public EnemyLoot getLoot(int id)
    {
        return lootTable.get(0);
        // GET DATA FROM Dictionary
    }
}
