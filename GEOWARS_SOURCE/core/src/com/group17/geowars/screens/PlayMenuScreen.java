package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gamemodes.ArcadeSoloGame;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.GAMESTATE;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 10/11/2016.
 */
public class PlayMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    public PlayMenuScreen()
    {
        super();
        create();
    }


    public void create() {
        Gdx.input.setInputProcessor(stage);

        final ImageButton arcadeButton = newImageButton("Menu_arcadeicon",(width/2)-75,height/2+50,150,50, new MenuGrid(0, 0));
        arcadeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                arcadeButton.setChecked(false);

                MenuScreen nextMenu = Managers.getScreenManager().getScreen("difficulty");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });

        final ImageButton multiPlayerButton = newImageButton("Menu_coopicon",(width/2)-75,height/3,150,50, new MenuGrid(0, 1));

        multiPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                multiPlayerButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("coopmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton eventButton = newImageButton("Menu_eventicon",(width/2)+150,height/2+50,150,50, new MenuGrid(1, 0));
        eventButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                arcadeButton.setChecked(false);

                startEventBossGame();

            }
        });

        final ImageButton backButton = newImageButton("Menu_backicon",(width/2)-75,(height/2)-(height/2)+(height/2)/4,150,50, new MenuGrid(0, 2));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }


    private void startEventBossGame()
    {
        if (Managers.getGameManager().getGame() != null)
            return;

        Managers.getGameManager().newBossSoloGame();

        MenuScreen nextMenu = Managers.getScreenManager().getScreen("game");
        Managers.getScreenManager().setScreen(nextMenu);
    }

    public void render (float deltaTime) {
        super.render(deltaTime);
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
