package com.group17.geowars.managers;

/**
 * Created by nikola on 21/12/2016.
 */

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Account;

import java.util.ArrayList;
import java.util.List;package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 10/11/2016.
 */
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

    public Player createPlayer()
    {
        return new Player("");
    }

    public void update()
    {
    }
    public void render(Batch batch)
    {
    }
    public void reset()
    {
    }
}

