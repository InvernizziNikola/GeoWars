package com.group17.geowars.playerobjects;

import com.group17.geowars.gameobjects.playerObjects.Drone;
import com.group17.geowars.gameobjects.playerObjects.Ship;

/**
 * Created by nikola on 20/12/2016.
 */

public class Profile {
    private Ship ship;
    private Drone drone;

    public Profile(Ship shipProfile, Drone droneProfile)
    {
        this.ship = shipProfile;
        this.drone = droneProfile;
    }
}
