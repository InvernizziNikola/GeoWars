package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;

/**
 * Created by kevin on 21/12/2016.
 */
public class AttackDrone extends Drone {
    public AttackDrone(Vector2 pos) {
        super(pos, "attackdrone");
        size=30;
        movementSpeed=350;
        fireRange=500;
        fireDelay=0.1f;

    }
}
