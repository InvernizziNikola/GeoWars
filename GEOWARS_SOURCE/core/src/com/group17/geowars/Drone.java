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
public class Drone {
    private int hp;
    private int attack;
    private int level;
    private String type;
  //  Texture img;
    private String position; //private Vector2 position;
  

    public Drone(String type)
    {
        this.type =type;
         //img = new Texture("badlogic.jpg");
    }

    public String getType() {
        return type;
    }
    
    
    
    
    
    
    
    
}
