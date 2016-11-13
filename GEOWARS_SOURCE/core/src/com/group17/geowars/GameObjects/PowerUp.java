/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 *
 * @author kevin
 */
public class PowerUp extends GameObject implements GOInterface {
    private Sprite sprite;
    private Color color;
    public boolean destroy = false;

    public PowerUp(String type,Vector2 pos) {
        super(pos);
    color= new Color(0.8f, 0.8f,0,0.6f);
        texture = Managers.getAssetManager().getTexture("powerup");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }


    public Sprite getSprite()
    {
        return sprite;
    }


    @Override
    public void render(Batch batch) {
        sprite.setColor(color);
        sprite.setSize(30, 30);
        sprite.setOrigin(15,15);
        sprite.setPosition(position.x-25,position.y-25);
        sprite.draw(batch);
    }

    @Override
    public void update() {

    }
}
