package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.Map;

/**
 * Created by michield on 10/11/2016.
 */
public class MainMenu extends MenuScreen implements hasStage{

    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    public MainMenu()
    {
        super();
        create();
    }
    public void create() {
        Gdx.input.setInputProcessor(stage);

        final TextButton playButton = newButton("PLAY", width/2-75, height/2+150, 150, 50, new MenuGrid(0, 0));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                playButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("playmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton profileButton = newButton("PROFILE", width/2-75, height/2+50, 150, 50, new MenuGrid(0, 1));
        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                profileButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("profilemenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton optionsButton = newButton("OPTIONS", width/2-75, height/2-50, 150, 50, new MenuGrid(0, 2));
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                optionsButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("optionsmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton leaderboardButton = newButton("LEADERBOARDS", width/2-75, height/2-150, 150, 50, new MenuGrid(0, 3));
        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                leaderboardButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("highscoremenu");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });

        final TextButton shopButton = newButton("SHOP", width/2-75, height/2-250, 150, 50, new MenuGrid(0, 4));
        shopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shopButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("shopmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });


        final TextButton quitButton = newButton("QUIT GAME", width/2-75, height-height+90, 150, 50, new MenuGrid(0, 5));
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
}
