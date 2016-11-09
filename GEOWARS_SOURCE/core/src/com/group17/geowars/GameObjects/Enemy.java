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

/**
 *
 * @author kevin
 */
public class Enemy extends GameObject implements GOInterface {
    private int EnemyType;
    private int hp;
    private int attack;
    private boolean dead;
    private Vector2 position; //private Vector2 position;
    private Sprite sprite;

    public Enemy(String type,Vector2 spawnLocation) {
        super(new Vector2(0,0));
        position =spawnLocation;
        dead = false;
        Texture img = new Texture(type+"_2.png");
        sprite = new Sprite(img,img.getWidth(),img.getHeight());
    }
    
    public void dropPowerUp(int EnemyType)
    {
        //String dropPosition = position;
        //PowerUp pow = new PowerUp(dropPosition);
        // pow naar game scherm doen
    }
    public Sprite getSprite()
    {
        return sprite;
    }


    @Override
    public void render(Batch batch) {

        sprite.setColor(new Color(0,0,1,1));
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        //sprite.setRotation(difference.angle());
        sprite.setPosition(position.x -25, position.y-25);
        sprite.draw(batch);
    }

    @Override
    public void update() {

    }
}
