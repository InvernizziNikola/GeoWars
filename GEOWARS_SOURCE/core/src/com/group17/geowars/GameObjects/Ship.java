/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author kevin
 */
public class Ship { //interface shoot?    extends DynamicGameObject ?
    private int hp;
    private int attack;
    private boolean dead;
    private int level;
    private String position; //private Vector2 position; 
    private String type;
    private Sprite sprite;

    public Ship(String type)
    {
        this.type =type;
        dead = false;
        Texture img = new Texture("Speler.png");
        sprite = new Sprite(img,img.getWidth(),img.getHeight());
    }

    public String getType() {
        return type;
    }
    public Sprite getSprite()
    {
        return sprite;
    }
}

