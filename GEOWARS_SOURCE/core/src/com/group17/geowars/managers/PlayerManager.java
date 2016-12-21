package com.group17.geowars.managers;


import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {

    private List<Player> players;
    public List<Player> getPlayers()
    {
        return players;
    }

    public PlayerManager()
    {
        players = new ArrayList<Player>();
    }

    public Player createPlayer(String name, Controller c)
    {
        Player p = new Player(name, c);
        players.add(p);
        return p;
    }

    public void update()
    {
        for(Player p: players)
        {
            p.update();
        }
    }
    public void render(Batch batch)
    {
        for(Player p : players)
        {
            p.render(batch);
        }
    }
    public void reset()
    {
        players.clear();
    }
}

