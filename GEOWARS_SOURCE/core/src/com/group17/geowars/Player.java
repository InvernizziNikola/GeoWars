/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_prutsen;

/**
 *
 * @author kevin
 */
public class Player {
    private String name;
    private Ship ship;
    private Drone drone;
    //private Controller x;

    public Player(String naam){
       name =naam;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
    
    public String getStats(){
        return "name= "+ name+"  "+"drone= "+drone.getType();
    }
    
    
    
}
