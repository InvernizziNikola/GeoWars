package com.group17.geowars.playerobjects;

import com.group17.geowars.gameobjects.playerObjects.AssaultShip;
import com.group17.geowars.gameobjects.playerObjects.Drone;
import com.group17.geowars.gameobjects.playerObjects.Ship;
import com.group17.geowars.utils.DRONETYPES;
import com.group17.geowars.utils.SHIPTYPES;

/**
 * Created by nikola on 20/12/2016.
 */

public class Profile {

    private SHIPTYPES shipType;
    private DRONETYPES droneType;


    public Profile(SHIPTYPES shipType, DRONETYPES droneType) {
        this.shipType = shipType;
        this.droneType = droneType;
    }

    public void init()
    {
        switch (shipType){
            case ASSAULT:

        }
    }

    public SHIPTYPES getShipType() {
        return shipType;
    }

    public DRONETYPES getDroneType() {
        return droneType;
    }
}
