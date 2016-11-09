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
import com.group17.geowars.managers.BulletManager;
import com.group17.geowars.managers.EnemyManager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Enemy extends GameObject implements GOInterface {
    private int EnemyType;
    private int hp;
    private int attack;
    private boolean dead;
    private Sprite sprite;
    private Color color;

    public Enemy(String type,Vector2 spawnLocation) {
        super(new Vector2(0,0));
        position =spawnLocation;
        dead = false;
        Texture img = new Texture(type+"_2.png");
        sprite = new Sprite(img,img.getWidth(),img.getHeight());
        color =new Color(0,0,1,1);
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

        sprite.setColor(color);
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        //sprite.setRotation(difference.angle());
        sprite.setPosition(position.x -25, position.y-25);
        sprite.draw(batch);
    }

    @Override
    public void update() {

        List<Bullet> bulletList = BulletManager.GetInstance().getBullets();
        List<Bullet> toRemove = new ArrayList<Bullet>();
        for (Bullet b: bulletList) {
            Vector2 newV = new Vector2(b.getPosition().x - getPosition().x, b.getPosition().y - getPosition().y);
            if(newV.len() < 25){
                toRemove.add(b);
                color =new Color(0,0,0,1);
                EnemyManager.GetInstance().removeEnemy(this);
            }
        }
        bulletList.removeAll(toRemove);
    }
}
