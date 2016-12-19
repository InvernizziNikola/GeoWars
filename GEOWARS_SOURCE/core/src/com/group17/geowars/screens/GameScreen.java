package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameScreen extends MenuScreen implements iGameScreen {

    //private GameWorld world;
    private Batch batch;
    public GameScreen()
    {
        batch = new SpriteBatch();

        Account account = new Account("YEEEY");
        account.setPlayer(account.getDrones().get(0), account.getShips().get(0));

        Managers.getAccountManager().addAccount(account);

        Player player = account.getPlayer();
        Controller controller = null;
        if(Controllers.getControllers().size > 0)
            controller = Controllers.getControllers().first();

        player.setController(controller);
    }

    public void renderGame()
    {
        super.render(Gdx.graphics.getDeltaTime());

        batch.begin();
        Managers.getGameManager().update();
        Managers.getGameManager().render(batch);
        batch.end();
    }

}
