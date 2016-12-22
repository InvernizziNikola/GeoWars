package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 10/11/2016.
 */
public class MainMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;

    public MainMenuScreen()
    {
        super();
        create();
    }
    public void create() {
        Gdx.input.setInputProcessor(stage);

        final TextButton playButton = newButton("PLAY",GeoWars.WIDTH/2-100, GeoWars.HEIGHT-GeoWars.HEIGHT/4, 200, 75, new MenuGrid(0, 0));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                playButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("playmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton optionsButton = newButton("OPTIONS", GeoWars.WIDTH/2-100, GeoWars.HEIGHT/2, 200, 75, new MenuGrid(0, 2));
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                optionsButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("optionsmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton leaderboardButton = newButton("LEADERBOARDS", GeoWars.WIDTH/2-100, GeoWars.HEIGHT/2-GeoWars.HEIGHT/8, 200, 75, new MenuGrid(0, 3));
        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                leaderboardButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("highscoremenu");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });
        System.out.println(Managers.getAccountManager().getAccounts().get(0).name.toString());
        if(!Managers.getAccountManager().getAccounts().get(0).name.toString().equals("Guest")) {
            final TextButton profileButton = newButton("PROFILE", GeoWars.WIDTH / 2 - 100, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 8, 200, 75, new MenuGrid(0, 1));
            profileButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    profileButton.setChecked(false);
                    MenuScreen nextMenu = Managers.getScreenManager().getScreen("profilemenu");
                    Managers.getScreenManager().setScreen(nextMenu);
                }
            });

            final TextButton shopButton = newButton("SHOP", GeoWars.WIDTH / 2 - 100, GeoWars.HEIGHT / 2 - GeoWars.HEIGHT / 4, 200, 75, new MenuGrid(0, 4));
            shopButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    shopButton.setChecked(false);
                    MenuScreen nextMenu = Managers.getScreenManager().getScreen("shopmenu");
                    Managers.getScreenManager().setScreen(nextMenu);
                }
            });
        }
        /*
        final TextButton LoginButton = newButton("Login", GeoWars.WIDTH/2-400, GeoWars.HEIGHT/2-GeoWars.HEIGHT/4, 200, 75, new MenuGrid(-1, 4));
        LoginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                LoginButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("LoginScreen");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
*/


        final TextButton quitButton = newButton("QUIT GAME", GeoWars.WIDTH/2-100, GeoWars.HEIGHT-GeoWars.HEIGHT+90, 200, 75, new MenuGrid(0, 5));
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                quitButton.setChecked(false);
                Gdx.app.exit();
            }
        });

    }

    public void render (float deltaTime) {
        super.render(deltaTime);
    }

    @Override
    public void setActive() {

        if(active)
            return;
        active = true;
        // do something when activating

    }

    @Override
    public void setInActive() {
        active = false;
    }
}
