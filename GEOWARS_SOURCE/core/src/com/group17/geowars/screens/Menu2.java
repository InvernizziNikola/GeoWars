package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Menu2 {


    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private BitmapFont title;
    private TextButton quitButton;
    private TextButton shopButton;
    private TextButton playButton;
    private TextButton optionButton;
    private TextButton leaderBordButton;
    private TextButton profileButton;
    private TextButtonStyle textButtonStyle;
    private TextButton backButton;

    public Menu2(){
        create();
    }
    public void create(){
        batch = new SpriteBatch();
        title = new BitmapFont();
        title.setColor(Color.WHITE);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        Pixmap pixmap = new Pixmap(200,50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        BitmapFont playButtonText = new BitmapFont();
        skin.add("default",playButtonText);

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = Color.BLACK;
        skin.add("default", textButtonStyle);

        playButton=new TextButton("PLAY",textButtonStyle);
        playButton.setPosition(300, 450);
        stage.addActor(playButton);

        profileButton = new TextButton("PROFILE", textButtonStyle);
        profileButton.setPosition(300, 390);
        stage.addActor(profileButton);

        optionButton = new TextButton("OPTIONS", textButtonStyle);
        optionButton.setPosition(300, 330);
        stage.addActor(optionButton);

        leaderBordButton = new TextButton("LEADERBORDS", textButtonStyle);
        leaderBordButton.setPosition(300, 270);
        stage.addActor(leaderBordButton);

        shopButton = new TextButton("SHOP", textButtonStyle);
        shopButton.setPosition(300, 210);
        stage.addActor(shopButton);

        quitButton = new TextButton("QUIT GAME", textButtonStyle);
        quitButton.setPosition(300, 90);
        stage.addActor(quitButton);

    }


    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        title.draw(batch,"GEOMETRYWARS",325,550);
        batch.end();
        stage.draw();

        if (quitButton.isChecked()){
            Gdx.app.exit();
        }




    }


}