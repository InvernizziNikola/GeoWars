/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.group17.geowars.TestMains;

import com.group17.geowars.GameObjects.Drone;
import com.group17.geowars.PlayerObjects.Player;
import com.group17.geowars.PlayerObjects.Profile;

/**
 *
 * @author kevin
 */
public class Geo_Wars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Profile proff = new Profile("1");
        Player player1 = proff.getPlayer();
        
        Drone dr = proff.getDrones().get(0);
        player1.setDrone(dr);
        
        System.out.println(player1.getStats());
    }
    
}
