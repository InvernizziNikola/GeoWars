package com.group17.geowars.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.GameWorld;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.playerobjects.Profile;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameScreen extends ScreenAdapter implements hasStage {

    private GameWorld world;
    private Batch batch;
    public GameScreen()
    {

        batch = new SpriteBatch();
        batch.begin();
        world = new GameWorld(batch);
        batch.end();

        Profile profile = new Profile("YEEEY");
        profile.setPlayer(profile.getDrones().get(0), profile.getShips().get(0));

        Managers.getProfileManager().addProfile(profile);

        Player player = profile.getPlayer();
        player.setController(Controllers.getControllers().first());

    }

    public void render()
    {
        world.update();
        world.render();
    }

    @Override
    public Stage getStage() {
        return null;
    }
}
