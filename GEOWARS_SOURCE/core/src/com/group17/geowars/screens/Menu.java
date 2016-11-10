package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import javafx.scene.control.Tab;

import java.nio.charset.Charset;

public class Menu {


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
    private Table table;
    private Label label1;


    public Menu(){
        mainMenu();
    }
    public void mainMenu(){

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

        stage.addActor(playButton);

        profileButton = new TextButton("PROFILE", textButtonStyle);
        stage.addActor(profileButton);

        optionButton = new TextButton("OPTIONS", textButtonStyle);
        stage.addActor(optionButton);

        leaderBordButton = new TextButton("LEADERBORDS", textButtonStyle);
        stage.addActor(leaderBordButton);

        shopButton = new TextButton("SHOP", textButtonStyle);
        stage.addActor(shopButton);

        quitButton = new TextButton("QUIT GAME", textButtonStyle);
        stage.addActor(quitButton);

        String value1 = "test1";
        String value2 = "test2";
        String value3 = "test3";
        String value4 = "test4";
        CharSequence  value5 = "test5";
        //Label label1 = new Label("test", skin);

        table = new Table();
        //table1.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
      //  table.setSkin(skin);
        table.setFillParent(true);

 
        table.add(shopButton);
        table.row();
        table.add(leaderBordButton);
        table.add(profileButton);
        stage.addActor(table);

    }


    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        title.draw(batch,"GEOMETRYWARS",325,550);
        batch.end();
        stage.draw();






    }


}