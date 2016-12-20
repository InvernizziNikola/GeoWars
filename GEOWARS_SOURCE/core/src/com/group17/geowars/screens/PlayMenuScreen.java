package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.group17.geowars.GeoWars;
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

        final TextButton campaignButton = newButton("CAMPAIGN", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0, 0));
        campaignButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                campaignButton.setChecked(false);
            }
        });

        final TextButton arcadeButton = newButton("ARCADE",(width/2)-75,height/2+50,150,50, new MenuGrid(1, 0));
        arcadeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                arcadeButton.setChecked(false);
                Managers.getGameManager().gameState = GAMESTATE.GAMEPLAYING;
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("game");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton multiPlayerButton = newButton("CO-OP",(width/2)+(width/6),height/2+50,150,50, new MenuGrid(2, 0));

        multiPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                multiPlayerButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("coopmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton backButton = newButton("BACK",(width/2)-75,(height/2)-(height/2)+(height/2)/4,150,50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
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
    }

    @Override
    public void setInActive() {
        active = false;
    }
}
