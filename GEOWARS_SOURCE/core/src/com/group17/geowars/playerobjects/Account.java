/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.playerobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.playerObjects.DefenceDrone;
import com.group17.geowars.gameobjects.playerObjects.DestroyerShip;
import com.group17.geowars.gameobjects.playerObjects.Drone;
import com.group17.geowars.gameobjects.playerObjects.Ship;

import java.util.*;

/**
 *
 * @author kevin
 */
public class Account {

    public String name;

    public Profile profile;

    public boolean main = false;
    private Player player;
    public Player getPlayer()
    {
        return player;
    }
    public void setPlayer(Player p) {player = p; }
    private Controller controller;

    public void setController(Controller c)
    {
        controller = c;
    }
    public Controller getController()
    {
        return controller;
    }

    public Account(String username)
    {
        name = username;
        profile =new Profile(new DestroyerShip(new Vector2(0,0)),new DefenceDrone(new Vector2(0,0)));


        //getAccountThread = getAccountProfiles();
    }

    public void update()
    {


    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Profile> getAccountProfiles() {

        // TODO GET PROFIELS FROM DB
        return new ArrayList<Profile>();
    }
}
