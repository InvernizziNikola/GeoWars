/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars;

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

    public Enemy() {
        dead = false;
    }
    
    public void dropPowerUp(int EnemyType)
    {
        String dropPosition = position;
        PowerUp pow = new PowerUp(dropPosition);
        // pow naar game scherm doen
    }
    
}
