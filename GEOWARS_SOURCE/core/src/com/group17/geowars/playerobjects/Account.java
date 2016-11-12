/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.playerobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Drone;
import com.group17.geowars.gameobjects.Ship;

import java.util.*;

/**
 *
 * @author kevin
 */
public class Account {
    private Profile profile;

    private List<Ship> ships;
    private List<Drone> drones;

    private String playerId;
    private int level;

    public Account(String playerId)
    {
        this.playerId = playerId;
        //get data from profile id #databank
        //profile = getProfile();
        ships = setShips();
        drones = SetDrones();
        level = setLevel();
    }

    public void setPlayer(Drone dr , Ship sp)//#TODO: VIA DATABANK
    {
        //Profile profile = new Profile("playerId");
         profile = new Profile("nikoala",dr,sp);

    }
    public Profile getProfile() {
       return profile;
    }

    private List<Ship> setShips( ) {//#TODO: VIA DATABANK
        List<Ship> s = new ArrayList();
        Vector2 startPos = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        s.add(new Ship(startPos, "tank"));
        s.add(new Ship(startPos, "fighter"));

        return s;
    }

    private List<Drone> SetDrones() {//#TODO: VIA DATABANK
        List<Drone> d = new ArrayList();
        d.add(new Drone("support"));
        d.add(new Drone("defence"));
        return d;
    }

    private int setLevel() {//#TODO: VIA DATABANK
           return 1;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getLevel() {
        return level;
    }

    public void update()
    {

    }
}
