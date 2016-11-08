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
public class Enemy {
    private int EnemyType;
    private int hp;
    private int attack;
    private boolean dead;
    private String position; //private Vector2 position;
    private Sprite sprite;

    public Enemy(String type) {

        dead = false;
        Texture img = new Texture("tank.png");
        sprite = new Sprite(img,img.getWidth(),img.getHeight());
    }
    
    public void dropPowerUp(int EnemyType)
    {
        String dropPosition = position;
        PowerUp pow = new PowerUp(dropPosition);
        // pow naar game scherm doen
    }
    public Sprite getSprite()
    {
        return sprite;
    }

}
