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
public class PowerUp {
    private String position; //private Vector2 position;
    private Sprite sprite;


    public PowerUp(String position) {
        this.position = position;
        Texture img = new Texture("Hitcircle.png");
        sprite = new Sprite(img,img.getWidth(),img.getHeight());

    }
    public Sprite getSprite()
    {
        return sprite;
    }



}
