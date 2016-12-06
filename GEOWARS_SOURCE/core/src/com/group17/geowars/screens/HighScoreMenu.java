package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.managers.Managers;
import java.util.ArrayList;

public class HighScoreMenu extends MenuScreen implements hasStage{

    private SpriteBatch batch;
    private Skin skin;
    private Skin skin1;
    private BitmapFont title;
    private Table table;
    private ArrayList highScores;
    private String GameModeString;
    private boolean runones =true;



    public HighScoreMenu() {
        super();
        create();
    }
    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        showHighscores("arcade");
    }
    public void Buttons(){

        final TextButton campaignButton = newButton("CAMPAIGN",152,400,150,50, new MenuGrid(0, 0));

        final TextButton arcadeButton = newButton("ARCADE",325,400,150,50, new MenuGrid(1, 0));

        final TextButton multiPlayerButton = newButton("CO-OP",525,400,150,50, new MenuGrid(2, 0));

        final TextButton backButton = newButton("BACK", 325,100,150,50, new MenuGrid(0, 1));

        /*--------------EVENT HANDLER--------------------------*/
        campaignButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                campaignButton.setChecked(false);
                showHighscores("campaign");
            }
        });
        multiPlayerButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                campaignButton.setChecked(false);
                showHighscores("coop");
            }
        });
        arcadeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                arcadeButton.setChecked(false);
                showHighscores("arcade");
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);

            }
        });
    }
    public void showHighscores(String GameMode) {

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
        stage.clear();
        Buttons();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        skin1 = new Skin();
        //Buttons();
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

        GameModeString = GameMode;
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                //DATABASE connectie arcade
                DBManager manager = new DBManager();

                highScores = manager.DBselectTOP10Highscore(GameModeString);
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        // process the result, e.g. add it to an Array<Result> field of the ApplicationListener.

                        if(runones){
                            runones = false;
                            
                            showHighscores(GameModeString);}
                        MenuScreen nextMenu = Managers.getMenuManager().currentScreen();
                        Managers.getMenuManager().setScreen(nextMenu);


                    }
                });



            }
        });
        t1.start();


        System.out.println(highScores);


        table.add(new Label("Name", style)).width(200);
        table.add(new Label("Score", style)).width(200);
        table.row();

        if(highScores != null) {
            Integer highScoreAmount = highScores.size();
            for (int i = 0; i < highScoreAmount; i++) {
                table.add(new Label(highScores.get(i).toString(), style)).width(200);
                table.add(new Label(highScores.get(i + 1).toString(), style)).width(200);
                table.row();
                i++;
            }
        }

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        super.render(delta);

        batch.begin();
        title.draw(batch, "GEOMETRYWARS", 325, 550);
        batch.end();
    }
}