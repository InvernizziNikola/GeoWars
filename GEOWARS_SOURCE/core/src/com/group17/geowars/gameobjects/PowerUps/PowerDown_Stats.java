package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 23/12/2016.
 */
public class PowerDown_Stats extends PowerUp {

    private float fireDelay;
    private int extraHp;
    private int speed;

    public PowerDown_Stats(Vector2 pos) {
        super(pos, POWERUPTYPE.POWERDOWN);
        color= Color.SCARLET;

        text="fireRate++ \n"+"hp++ \n"+"speed++ \n";
        //get out of DB ? DB.getpowerUP(type)
        //this.fireRate = fireRate;
        //this.extraHp = extraHp;
        fireDelay=0.9f;
        extraHp=-1;
        speed=-20;
        texture = Managers.getAssetManager().getTexture("itsagem:)");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public float getFireDelay() {
        return fireDelay;
    }

    public int getExtraHp() {
        return extraHp;
    }

    public int getSpeed() {
        return speed;
    }
}
