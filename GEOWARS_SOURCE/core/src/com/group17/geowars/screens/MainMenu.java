package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;

/**
 * Created by michield on 10/11/2016.
 */
public class MainMenu extends MenuScreen implements Screen{

    public MainMenu()
    {
        super();
        create();
    }
    public void create(){
        Gdx.input.setInputProcessor(stage);

        TextButton playButton = newButton("PLAY", 300, 450, 150, 50);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("playmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        TextButton profileButton = newButton("PROFILE", 300, 390, 150, 50);
        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("profilemenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        TextButton optionsButton = newButton("OPTIONS", 300, 330, 150, 50);
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("options");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        TextButton leaderboardButton = newButton("LEADERBOARDS", 300, 270, 150, 50);
        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("highScore");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        TextButton shopButton = newButton("SHOP", 300, 210, 150, 50);
        shopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("shop");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });


        TextButton quitButton = newButton("QUIT GAME", 300, 90, 150, 50);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                Gdx.app.exit();
            }
        });


        if(menuButtons.size() >= 0) // todo check if controller is connected
            menuButtons.get(selectedButton).setStyle(styleSelected);
    }



    @Override
    public void show() {

    }

    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        super.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
