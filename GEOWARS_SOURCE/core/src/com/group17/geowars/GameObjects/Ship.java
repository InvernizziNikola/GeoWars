/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.AssetManager;
import com.group17.geowars.managers.BulletManager;

/**
 *
 * @author kevin
 */
public class Ship extends GameObject implements GOInterface { //interface shoot?    extends DynamicGameObject ?
    private int hp;
    private int attack;
    private boolean dead;
    private int level;

    private String type;
    private Sprite sprite;

    private Vector2 direction;


    public Ship(Vector2 pos, String type)
    {
        super(pos);

        texture = AssetManager.getInstance().getTexture("Speler_2");

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
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        sprite.setRotation(direction.angle());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void shoot()
    {
        BulletManager.GetInstance().addBullet(new Bullet(new Vector2(position), new Vector2(direction)));
    }

    @Override
    public void update()
    {

        Vector2 mousePos = new Vector2(Gdx.input.getX(), -Gdx.input.getY() + 600);
        direction = mousePos.sub(position.x + 25, position.y + 25).nor();


        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            //shoot();
            //BulletManager.GetInstance().addBullet(new Bullet(new Vector2(position), new Vector2(direction)));
        }


        // TODO HACK TILL WE GET CONTROLLERS
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            position = new Vector2(position.x + (-200 * Gdx.graphics.getDeltaTime()), position.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            position = new Vector2(position.x + (200 * Gdx.graphics.getDeltaTime()), position.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            position = new Vector2(position.x, position.y + (-200 * Gdx.graphics.getDeltaTime()));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            position = new Vector2(position.x, position.y + (200 * Gdx.graphics.getDeltaTime()));
        }
    }
}

