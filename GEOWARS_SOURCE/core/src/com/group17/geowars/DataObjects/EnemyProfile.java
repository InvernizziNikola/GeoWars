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
    public Integer difficultyGrade;
    public Integer Spread;
    public Integer red;
    public Integer green;
    public Integer blue;

    public EnemyProfile()
    {
        this.type = ENEMYTYPE.valueOf(type);
    }

}
