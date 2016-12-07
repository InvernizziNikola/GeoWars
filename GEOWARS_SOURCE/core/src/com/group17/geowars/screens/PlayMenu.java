package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.GAMESTATE;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 10/11/2016.
 */
public class PlayMenu extends MenuScreen implements hasStage, setActive{

    public PlayMenu()
    {
        super();
        create();
    }


    public void create() {
        Gdx.input.setInputProcessor(stage);

        final TextButton campaignButton = newButton("CAMPAIGN", 125,400,150,50, new MenuGrid(0, 0));
        campaignButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                campaignButton.setChecked(false);
            }
        });
        final TextButton arcadeButton = newButton("ARCADE",325,400,150,50, new MenuGrid(1, 0));
        arcadeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                arcadeButton.setChecked(false);
                Managers.getGameManager().gameState = GAMESTATE.GAMEPLAYING;
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("game");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton multiPlayerButton = newButton("CO-OP",525,400,150,50, new MenuGrid(2, 0));
        multiPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                multiPlayerButton.setChecked(false);

            }
        });

        final TextButton backButton = newButton("BACK",325,250,150,50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });
    }


    public void render (float deltaTime) {
        super.render(deltaTime);
    }

    @Override
    public void setActive() {
        System.out.println("test2");
    }
}
