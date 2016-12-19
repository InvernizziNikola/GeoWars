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
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.GameObject;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.PowerUp;
import com.group17.geowars.managers.Managers;
import com.badlogic.gdx.files.FileHandle;


import java.util.Random;

/**
 *
 * @author kevin
 */
public abstract class Enemy extends GameObject implements GOInterface {
    protected int hp;
    protected int attack;
    protected Sprite sprite;
    protected Color color;
    protected Vector2 direction;
    protected boolean insidePlayingField = false;
    protected float offset = 200;
    public boolean destroy = false;
    protected Vector2 lookAt = new Vector2(0,0);
    protected ParticleEffect pe;
    protected Vector2 target = new Vector2(0,0);


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
/*
    public void dropPowerUp(int EnemyType)
    {
        //String dropPosition = position;
        //PowerUp pow = new PowerUp(dropPosition);
        // pow naar game scherm doen
    }
    */
    public void handleDead()
    {
        int lootId = 1;
        Geom g = new Geom( lootId,position);
        Managers.getGeomManager().addGeom(g);
        int i = new Random().nextInt(100);
        if(i>98) {
            PowerUp p = new PowerUp("nuke", position);
            Managers.getpowerUpManager().addPowerUp(p);
        }

/*
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal("explosion.party"), Gdx.files.internal(""));
        pe.getEmitters().first().setPosition(position.x, position.y);
        pe.start();
*/
    }

    public Sprite getSprite()
    {
        return sprite;
    }



    @Override
    public void render(Batch batch) {



/*
        if(pe != null) {

            pe.update(Gdx.graphics.getDeltaTime());
            pe.draw(batch);
            return;
        }
*/
        sprite.setColor(color);
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        sprite.setRotation(lookAt.angle());
        sprite.setPosition(position.x -25, position.y-25);
        sprite.draw(batch);
    }




    @Override
    public void update() {

        if(pe != null)
            return;

        if(!insidePlayingField) {
            target = new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 2);
            if (position.x > 1
                    && position.x < Gdx.graphics.getWidth() - 1
                    && position.y > 1
                    && position.y < Gdx.graphics.getHeight() - 1) {
                insidePlayingField = true;
                offset = -0;
                target = Managers.getAccountManager().getAccounts().get(0).getPlayer().getShip().getPosition();
            }
        }
        if(insidePlayingField) {
            if (position.x < -offset || position.x > Gdx.graphics.getWidth() + offset)
                direction.x *= -1;
            if (position.y <= -offset || position.y > Gdx.graphics.getHeight() + offset)
                direction.y *= -1;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        //aggro
        if (dist.len() < 500 || !insidePlayingField)
        {
            lookAt = dist.nor();

        }else
        {
            lookAt = direction.nor();
        }
        position.mulAdd(lookAt.nor(), 125 * Gdx.graphics.getDeltaTime());
    }
}
