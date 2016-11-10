package com.group17.geowars.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.GameWorld;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.playerobjects.Profile;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameScreen extends ScreenAdapter
{

    private GameWorld world;

    public GameScreen(Batch batch)
    {

        world = new GameWorld(batch);

        Profile profile = new Profile("YEEEY");
        profile.setPlayer(profile.getDrones().get(0), profile.getShips().get(0));

        Player player = profile.getPlayer();
        player.setController(Controllers.getControllers().first());

        world.players.add(player);


        //world.geoms.add(new Geom(1, new Vector2(300,50)));
        //world.geoms.add(new Geom(1, new Vector2(100,300)));
        //world.geoms.add(new Geom(1, new Vector2(300,300)));
    }

    public void render()
    {
        world.update();
        world.render();
    }
}
