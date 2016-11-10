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
import com.badlogic.gdx.utils.Array;
import com.group17.geowars.database.DBManager;
import javafx.scene.control.Tab;
import com.group17.geowars.database.DBManager.*;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class Menu {


    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private Skin skin1;
    private BitmapFont title;
    private TextButton quitButton;
    private TextButton shopButton;
    private TextButton playButton;
    private TextButton optionButton;
    private TextButton leaderBordButton;
    private TextButton profileButton;
    private TextButtonStyle textButtonStyle;
    private Label.LabelStyle labelStyle;
    private TextButton backButton;
    private Table table;
    private Label label1;


    public Menu(){
        mainMenu();
    }
    public void mainMenu(){
/*    BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        Label text;
        style.font = font;
        text = new Label("test",style);
        text.setText("test");
        text.setBounds(0,0,16,4);*/
        batch = new SpriteBatch();
        title = new BitmapFont();
        title.setColor(Color.WHITE);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        skin1 = new Skin();
        Pixmap pixmap = new Pixmap(200,50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        

        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        Label text;
        style.font = font;

        table = new Table();
        table.setFillParent(true);
        String name = "egoon";
        Integer score = 5000;

        //DATABASE connectie arcade
        DBManager manager = new DBManager();
        ArrayList highScores = manager.DBselectTOP10Highscore("arcade");

        table.add(new Label("Name",style)).width(100);
        table.add(new Label("Score",style)).width(100);
        table.row();
        table.add(new Label(name,style)).width(100);
        table.add(new Label(score.toString(),style)).width(100);
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