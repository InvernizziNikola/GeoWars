package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;

/**
 * Created by michield on 10/11/2016.
 */
public class MainMenu implements Screen, hasStage{
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont title;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextButton.TextButtonStyle selectedButtonStyle;
    private int buttonSelected = 0;
    private TextButton quitButton;
    private TextButton playButton;
    private TextButton leaderboardButton;
    private TextButton profileButton;
    private TextButton shopButton;
    private TextButton optionButton;
    private boolean pressed = false;


    public MainMenu(){
        create();
    }

    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        title = new BitmapFont();
        Gdx.input.setInputProcessor(stage);


        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.down = skin.newDrawable("white", Color.BLACK);
        textButtonStyle.fontColor = Color.BLACK;

        textButtonStyle.font = skin.getFont("default");

        selectedButtonStyle = new TextButton.TextButtonStyle();
        selectedButtonStyle.up = skin.newDrawable("white", Color.GRAY);
        selectedButtonStyle.fontColor = Color.BLACK;

        selectedButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        quitButton=new TextButton("QUIT GAME",textButtonStyle);
        quitButton.setPosition(300, 90);
        quitButton.setWidth(150);
        quitButton.setHeight(50);
        stage.addActor(quitButton);

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("CLICKED: " + actor);
                Gdx.app.exit();
            }
        });

        playButton = new TextButton("PLAY", textButtonStyle);
        playButton.setPosition(300,450);
        playButton.setWidth(150);
        playButton.setHeight(50);
        stage.addActor(playButton);


        playButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {


                playButton.setChecked(false);

                System.out.println("CLICKED: " + actor);
                Screen nextMenu = Managers.getMenuManager().getScreen("playmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        profileButton = new TextButton("PROFILE", textButtonStyle);
        profileButton.setPosition(300, 390);
        profileButton.setWidth(150);
        profileButton.setHeight(50);
        stage.addActor(profileButton);

        profileButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("CLICKED: " + actor);
                Screen newtMenu = Managers.getMenuManager().getScreen("profilemenu");
                Managers.getMenuManager().setScreen(newtMenu);
            }
        });

        optionButton = new TextButton("OPTIONS", textButtonStyle);
        optionButton.setPosition(300, 330);
        optionButton.setWidth(150);
        optionButton.setHeight(50);
        stage.addActor(optionButton);

        leaderboardButton = new TextButton("LEADERBORDS", textButtonStyle);
        leaderboardButton.setPosition(300, 270);
        leaderboardButton.setWidth(150);
        leaderboardButton.setHeight(50);
        stage.addActor(leaderboardButton);

        leaderboardButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor)
            {
                System.out.println("CLICKED: " + actor);
                Screen newtMenu = Managers.getMenuManager().getScreen("highScore");
                Managers.getMenuManager().setScreen(newtMenu);
            }
        });

        shopButton = new TextButton("SHOP", textButtonStyle);
        shopButton.setPosition(300, 210);
        shopButton.setWidth(150);
        shopButton.setHeight(50);
        stage.addActor(shopButton);

    }

    @Override
    public void show() {

    }

    public void render (float deltaTime) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

/*
        Controller c = Controllers.getControllers().first();
        if(c.getButton(3) && !pressed)
        {

            buttonSelected++;

            if(buttonSelected > 5)
                buttonSelected = 0;
            pressed = true;
        }
        if(c.getButton(0) && !pressed)
        {
            buttonSelected--;
            if(buttonSelected < 0)
                buttonSelected = 5;
            pressed = true;
        }
        if(!c.getButton(0) && !c.getButton(3) && pressed)
        {
            pressed = false;
        }

        if(c.getButton(1) || c.getButton(2))
        {
            playButton.setChecked(true);
        }
        */

        /*

        leaderboardButton.setStyle(textButtonStyle);
        optionButton.setStyle(textButtonStyle);
        shopButton.setStyle(textButtonStyle);
        profileButton.setStyle(textButtonStyle);
        quitButton.setStyle(textButtonStyle);
        playButton.setStyle(textButtonStyle);
        switch (buttonSelected)
        {
            case 0:{
                playButton.setStyle(selectedButtonStyle);
                break;
            }
            case 1:{
                profileButton.setStyle(selectedButtonStyle);
                break;
            }
            case 2:{
                optionButton.setStyle(selectedButtonStyle);
                break;
            }
            case 3:{
                leaderboardButton.setStyle(selectedButtonStyle);
                break;
            }
            case 4:{
                shopButton.setStyle(selectedButtonStyle);
                break;
            }
            case 5:{
                quitButton.setStyle(selectedButtonStyle);
                break;
            }
        }
        */
        //Table.drawDebug(stage);
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
