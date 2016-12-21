/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.playerobjects;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.playerObjects.Drone;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.playerObjects.Ship;

/**
 *
 * @author kevin
 */

public class Player implements GOInterface {
    private String name;
    private Ship ship;
    private Drone drone;
    private Controller controller;// nodig ?
    private PlayerInput playerInput;

    public Player(String naam)
    {
        name = naam;
    }
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public PlayerInput getPlayerInput()
    {
        return playerInput;
    }

    public String getStats(){
        return "name= "+ name+
                "\n   drone= "+drone.getType()+
                "\n   ship=  "+ship.getType();
    }

    public Ship getShip() {
        return ship;
    }

    public String getName() {
        return name;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        playerInput = new PlayerInput(controller, this);
        //controller.addListener(playerInput);
        //Gdx.app.log("controller: ", controller.getName());
    }

    public Controller getController() {
        return controller;
    }

    @Override
    public void render(Batch batch)
    {
        drone.render(batch);
        ship.render(batch);
    }

    @Override
    public void update()
    {
        playerInput.update();
        drone.update();
        ship.update();
    }
    public void reset()
    {
        ship.reset();
        drone.reset();
    }
}
