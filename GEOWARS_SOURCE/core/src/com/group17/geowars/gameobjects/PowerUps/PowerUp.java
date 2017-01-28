/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.GameObject;
import com.group17.geowars.managers.Managers;

/**
 * @author kevin
 */
public abstract class PowerUp extends GameObject implements GOInterface {
    protected Sprite sprite;
    protected Color color;
    protected POWERUPTYPE type;
    protected String text;
    private float extrasize = 0;
    private float mult = 1;
    private boolean destroy = false;

    public PowerUp(Vector2 pos, POWERUPTYPE type) {
        super(new Vector2(pos));
        color= Color.CYAN;
        this.type=type;

    }

    public void activate()
    {
        System.out.println("SHOULDNT HAPPEN WITH NUKES");
        Managers.getpowerUpManager().removePowerUp(this);
    }

    public Sprite getShipSprite() {
        return sprite;
    }

    public POWERUPTYPE getType() {
        return type;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public String getText() {
        return text;
    }

    @Override
    public void render(Batch batch) {


        sprite.setColor(color);
        sprite.setSize(30 + extrasize, 30 + extrasize);
        sprite.setOrigin(15 + (extrasize / 2.0f), 15 + (extrasize / 2.0f));
        sprite.setPosition(position.x - (30 + extrasize) /2.0f , position.y - (30 + extrasize) / 2.0f);
        sprite.draw(batch);
    }

    @Override
    public void update() {
        extrasize += Gdx.graphics.getDeltaTime() * mult * 10;
        if(extrasize >= 10) {
            extrasize = 10;
            mult *= -1;
        }
        if(extrasize <= 0) {
            extrasize = 0;
            mult *= -1;
        }
    }
}
