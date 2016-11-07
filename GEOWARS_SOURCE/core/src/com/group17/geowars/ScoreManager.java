package com.group17.geowars;

import sun.security.jca.GetInstance;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by nikola on 07/11/2016.
 */

public class ScoreManager {

    private static ScoreManager _instance;

    public static ScoreManager GetInstance()
    {
        if(_instance == null)
            _instance = new ScoreManager();

        return _instance;
    }

    private Dictionary<Integer, EnemyLoot> lootTable;

    private ScoreManager ()
    {
        lootTable = new Hashtable<Integer, EnemyLoot>();

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
