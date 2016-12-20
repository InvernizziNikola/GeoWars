package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.utils.GAMESTATE;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 10/11/2016.
 */
public class CoopMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    public CoopMenuScreen()
    {
        super();
        create();
    }


    public void create() {
        Gdx.input.setInputProcessor(stage);

        final TextButton playerButton = newButton("PLAYER 1", GeoWars.WIDTH/3-125, GeoWars.HEIGHT / 5 * 4, 250, 50, new MenuGrid(0, 0));
        playerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playerButton.setChecked(false);
            }
        });

        final TextButton addNewButton = newButton("Add Player", GeoWars.WIDTH/3*2-125, GeoWars.HEIGHT / 5 * 4, 250, 50, new MenuGrid(0, 0));
        addNewButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                addNewButton.setChecked(false);
            }
        });


        final TextButton startGameButton = newButton("START", GeoWars.WIDTH/2-125, GeoWars.HEIGHT / 5 * 2 ,250,50, new MenuGrid(0, 0));
        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startGameButton.setChecked(false);


                Managers.getGameManager(); // -> START GAME, ADD PLAYERS WITH SELECTED PROFILES
            }
        });


        final TextButton backButton = newButton("Back", GeoWars.WIDTH/2 - 125, GeoWars.HEIGHT / 5, 250 ,50, new MenuGrid(0, 1));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }


    public void render (float deltaTime) {
        super.render(deltaTime);


        batch.begin();
        for(Account a : Managers.getAccountManager().getAccounts()) {
            text.draw(batch, a.name, GeoWars.WIDTH / 3 - 100, GeoWars.HEIGHT / 5 * 4);
        }
        batch.end();

    }

    @Override
    public void setActive() {
        if(active)
            return;
        active = true;
    }

    @Override
    public void setInActive() {
        active = false;
    }
}
