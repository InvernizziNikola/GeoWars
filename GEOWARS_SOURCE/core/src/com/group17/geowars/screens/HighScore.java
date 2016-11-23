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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.managers.Managers;

import java.util.ArrayList;

public class HighScore extends MenuScreen{


    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private Skin skin1;
    private BitmapFont title;
    private Table table;



    public HighScore() {
        HighScore("arcade");
    }

    public void Buttons(){

        TextButton campaignButton = newButton("CAMPAIGN",152,400,150,50);

        TextButton arcadeButton = newButton("ARCADE",325,400,150,50);
        arcadeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                //Screen nextMenu = Managers.getMenuManager().getScreen("playArcade");
                //Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        TextButton multiPlayerButton = newButton("CO-OP",525,400,150,50);


        TextButton backButton = newButton("BACK", 325,100,150,50);

        /*--------------EVENT HANDLER--------------------------*/
        campaignButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                HighScore("campaign");
            }
        });
        multiPlayerButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

                System.out.println("CLICKED: " + actor);
                HighScore("coop");
            }
        });
        arcadeButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                stage.clear();

                System.out.println("CLICKED: " + actor);
                HighScore("arcade");
            }
        });

        /*backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("CLICKED: " + actor);
                Screen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);

            }
        });*/


    }
    public void HighScore(String GameMode) {

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
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        skin1 = new Skin();
        Buttons();
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
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
        ArrayList highScores = manager.DBselectTOP10Highscore(GameMode);
        table.add(new Label("Name", style)).width(200);
        table.add(new Label("Score", style)).width(200);
        table.row();
        Integer highScoreAmount = highScores.size();
        for (int i = 0; i <highScoreAmount ; i++) {
            table.add(new Label(highScores.get(i).toString(), style)).width(200);
            table.add(new Label(highScores.get(i + 1).toString(), style)).width(200);
            table.row();
            i++;
        }
        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        title.draw(batch, "GEOMETRYWARS", 325, 550);
        batch.end();
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