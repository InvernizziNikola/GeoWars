/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.gameobjects;
import com.badlogic.gdx.graphics.Texture;

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
    private Texture img;

    public Ship(String type)
    {
        this.type =type;
        dead = false;
         img = new Texture("Speler.png");
    }

    public String getType() {
        return type;
    }
    public Texture getTexture()
    {
        return img;
    }
}
