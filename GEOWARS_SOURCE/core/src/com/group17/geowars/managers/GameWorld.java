package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.Ship;
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
    public List<Ship> ships;
    public List<Player> players;
    public Player player;


    private Batch batch;

    public GameWorld(Batch batch)
    {
        this.batch = batch;

        geoms = new ArrayList<Geom>();
        enemies = new ArrayList<Enemy>();
        ships = new ArrayList<Ship>();
        players = new ArrayList<Player>();
    }

    public void update()
    {
        for(int i = 0; i < geoms.size(); i++)
            geoms.get(i).update();

        for(int i = 0; i < players.size(); i++)
            players.get(i).update();

        BulletManager.GetInstance().update();
    }

    public void render()
    {
        BulletManager.GetInstance().render(batch);

        for(int i = 0; i < geoms.size(); i++)
            geoms.get(i).render(batch);

        for(int i = 0; i < players.size(); i++)
            players.get(i).render(batch);
    }

}
