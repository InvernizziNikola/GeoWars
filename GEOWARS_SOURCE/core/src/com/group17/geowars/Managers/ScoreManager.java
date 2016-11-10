package com.group17.geowars.managers;

import com.group17.geowars.database.EnemyLoot;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by nikola on 07/11/2016.
 */

public class ScoreManager {



    private Dictionary<Integer, EnemyLoot> lootTable;

    public ScoreManager ()
    {
        lootTable = new Hashtable<Integer, EnemyLoot>();
    }


    public void init()
    {
        // GET LOOTTABLE FROM DATABASE AND INSERT IN HASHTABLE
        // HACK
        lootTable.put(1, new EnemyLoot(1,1,1));
        lootTable.put(2, new EnemyLoot(2,2,2));
        lootTable.put(3, new EnemyLoot(3,3,3));
        lootTable.put(4, new EnemyLoot(4,4,4));
    }

    public EnemyLoot getLoot(int id)
    {
        return lootTable.get(id);
        // GET DATA FROM Dictionary
    }
}
