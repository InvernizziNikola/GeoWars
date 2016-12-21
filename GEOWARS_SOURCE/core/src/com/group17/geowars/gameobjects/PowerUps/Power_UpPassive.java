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

    public Power_UpPassive(Vector2 pos) {
        super(pos,POWERUPTYPE.PASSIVE);
        //get out of DB ? DB.getpowerUP(type)
        //this.fireRate = fireRate;
        //this.extraHp = extraHp;
        fireDelay=1.2f;
        color= new Color(0.8f, 0.8f,0,0.6f);
        texture = Managers.getAssetManager().getTexture("dfdgsdgsd");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public float getFireDelay() {
        return fireDelay;
    }
}
