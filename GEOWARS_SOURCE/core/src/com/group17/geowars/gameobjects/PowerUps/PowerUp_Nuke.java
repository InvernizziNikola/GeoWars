package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 20/12/2016.
 */
public class PowerUp_Nuke extends PowerUp {

    public PowerUp_Nuke(Vector2 pos) {
        super(pos,POWERUPTYPE.NUKE);
        color= new Color(0.8f, 0.8f,0,0.6f);
        texture = Managers.getAssetManager().getTexture("powerup");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

    }
}
