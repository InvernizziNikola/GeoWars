/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.GameObject;
import com.group17.geowars.managers.Managers;

/**
 *
 * @author kevin
 */
public abstract class PowerUp extends GameObject implements GOInterface {
    protected Sprite sprite;
    protected Color color;
    private boolean destroy = false;

    public PowerUp(Vector2 pos) {
        super(pos);

    }


    public Sprite getSprite()
    {
        return sprite;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    @Override
    public void render(Batch batch) {
        sprite.setColor(color);
        sprite.setSize(30, 30);
        sprite.setOrigin(15,15);
        sprite.setPosition(position.x-15,position.y-15);
        sprite.draw(batch);
    }

    @Override
    public void update() {

    }
}
