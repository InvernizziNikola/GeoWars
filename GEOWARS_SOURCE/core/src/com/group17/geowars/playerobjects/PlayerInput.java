package com.group17.geowars.playerobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;

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
        //constructor empty ?
    }


    public void update()
    {
        if(controller != null)
            handleControllerInput();
        else
            handleKeyboardMouseInput();
    }
    public void handleKeyboardMouseInput()
    {
        handleKeyboardMovement();
        handleMouseShooting();
    }
    public void handleKeyboardMovement()
    {

        Vector2 dir = new Vector2(0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dir.x += -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dir.x += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dir.y += 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dir.y += -1;
        }

        player.getShip().setMoveDirection(dir.nor());
    }
    public void handleMouseShooting()
    {
        Vector2 dir = new Vector2(0,0);

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            Vector2 shipPos = player.getShip().getPosition();
            Vector2 mousePos = new Vector2(Gdx.input.getX(), -Gdx.input.getY() + 600);


            dir = new Vector2(mousePos.x - shipPos.x, mousePos.y - shipPos.y);
        }

        player.getShip().setShootDirection(dir.nor());
    }
    public void handleControllerInput()
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
