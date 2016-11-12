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
import com.group17.geowars.playerobjects.Profile;
import com.sun.xml.internal.ws.dump.LoggingDumpTube;


import java.util.ArrayList;
import java.util.List;
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

    public Enemy(String type,Vector2 spawnLocation) {
        super(new Vector2(0,0));
        position =spawnLocation;
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
        //sprite.setRotation(difference.angle());
        sprite.setPosition(position.x -25, position.y-25);
        sprite.draw(batch);
    }




    @Override
    public void update() {


        if(!insidePlayingField) {
            if (position.x < -offset || position.x > Gdx.graphics.getWidth() + offset)
                direction.x *= -1;
            if (position.y < -offset || position.y > Gdx.graphics.getHeight() + offset)
                direction.y *= -1;
        }
        
        if(insidePlayingField
                && position.x > 0
                && position.x < Gdx.graphics.getWidth()
                && position.y > 0
                && position.y < Gdx.graphics.getHeight())
        {
            offset = 0;
        }


        Vector2 target = Managers.getProfileManager().getProfiles().get(0).getPlayer().getShip().getPosition(); //closestPlayer;
        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        if (dist.len() < 300)
        {
            position.mulAdd(dist.nor(),
                    50 * Gdx.graphics.getDeltaTime());

        }else
        {
            position.mulAdd(direction.nor(),
                    50 * Gdx.graphics.getDeltaTime());
        }
    }
}
