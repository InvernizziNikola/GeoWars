package com.group17.geowars.DataObjects;

import com.group17.geowars.utils.ENEMYTYPE;

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

    public EnemyProfile(String name, String type, String imageName, int health, float fireDelay, float fireRange, float speed)
    {
        this.name = name;
        this.type = ENEMYTYPE.valueOf(type);
        this.imageName = imageName;
        this.health = health;
        this.fireDelay = fireDelay;
        this.fireRange = fireRange;
        this.speed = speed;
    }
}
