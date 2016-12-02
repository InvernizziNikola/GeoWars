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
public class PlayMenu extends MenuScreen {

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
                //moet nog een action komen
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

        TextButton multiPlayerButton = newButton("CO-OP",525,400,150,50, new MenuGrid(2, 0));
        /*multiPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });*/

        TextButton backButton = newButton("BACK",325,250,150,50, new MenuGrid(0, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

    }

    @Override
    public void show()
    {

    }
    @SuppressWarnings("Duplicates")
    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
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
