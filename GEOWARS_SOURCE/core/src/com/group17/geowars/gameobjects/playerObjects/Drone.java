/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.gameobjects.GameObject;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;


public class Drone extends GameObject {
    private int hp;
    private int attack;
    private int level;
    private float angle;
    private String type;
    private Sprite sprite;
    private Texture texture;
    private Account player;
    private Enemy target = null;


    private boolean canShoot = true;
    private float timer = 0;

    public Drone(Vector2 pos, String type, Account player)
    {
        super(pos);
        this.player = player;
        this.type = type;
        texture = Managers.getAssetManager().getTexture("atackdrone");
        sprite = new Sprite(texture,texture.getWidth(),texture.getHeight());
    }
    public Sprite getSprite()
    {
        return sprite;
    }

    public String getType() {
        return type;
    }


    public void render(Batch batch)
    {

        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(20, 20);
        sprite.setOrigin(10, 10);
        sprite.setRotation(90);
        sprite.setPosition(position.x - 10, position.y - 10);
        sprite.draw(batch);
    }
    public void reset()
    {
        target = null;

    }
    public void shoot(Vector2 dir)
    {
        if(canShoot) {
            Managers.getBulletManager().addBullet(new Bullet(position.cpy(), dir));
            canShoot = false;
        }
    }
    public void update() {

        timer += Gdx.graphics.getDeltaTime();
        if(timer > 0.2f) {
            timer %= 0.2f;
            canShoot = true;
        }

        Vector2 shipPos = player.getPlayer().getShip().getPosition();
        Vector2 dist = new Vector2(shipPos.x - getPosition().x, shipPos.y - getPosition().y);
        Vector2 pos = new Vector2(position.x, position.y);

        angle+=2;

        if(dist.len() > 150)
        {
            setPosition(pos.lerp(shipPos, Gdx.graphics.getDeltaTime() * 2));
        }
        else{

            float cos = MathUtils.cosDeg(angle);
            float sin = MathUtils.sinDeg(angle);

            float s = 1.0f;
            if(cos != 0)
                s = 1.0f / cos;

            float o = cos;
            if(sin != 0)
                o =cos / sin;

            Vector2 dir = new Vector2(s, o).nor();

            position = pos.lerp(new Vector2(shipPos.x + dir.x * 250,shipPos.y + dir.y * 250), Gdx.graphics.getDeltaTime() * 1.5f);
        }

        if(target != null)
        {
            if(target.destroy || new Vector2(target.getPosition().x - position.x, target.getPosition().y - position.y).len() > 250)
            {
                target = null;
            }
            else
            {
                Vector2 dir = new Vector2(target.getPosition().x - position.x, target.getPosition().y - position.y).nor();
                shoot(dir);
            }
        }

        if(target == null) {
            float distance = 0;
            for (Enemy e : Managers.getEnemyManager().getEnemies()) {
                float tempDist = new Vector2(e.getPosition().x - position.x, e.getPosition().y - position.y).len();
                if(distance == 0 || tempDist < distance)
                {
                    distance = tempDist;

                    if(tempDist < 250)
                        target = e;
                }
            }
        }

    }
}