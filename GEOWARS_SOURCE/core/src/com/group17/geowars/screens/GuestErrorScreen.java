package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.HighScoreMenuThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;

public class GuestErrorScreen extends MenuScreen implements iHasStage, iSetActive {

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


    public GuestErrorScreen() {
        super();
        create();
    }
    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        text = new BitmapFont();
        text.setColor(Color.WHITE);

        batch = new SpriteBatch();

        Buttons();
    }

    public void setActive()
    {
        if(active)
            return;
        active = true;
    }

    @Override
    public void setInActive() {
        active = false;
    }

    public void Buttons(){

        final TextButton backButton = newButton("BACK", width/2-75,height/5,150,50, new MenuGrid(0, 0));

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });
    }

    public void render(float delta) {

        super.render(delta);
        batch.begin();
        //// TODO: Add pic?
        text.draw(batch, "You are a GUEST! This function is disabled for you" +
                ".... Please login and come back later!", width/2-300, height-height/4);
        batch.end();
    }
}