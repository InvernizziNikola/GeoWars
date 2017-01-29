package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.HighScoreMenuThread;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.managers.Managers;
import java.util.ArrayList;

public class HighScoreMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    private SpriteBatch batch;
    private Skin skin;
    private Skin skin1;
    private BitmapFont text;
    private Table table;
    private HighScoreMenuThread hsT;
    private ArrayList highScores;
    private boolean loading = false;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    protected Color color;
    protected BitmapFont bigFont;
    protected BitmapFont title;


    public HighScoreMenuScreen() {
        super();
        create();
    }
    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        color = Color.WHITE;
        bigFont = Managers.getAssetManager().getGameFont(color, 15);
        title = Managers.getAssetManager().getGameFont(color, 25);
        batch = new SpriteBatch();

        Buttons();
    }

    public void setActive()
    {
        if(active)
            return;
        active = true;
        getHighScore("arcade");
    }

    @Override
    public void setInActive() {
        active = false;
    }

    public void showLoading()
    {
        bigFont.draw(batch, "Loading...", 350, 380);
    }
    public void Buttons(){

        final ImageButton arcadeButton = newImageButton("Menu_arcadeicon",width/2-75,height-height/3,150,50, new MenuGrid(0, 0));

        final ImageButton multiPlayerButton = newImageButton("Menu_coopicon",width/2+width/8,height-height/3,150,50, new MenuGrid(1, 0));

        final ImageButton backButton = newImageButton("Menu_backicon", width/2-75,height/5,150,50, new MenuGrid(0, 1));

        /*--------------EVENT HANDLER--------------------------*/

        multiPlayerButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                multiPlayerButton.setChecked(false);
                getHighScore("coop");
            }
        });
        arcadeButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                arcadeButton.setChecked(false);
                getHighScore("arcade");
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });
    }

    public void getHighScore(String gameMode)
    {
        if(loading)
            return;

        loading = true;
        hsT = new HighScoreMenuThread(gameMode);
        hsT.start();
    }
    public void showHighscores() {

        stage.clear();
        Buttons();
        skin = new Skin();
        skin1 = new Skin();
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = bigFont;

        table = new Table();
        table.setFillParent(true);
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
        table.setPosition(GeoWars.WIDTH/20 - 50,GeoWars.HEIGHT/40 - 50);
        stage.addActor(table);
    }

    public void render(float delta) {

        super.render(delta);

        batch.begin();

        if(hsT != null && hsT.finished())
        {
            highScores = hsT.getData();
            hsT = null;

            loading = false;
            showHighscores();
        }
        if(hsT != null && !hsT.finished())
        {
            showLoading();
        }



        title.draw(batch, "GEOMETRYWARS", width/2-175, height-height/6);
        batch.end();
    }
}