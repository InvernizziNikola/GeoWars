package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

public class DifficultyScreen extends MenuScreen implements iHasStage, iSetActive {

    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;

    public DifficultyScreen() {
        super();
        create();
    }


    public void create() {
        Gdx.input.setInputProcessor(stage);

        final ImageButton easyButton = newImageButton("Easy", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0, 0));
        easyButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                easyButton.setChecked(false);
                //set here
                Managers.getGameManager().setDifficulty("Easy");
                if(Managers.getGameManager().game != null)
                    return;

                Managers.getGameManager().newGame();
            }
        });

        final ImageButton mediumButton = newImageButton("Normal",(width/2)-75,height/2+50,150,50, new MenuGrid(1, 0));
        mediumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mediumButton.setChecked(false);
                //sethere
                Managers.getGameManager().setDifficulty("Normal");
                if(Managers.getGameManager().game != null)
                    return;

                Managers.getGameManager().newGame();

            }
        });

        final ImageButton hardButton = newImageButton("Hard",(width/2)+(width/6),height/2+50,150,50, new MenuGrid(2, 0));

        hardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hardButton.setChecked(false);
                //sethere
                Managers.getGameManager().setDifficulty("Hard");
                if(Managers.getGameManager().game != null)
                    return;

                Managers.getGameManager().newGame();

            }
        });

        final ImageButton backButton = newImageButton("BACK",(width/2)-75,(height/2)-(height/2)+(height/2)/4,150,50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);

                if(Managers.getGameManager().game != null)
                    return;

                Managers.getGameManager().newGame();
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
