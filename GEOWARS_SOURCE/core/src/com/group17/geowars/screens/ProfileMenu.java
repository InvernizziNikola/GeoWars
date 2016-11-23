package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by michield on 10/11/2016.
 */
public class ProfileMenu extends MenuScreen {

    public ProfileMenu()
    {
        super();
        create();
    }

    public void create(){
        Gdx.input.setInputProcessor(stage);

        TextButton clanButton = newButton("CLANS",50,100,150,50);
        /*clanButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });*/

        TextButton backButton = newButton("BACK",640,50,150,50);
        /*backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                Screen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });*/
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
