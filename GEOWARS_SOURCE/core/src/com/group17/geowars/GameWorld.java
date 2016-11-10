package com.group17.geowars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.Ship;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */

public class GameWorld
{
    public List<Geom> geoms;
    public List<Ship> ships;
    public List<Player> players;

    private Batch batch;

    public GameWorld(Batch batch)
    {
        this.batch = batch;

        geoms = new ArrayList<Geom>();
        ships = new ArrayList<Ship>();
        players = new ArrayList<Player>();
    }

    public void update()
    {
        Managers.update();

        for(int i = 0; i < players.size(); i++)
            players.get(i).update();
    }

    public void render()
    {
        Managers.render(batch);

        for(int i = 0; i < players.size(); i++)
            players.get(i).render(batch);

    }

}
