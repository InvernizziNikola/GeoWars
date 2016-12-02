package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group17.geowars.GameWorld;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameScreen extends MenuScreen {

    private GameWorld world;
    private Batch batch;
    public GameScreen()
    {

        batch = new SpriteBatch();
        batch.begin();
        world = new GameWorld(batch);
        batch.end();

        Account account = new Account("YEEEY");
        account.setPlayer(account.getDrones().get(0), account.getShips().get(0));

        Managers.getAccountManager().addAccount(account);

        Player player = account.getPlayer();
        Controller controller = null;
        if(Controllers.getControllers().size > 0)
            controller = Controllers.getControllers().first();

        player.setController(controller);

    }

    public void render()
    {
        world.update();
        world.render();
    }

}
