/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;


import java.util.Random;

/**
 *
 * @author kevin
 */
public class Enemy extends GameObject implements GOInterface {
    private int EnemyType;
    private int hp;
    private int attack;
    private Sprite sprite;
    private Color color;
    private Vector2 direction;
    private boolean insidePlayingField = false;
    private float offset = 200;
    public boolean destroy = false;
    private Vector2 lookAt = new Vector2(0,0);
    public Enemy(String type,Vector2 spawnLocation) {
        super(new Vector2(0,0));
        position = new Vector2(spawnLocation);
        destroy = false;
        texture = Managers.getAssetManager().getTexture(type+"_2");
        sprite = new Sprite(texture,texture.getWidth(),texture.getHeight());
        color =new Color(0,0,1,1);


        Random rand = new Random();
        direction = new Vector2(rand.nextInt(100) - 50, rand.nextInt(100) - 50).nor();
    }

    public void dropPowerUp(int EnemyType)
    {
        //String dropPosition = position;
        //PowerUp pow = new PowerUp(dropPosition);
        // pow naar game scherm doen
    }
    public void handleDead()
    {
        int lootId = 1;
        Geom g = new Geom( lootId,position);
        Managers.getGeomManager().addGeom(g);
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
        sprite.setRotation(lookAt.angle());
        sprite.setPosition(position.x -25, position.y-25);
        sprite.draw(batch);
    }




    @Override
    public void update() {


        Vector2 target = Managers.getAccountManager().getAccounts().get(0).getProfile().getShip().getPosition(); //closestPlayer;

        if(!insidePlayingField) {
            target = new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 2);
            if (position.x > 1
                    && position.x < Gdx.graphics.getWidth() - 1
                    && position.y > 1
                    && position.y < Gdx.graphics.getHeight() - 1) {
                insidePlayingField = true;
                offset = -0;
            }
        }
        if(insidePlayingField) {
            if (position.x < -offset || position.x > Gdx.graphics.getWidth() + offset)
                direction.x *= -1;
            if (position.y <= -offset || position.y > Gdx.graphics.getHeight() + offset)
                direction.y *= -1;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        if (dist.len() < 800 || !insidePlayingField)
        {
            lookAt = dist.nor();

        }else
        {
            lookAt = direction.nor();
        }
        position.mulAdd(lookAt.nor(),
                50 * Gdx.graphics.getDeltaTime());
    }
}
