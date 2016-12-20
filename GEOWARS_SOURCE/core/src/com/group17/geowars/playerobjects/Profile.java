package com.group17.geowars.playerobjects;

import com.group17.geowars.gameobjects.playerObjects.Ship;

/**
 * Created by nikola on 20/12/2016.
 */

public class Profile {
    private ShipProfile shipProfile;
    private DroneProfile droneProfile;

    public Profile(ShipProfile shipProfile, DroneProfile droneProfile)
    {
        this.shipProfile = shipProfile;
        this.droneProfile = droneProfile;
    }
}
