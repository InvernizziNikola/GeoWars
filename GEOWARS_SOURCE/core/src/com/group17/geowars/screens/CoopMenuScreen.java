package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
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

        final TextButton startGameButton = newButton("START", GeoWars.WIDTH/2, GeoWars.HEIGHT / 5 ,250,50, new MenuGrid(0, 0));
        startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startGameButton.setChecked(false);

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
