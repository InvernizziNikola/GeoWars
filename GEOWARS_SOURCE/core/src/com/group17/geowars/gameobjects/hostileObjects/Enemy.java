/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.*;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.gameobjects.PowerUps.PowerUp_Nuke;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;


import java.util.Random;

/**
 * @author kevin
 */
public abstract class Enemy extends GameObject implements GOInterface {
    protected Sprite sprite;
    protected Color color;
    protected Vector2 direction;
    public boolean destroy = false;
    protected Vector2 lookAt = new Vector2(0, 0);
    protected ParticleEffect pe;
    protected Player target = null;
    protected int speed = 125;
    protected int size;
    protected int maxHp;
    protected int hp;
    protected float firedelay;
    protected int fireRange;


    public Enemy(String type, Vector2 spawnLocation) {

        super(new Vector2(0, 0));
        position = new Vector2(spawnLocation);
        destroy = false;
        texture = Managers.getAssetManager().getTexture(type + "_2");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
        color = new Color(0, 0, 1, 1);
        size = 50;
        hp = 1;
        maxHp=1;
        Random rand = new Random();
        direction = new Vector2(rand.nextInt(100) - 50, rand.nextInt(100) - 50).nor();

    }
    public void setTexture(String type)
    {
        texture = Managers.getAssetManager().getTexture(type + "_2");
    }

    /*
        public void dropPowerUp(int EnemyType)
        {
            //String dropPosition = position;
            //PowerUp pow = new PowerUp(dropPosition);
            // pow naar game scherm doen
        }
        */

    public void handleDead(Enemy e, Bullet b) {

        float c =(float)hp/maxHp ;
        System.out.println(c);
        setColor(new Color((1-c),0,c,1));
        //color = new Color(0, 0, 1, 1);
        if(hp<1) {
            int lootId = 1;
            Geom g = new Geom(lootId, position);
            Managers.getGeomManager().addGeom(g);
            int i = new Random().nextInt(100);
            //dropPowerUp
            if (i > 98) {
                PowerUp p = new PowerUp_Nuke(position);
                Managers.getpowerUpManager().addPowerUp(p);
            }
            Managers.getEnemyManager().remove(e);
        }
        Managers.getBulletManager().remove(b);
        hp--;
/*
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("explosion.party"), Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(position.x, position.y);
        pe.start();
*/
}

    public Sprite getSprite() {
        return sprite;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(Batch batch) {

        sprite.setColor(color);
        sprite.setSize(size, size);
        sprite.setOrigin(size / 2, size / 2);
        sprite.setRotation(lookAt.angle());
        sprite.setPosition(position.x - size / 2, position.y - size / 2);
        sprite.draw(batch);
    }

    public void setSize(int size) {
        this.size = size;
        sprite.setOrigin(size / 2, size / 2);//TODO remove?
    }


    public int getSize() {
        return size;
    }

    public void keepOnScreen()
    {
        if (position.x < 0)
            position.x = 0;

        if(position.x > GeoWars.ORIGINALWIDTH)
            position.x = GeoWars.ORIGINALWIDTH;

        if (position.y < 0)
            position.y = 0;

        if(position.y > GeoWars.ORIGINALHEIGHT)
            position.y = GeoWars.ORIGINALHEIGHT;

    }

    public Player findTarget()
    {
        Player newTarget = null;
        float dst = 0;
        for(Player p : Managers.getPlayerManager().getPlayers())
        {
            if(newTarget == null) {
                dst = getPosition().dst(p.getShip().getPosition());
                newTarget = p;
            }else
            {
                float newDst = getPosition().dst(p.getShip().getPosition());
                if(dst > newDst)
                {
                    dst = newDst;
                    newTarget = p;
                }
            }
        }
        return newTarget;
    }

    @Override
    public void update() {

        // keep enemies in field
        if (position.x < 0 || position.x > GeoWars.ORIGINALWIDTH)
            direction.x *= -1;
        if (position.y <= 0 || position.y > GeoWars.ORIGINALHEIGHT)
            direction.y *= -1;

        position.mulAdd(lookAt.nor(), speed * Gdx.graphics.getDeltaTime());

        keepOnScreen();
    }
}
