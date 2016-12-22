package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 21/12/2016.
 */
public class TankShip extends Ship {
    public TankShip(Vector2 pos, String type) {
        super(pos,"TankShip");
        maxHp=50;
        hp=maxHp;
        fireDelay =0.3f;
        speed=200;
    }


}
