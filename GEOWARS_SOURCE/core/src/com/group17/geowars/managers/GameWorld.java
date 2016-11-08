package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.playerobjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameWorld
{
    public List<Geom> geoms;
    public List<Enemy> enemies;
    public Player player;


    private Batch batch;

    public GameWorld(Batch batch)
    {
        this.batch = batch;

        geoms = new ArrayList<Geom>();
        enemies = new ArrayList<Enemy>();
        player = null; // TODO WORD OPGEVRAAGD


    }

    public void update()
    {
        for(int i = 0; i < geoms.size(); i++)
           geoms.get(i).update();
    }

    public void render()
    {
        for(int i = 0; i < geoms.size(); i++)
           geoms.get(i).render(batch);
    }

}
