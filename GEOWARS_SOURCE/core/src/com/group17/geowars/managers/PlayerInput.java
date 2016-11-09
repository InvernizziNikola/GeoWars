package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Ouya;
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
public class PlayerInput implements ControllerListener {
    private Controller controller;
    private Player player;


    public PlayerInput(Controller controller, Player player)
    {
        this.controller = controller;
        this.player = player;
        System.out.println("on!!");
        //constructor empty ?
    }

    public void handleKey()
    {

    }

    @Override
    public void connected(Controller controller) {
        System.out.println(" we have lift off!!");
    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        System.out.println("ship type:   "+player.getShip().getType());
        player.getShip().shoot();
        System.out.println(" we have a pressed button!!");
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        System.out.println(buttonCode);
        if (buttonCode == 22)
        {

        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
/*        System.out.println(value);
        if(axisCode == XBOX360KeyMapping.AXIS_LEFT_X)
            if(value<-0.5f) {
                player.getShip().moveLeft();
               System.out.println("left");
            }
            if(value>0.5) {
            player.getShip().moveRight();
               System.out.println("right");
            }
        if(axisCode == XBOX360KeyMapping.AXIS_LEFT_Y)
            if(value<0.5f) {
                player.getShip().moveDown();
                System.out.println("down");
            }
            if(value>-0.5f) {
            player.getShip().moveUp();
              System.out.println("up");
        }*/




       // if(axisCode == XBOX360KeyMapping.AXIS_RIGHT_X)
        //    player.getShip().moveLeft();



        //System.out.println(axisCode+"     "+ value);
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {

        System.out.println("00          " + value);
        System.out.println(value);

        if (value.toString().equals("west")) {
            player.getShip().moveLeft();
            System.out.println("left");
        }
        if (value.toString().equals("east")) {
            player.getShip().moveRight();
            System.out.println("right");
        }
        if (value.toString().equals("south")) {
            player.getShip().moveDown();
            System.out.println("down");
        }
        if (value.toString().equals("north")) {
            player.getShip().moveUp();
            System.out.println("up");



        }
        return false;
    }
    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
