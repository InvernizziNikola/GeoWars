package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 20/12/2016.
 */
public class Power_UpPassive extends PowerUp {

    private float fireDelay;
    private int extraHp;
    private int speed;

    public Power_UpPassive(Vector2 pos) {
        super(pos,POWERUPTYPE.PASSIVE);
        text="fireRate++ \n"+"hp++ \n"+"speed++ \n";
        //get out of DB ? DB.getpowerUP(type)
        //this.fireRate = fireRate;
        //this.extraHp = extraHp;
        fireDelay=1.2f;
        extraHp=2;
        speed=50;
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
