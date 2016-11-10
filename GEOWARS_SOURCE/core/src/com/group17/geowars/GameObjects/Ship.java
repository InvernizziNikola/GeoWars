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

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kevin
 */
public class Ship extends GameObject implements GOInterface { //interface shoot?    extends DynamicGameObject ?
    private int hp;
    private int attack;
    private boolean dead;
    private int exp;
    private int score;
    private int multiplier;

    private String type;
    private Sprite sprite;

    private Vector2 shootDir = new Vector2(0,0);
    private Vector2 moveDir = new Vector2(0,0);
    private Vector2 lookDir = new Vector2(0,0);

    public Ship(Vector2 pos, String type)
    {
        super(pos);

        texture = Managers.getAssetManager().getTexture("Speler_2");

        this.type = type;
        dead = false;
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public String getType() {
        return type;
    }
    public Sprite getSprite()
    {
        return sprite;
    }

    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(50, 50);
        sprite.setOrigin(25, 25);
        sprite.setRotation(lookDir.angle());
        sprite.setPosition(position.x - 25, position.y - 25);
        sprite.draw(batch);
    }

    public void shoot()
    {
        Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(shootDir)));
    }

    public void handlePickedUp(Geom geom)
    {
        exp +=geom.getLoot().getExperience();
        score +=geom.getLoot().getScorePoints();
        multiplier += geom.getLoot().getMultiplier();

    }




    public void nuke()
    {
        Managers.getBulletManager().clearAll();
    }

    @Override
    public void update()
    {
        if(shootDir.len() > 0.1f)
        {
            shoot();
            lookDir = shootDir;
        }
        else if (moveDir.len() > 0.1f)
        {
            lookDir = moveDir;
        }

////////////////////////////////
        List<Geom> geomList = Managers.getGeomManager().getGeomList();
        List<Geom> toRemove = new ArrayList<Geom>();
        boolean toRemove2 = false;
        for (Geom b: geomList) {
            Vector2 distance = new Vector2(b.getPosition().x - getPosition().x, b.getPosition().y - getPosition().y);
            if(distance.len() < 25)
            {
                toRemove.add(b);
                handlePickedUp(b);
                Managers.getGeomManager().removeGeom(b);
                toRemove2 = true;
                break;
            }
        }
        geomList.removeAll(toRemove);

        /////////////////////////////////////////


        System.out.println(score);
        Move();
    }

    void Move()
    {
        position.mulAdd(moveDir, 200 * Gdx.graphics.getDeltaTime());
    }

    public void setMoveDirection(Vector2 dir)
    {
        moveDir = dir;
    }
    public void setShootDirection(Vector2 dir) {
        shootDir = dir;
    }
}

