/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;



/**
 *
 * @author kevin
 */
public class Ship extends GameObject implements GOInterface { //interface shoot?    extends DynamicGameObject ?
    private int hp;
    private int attack;
    private boolean dead;
    private int exp;
    private int level;
    private int score;
    private int multiplier;
    private BitmapFont font;
    private String type;
    private Sprite sprite;
    private Sprite shield;

    private boolean canShoot = true;
    private float timer = 0;

    private Vector2 shootDir = new Vector2(0,0);
    private Vector2 moveDir = new Vector2(0,0);
    private Vector2 lookDir = new Vector2(0,0);


    public int getScore()
    {
        return score;
    }

    public Ship(Vector2 pos, String type)
    {
        super(pos);


        font = new BitmapFont();
        score=0;
        this.type = type;
        dead = false;

        texture = Managers.getAssetManager().getTexture("Speler_2");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        Texture texture2 = Managers.getAssetManager().getTexture("shield");
        shield = new Sprite(texture2, texture.getWidth(), texture.getHeight());
    }

    public String getType() {
        return type;
    }
    public Sprite getSprite()
    {
        return sprite;
    }



    public void shoot()
    {
        if(canShoot) {
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(shootDir)));
            canShoot = false;
        }
    }

    public void handlePickedUp(Geom geom)
    {
        exp +=geom.getLoot().getExperience();
        level = (exp/100)+1;
        multiplier += geom.getLoot().getMultiplier();
        score +=(geom.getLoot().getScorePoints())*multiplier;
    }

    public void handlePickedUp(PowerUp pow)
    {
     Managers.getEnemyManager().clearAll();
    }

    public void nuke()
    {
        Managers.getBulletManager().clearAll();
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


        shield.setColor(new Color(0.1f, 0.8f,0,0.5f));
        shield.setSize(80  , 80);
        shield.setOrigin(40, 40);
        shield.setPosition(position.x - 40, position.y - 40);
        shield.draw(batch);


        font.draw(batch, "speler: score "+score+" multiplier= "+multiplier+"    level= "+level, 10, 20);
    }

    @Override
    public void update()
    {
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 0.15f) {
            timer %= 0.15f;
            canShoot = true;
        }

        if(shootDir.len() > 0.01f)
        {
            shoot();
            lookDir = shootDir;
        }
        else if (moveDir.len() > 0.01f)
        {
            lookDir = moveDir;
        }

        Move();


        if(position.x < 0)
            position.x = Gdx.graphics.getWidth();
        if(position.x > Gdx.graphics.getWidth())
             position.x = 0;
        if(position.y < 0)
            position.y = Gdx.graphics.getHeight();
        if(position.y > Gdx.graphics.getHeight())
            position.y = 0;

    }

    private void Move()
    {
        position.mulAdd(moveDir, 450 * Gdx.graphics.getDeltaTime());
    }

    public void setMoveDirection(Vector2 dir)
    {
        moveDir = dir;
    }
    public void setShootDirection(Vector2 dir) {
        shootDir = dir;
    }
    public void setDead()
    {
        dead = true;
    }

    public void reset()
    {
        position = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        score = 0;
        multiplier = 0;
        level = 0;
    }

}

