/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_prutsen;

import java.util.*;

/**
 *
 * @author kevin
 */
public class Profile {
    private Player player;
    private List<Ship> ships;
    private List<Drone> drones;
    private String playerId;
    private int level;

    public Profile(String playerId)
    {
        this.playerId = playerId;
        // get data from player id #databank
        player = getPlayer();
        ships = setShips();  
        drones = SetDrones();
        level = setLevel();

    }

    public Player getPlayer() {//#TODO: VIA DATABANK
       
        //Player p = player met id uit databank 
         Player p = new Player("nikoala");
       return p;
    }

    public List<Ship> setShips( ) {//#TODO: VIA DATABANK
        List<Ship> s = new ArrayList();
        s.add(new Ship("tank"));
        s.add(new Ship("fighter"));
        return s;
    }

    public List<Drone> SetDrones() {//#TODO: VIA DATABANK
        List<Drone> d = new ArrayList();
        d.add(new Drone("support"));
        d.add(new Drone("defence"));
        return d;
    }

    public int setLevel() {//#TODO: VIA DATABANK
           return 1;
    }
    
    public List<Ship> getShips() {
        return ships;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public String getPlayerId() {
        return playerId;
    }

    public int getLevel() {
        return level;
    }

    
}
