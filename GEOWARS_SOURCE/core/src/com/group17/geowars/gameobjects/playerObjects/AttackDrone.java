package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;

/**
 * Created by kevin on 21/12/2016.
 */
public class AttackDrone extends Drone {
    public AttackDrone(Vector2 pos, String type, Account player) {
        super(pos, type, player);
        texture = Managers.getAssetManager().getTexture("attackdrone");
        setSprite(new Sprite(texture, texture.getWidth(), texture.getHeight()));
    }
}