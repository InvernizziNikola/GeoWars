package com.group17.geowars.playerobjects;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kevin on 9/11/2016.
 */
public class PlayerInput {
    private Controller controller;
    private Profile profile;

private boolean fired = false;
    public PlayerInput(Controller controller, Profile profile)
    {
        this.controller = controller;
        this.profile = profile;
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
            profile.getShip().nuke();

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

        profile.getShip().setMoveDirection(dir.nor());
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

        profile.getShip().setShootDirection(dir.nor());
    }
}
