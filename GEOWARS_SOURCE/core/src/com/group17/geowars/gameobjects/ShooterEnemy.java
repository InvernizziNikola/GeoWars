package com.group17.geowars.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by kevin on 19/12/2016.
 */
public class ShooterEnemy extends Enemy  {
    private int EnemyType;
    private int hp;
    private int attack;
    public ShooterEnemy(String type,Vector2 spawnLocation)
    {
        super("fighter",new Vector2(0,0));
    }
}
