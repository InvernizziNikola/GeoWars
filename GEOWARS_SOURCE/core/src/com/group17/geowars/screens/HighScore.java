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

public class HighScore implements Screen, hasStage{


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


    public HighScore() {
        HighScore("arcade");
    }

    public void Buttons(){
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
                //Screen nextMenu = Managers.getMenuManager().getScreen("playArcade");
                //Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton multiPlayerButton = new TextButton("CO-OP",textButtonStyle);
        multiPlayerButton.setPosition(525,400);
        multiPlayerButton.setHeight(50);
        multiPlayerButton.setWidth(150);
        stage.addActor(multiPlayerButton);

        final TextButton backButton = new TextButton("BACK", textButtonStyle);
        backButton.setPosition(325, 100);
        backButton.setHeight(50);
        backButton.setWidth(150);
        stage.addActor(backButton);
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