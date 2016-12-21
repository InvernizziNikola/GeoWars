package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 21/12/2016.
 */
public class AssaultShip extends Ship {
    public AssaultShip(Vector2 pos, String type) {
        super(pos,"Assault");
        hp=20;
        fireDelay =0.15f;
        speed=450;
    }
}
