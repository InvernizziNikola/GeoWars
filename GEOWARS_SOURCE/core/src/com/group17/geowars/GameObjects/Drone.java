/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

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
  

    public Drone(Vector2 pos, String type)
    {
        super(pos);

        this.type =type;
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

    public void update()
    {

    }
    
    
    
    
    
    
    
}
