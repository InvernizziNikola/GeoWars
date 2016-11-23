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
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;

/**
 *
 * @author kevin
 */
public class Drone extends GameObject{
    private int hp;
    private int attack;
    private int level;
    private String type;
    private Sprite sprite;
    private Texture texture;
    private Player player;

    public Drone(Vector2 pos, String type, Player player)
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

    public void update() {

        Vector2 shipPos = player.getShip().getPosition();
        Vector2 dist = new Vector2(shipPos.x - getPosition().x, shipPos.y - getPosition().y);

        if(dist.len() > 100)
        {
            Vector2 pos = new Vector2(position.x, position.y);
            setPosition(pos.lerp(shipPos, Gdx.graphics.getDeltaTime() * 50));
        }
    }
}
