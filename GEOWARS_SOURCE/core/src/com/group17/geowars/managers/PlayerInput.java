package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.playerobjects.Player;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by kevin on 9/11/2016.
 */
public class PlayerInput {
    private Controller controller;
    private Player player;


    public PlayerInput(Controller controller, Player player)
    {
        this.controller = controller;
        this.player = player;
        //constructor empty ?
    }

    public void handleKey()
    {

    }

}
