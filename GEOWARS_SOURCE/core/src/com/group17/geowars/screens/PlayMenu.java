package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;

/**
 * Created by michield on 10/11/2016.
 */
public class PlayMenu implements Screen, hasStage {

    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;

    public PlayMenu(){
        create();
    }


    public void create() {

        batch = new SpriteBatch();
        stage = new Stage();

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();

        skin.add("default", bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        final TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.fontColor = Color.BLACK;

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        final TextButton campaignButton = new TextButton("CAMPAIGN",textButtonStyle);
        campaignButton.setPosition(125,400);
        campaignButton.setHeight(50);
        campaignButton.setWidth(150);
        stage.addActor(campaignButton);

        final TextButton arcadeButton = new TextButton("ARCADE",textButtonStyle);
        arcadeButton.setPosition(325,400);
        arcadeButton.setHeight(50);
        arcadeButton.setWidth(150);
        stage.addActor(arcadeButton);
        arcadeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                Screen nextMenu = Managers.getMenuManager().getScreen("game");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton multiPlayerButton = new TextButton("CO-OP",textButtonStyle);
        multiPlayerButton.setPosition(525,400);
        multiPlayerButton.setHeight(50);
        multiPlayerButton.setWidth(150);
        stage.addActor(multiPlayerButton);

        final TextButton backButton = new TextButton("BACK", textButtonStyle);
        backButton.setPosition(325, 250);
        backButton.setHeight(50);
        backButton.setWidth(150);
        stage.addActor(backButton);
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                Screen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

    }

    @Override
    public void show()
    {

    }

    public void render (float delta) {
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
