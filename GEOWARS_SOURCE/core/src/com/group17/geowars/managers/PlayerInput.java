package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.database.XBOX360KeyMapping;
import com.group17.geowars.playerobjects.Player;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by kevin on 9/11/2016.
 */
public class PlayerInput {
    private Controller controller;
    private Player player;

private boolean fired = false;
    public PlayerInput(Controller controller, Player player)
    {
        this.controller = controller;
        this.player = player;
        System.out.println("on!!");
        //constructor empty ?



    }


    public void update()
    {
        handleMovementJoystick();
        handleShootJoystick();
        handleButtonInput();
    }

    public void handleButtonInput()
    {
        if(controller.getButton(0) && !fired)
        {
            fired = true;
            player.getShip().nuke();

        }
        else if(!controller.getButton(0) && fired)
        {
            fired = false;
        }
    }

    public void handleMovementJoystick()
    {
        Vector2 dir = new Vector2(0,0);

        if(controller.getAxis(2) > 0.1f || controller.getAxis(2) < -0.1f)
        {
            dir.y = -controller.getAxis(2);
        }
        if(controller.getAxis(3) > 0.1f || controller.getAxis(3) < -0.1f)
        {
            dir.x = controller.getAxis(3);
        }

        if(dir.len() < 0.2f)
            dir = new Vector2(0,0);

        player.getShip().setMoveDirection(dir.nor());
    }
    public void handleShootJoystick()
    {
        Vector2 dir = new Vector2(0,0);

        if(controller.getAxis(0) > 0.1f || controller.getAxis(0) < -0.1f)
        {
            dir.y = -controller.getAxis(0);
        }
        if(controller.getAxis(1) > 0.1f || controller.getAxis(1) < -0.1f)
        {
            dir.x = controller.getAxis(1);
        }

        if(dir.len() < 0.2f)
            dir = new Vector2(0,0);

        player.getShip().setShootDirection(dir.nor());
    }
}
