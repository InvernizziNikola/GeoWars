/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group17.geowars.PlayerObjects;

import com.group17.geowars.GameObjects.Drone;
import com.group17.geowars.GameObjects.Ship;

/**
 *
 * @author kevin
 */
public class Player {
    private String name;
    private Ship ship;
    private Drone drone;
    //private Controller x;

    public Player(String naam , Drone dr ,Ship sp)
    {
       name =naam;
        drone=dr;
        ship=sp;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
    
    public String getStats(){
        return "name= "+ name+
                "\n   drone= "+drone.getType()+
                "\n   ship=  "+ship.getType();
    }

    public Ship getShip() {
        return ship;
    }
}
