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

    private int axis0 = 0;
    private int axis1 = 1;
    private int axis2 = 2;
    private int axis3 = 3;
    private int Keybinding=1;

private boolean fired = false;
    public PlayerInput(Controller controller, Player player)
    {
        if(controller != null) {
            if (controller.getName().toLowerCase().contains("xbox") &&
                    controller.getName().contains("360")) {
                axis0 = 2;
                axis1 = 3;
                axis2 = 0;
                axis3 = 1;
            }
        }

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
    public void setArrowkeys(){
        //kijken hoe we deze moeten activeren
        Keybinding = 1;

    }
    public void setAzerty(){
        Keybinding = 2;
    }
    public void setQwerty(){
        Keybinding = 3;
    }
    public void handleKeyboardMovement()
    {

        Vector2 dir = new Vector2(0,0);
        if(Keybinding==1) {
           // System.out.println("keybinding : arrowkeys");
            //arrowkeys
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                dir.x += -1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                dir.x += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                dir.y += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                dir.y += -1;
            }
        }else if (Keybinding ==2){
            //azerty
           // System.out.println("keybinding : azerty");
            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
                dir.x += -1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                dir.x += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
                dir.y += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                dir.y += -1;
            }
        }else if (Keybinding==3){
            //Qwerty
            //System.out.println("keybinding : qwerty");
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                dir.x += -1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                dir.x += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                dir.y += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                dir.y += -1;
            }
        }else{
            System.out.println("no keybinding selected!");
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

        if(controller.getAxis(axis2) > 0.1f || controller.getAxis(axis2) < -0.1f)
        {
            dir.y = -controller.getAxis(axis2);
        }
        if(controller.getAxis(axis3) > 0.1f || controller.getAxis(axis3) < -0.1f)
        {
            dir.x = controller.getAxis(axis3);
        }

        if(dir.len() < 0.2f)
            dir = new Vector2(0,0);

        player.getShip().setMoveDirection(dir.nor());
    }
    public void handleShootJoystick()
    {
        Vector2 dir = new Vector2(0,0);

        if(controller.getAxis(axis0) > 0.1f || controller.getAxis(axis0) < -0.1f)
        {
            dir.y = -controller.getAxis(axis0);
        }
        if(controller.getAxis(axis1) > 0.1f || controller.getAxis(axis1) < -0.1f)
        {
            dir.x = controller.getAxis(axis1);
        }

        if(dir.len() < 0.2f)
            dir = new Vector2(0,0);

        player.getShip().setShootDirection(dir.nor());
    }
}
