/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.*;
import com.group17.geowars.gameobjects.PowerUps.PowerDown_Stats;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.gameobjects.PowerUps.PowerUp_Nuke;
import com.group17.geowars.gameobjects.PowerUps.Power_UpPassive;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.ENEMYSTATE;
import com.group17.geowars.utils.ENEMYTYPE;


import java.util.Random;

public abstract class Enemy extends GameObject implements GOInterface {
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
    protected ENEMYSTATE currentState;
    protected float timer;
    protected Texture textureEx;
    protected Sprite spriteEx;

    private float angleEx = 0;

    public Enemy(String type, Vector2 spawnLocation) {

        super(new Vector2(0, 0));
        position = new Vector2(spawnLocation);
        destroy = false;
        texture = Managers.getAssetManager().getTexture(type + "_2");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        textureEx = Managers.getAssetManager().getTexture("explosion_enemy");
        spriteEx = new Sprite(textureEx, textureEx.getWidth(), textureEx.getHeight());

        color = new Color(0, 0, 1, 1);
        size = 50;
        hp = 1;
        maxHp = 1;
        Random rand = new Random();
        direction = new Vector2(rand.nextInt(100) - 50, rand.nextInt(100) - 50).nor();
        currentState = ENEMYSTATE.WARPING_IN;
        timer = 0.5f;
    }

    public void setTexture(String type) {
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

    public void dropexp() {
        int lootId = 1;
        Geom g = new Geom(lootId, position);
        Managers.getGeomManager().addGeom(g);
        int i = new Random().nextInt(300);

        //dropPowerUp
        if (i > 10) {
            PowerUp_Nuke p = new PowerUp_Nuke(position);
            Managers.getpowerUpManager().addPowerUp(p);
            return;
        }
        if (i >= 285 && i < 292) {
            Power_UpPassive p = new Power_UpPassive(position);
            Managers.getpowerUpManager().addPowerUp(p);
            return;
        }
        if (i > 293 && i < 298) {
            PowerDown_Stats p = new PowerDown_Stats(position);
            Managers.getpowerUpManager().addPowerUp(p);
            return;
        }
    }


    public void handleDead() {
        float c = (float) hp / maxHp;

        setColor(new Color((1 - c), 0, c, 1));
        //color = new Color(0, 0, 1, 1);
        if (hp < 1) {

            destroy = true;
            currentState = ENEMYSTATE.DYING;
            dropexp();
        }
        hp--;
    }

    public int getHp() {
        return hp;
    }

    public Sprite getShipSprite() {
        return sprite;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(Batch batch) {

        switch (currentState) {
            case WARPING_IN:
            case FIGHTING: {
                showShip(batch);
                break;
            }
            case DYING: {
                showExplosion(batch);
                break;
            }
        }
    }

    public void showExplosion(Batch batch)
    {
        spriteEx.setColor(1.0f, 0f, 0f, 1.0f - ((1.0f / 2.0f) * timer));
        spriteEx.setSize(size - (size/2 * timer), size - (size/2 * timer));
        spriteEx.setOrigin((size - (size/2 * timer)) / 2, (size - (size/2 * timer))/2);
        spriteEx.setRotation(angleEx);
        spriteEx.setPosition(position.x - (size - (size/2 * timer)) / 2, position.y - (size - (size/2 * timer)) / 2);
        spriteEx.draw(batch);
    }
    public void showShip(Batch batch)
    {
        sprite.setColor(color.r, color.g, color.b, 1.0f - ((1.0f / 0.5f) * timer));
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

        // keep enemies in field
        if (position.x <= 0 || position.x >= GeoWars.ORIGINALWIDTH)
            direction.x *= -1;
        if (position.y <= 0 || position.y >= GeoWars.ORIGINALHEIGHT)
            direction.y *= -1;

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

        switch(currentState)
        {
            case WARPING_IN:
            {
                timer-=Gdx.graphics.getDeltaTime();
                if(timer <= 0) {
                    timer = 0;
                    currentState = ENEMYSTATE.FIGHTING;
                }
                break;
            }
            case FIGHTING:
            {
                fighting();
                break;
            }
            case DYING: {
                timer+= Gdx.graphics.getDeltaTime();
                angleEx+= Gdx.graphics.getDeltaTime() * 300;
                if(timer >= 2) {
                    timer = 2;
                    currentState = ENEMYSTATE.REMOVED;
                }
                break;
            }
            case REMOVED: {
                Managers.getEnemyManager().remove(this);
            }
        }

        keepOnScreen();
    }
    protected void fighting()
    {
        position.mulAdd(lookAt.nor(), speed * Gdx.graphics.getDeltaTime());
    }
}
