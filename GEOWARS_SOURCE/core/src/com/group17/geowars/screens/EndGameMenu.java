package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.utils.GAMESTATE;
/**
 * Created by michiel on 4/12/2016.
 */
public class EndGameMenu extends MenuScreen implements hasStage{

    private BitmapFont text;
    private Batch batch;
    public EndGameMenu()
    {
        super();
        create();
    }

    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        final TextButton replayButton = newButton("REPLAY", 50,350,150,50, new MenuGrid(0,0));
        replayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                replayButton.setChecked(false);
                Managers.getGameManager().gameState = GAMESTATE.GAMEPLAYING;
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("game");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton mainMenuButton = newButton("MAIN MENU",50,275,150,50, new MenuGrid(0,1));
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMenuButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton shareScoreButton = newButton("SHARE SCORE",50,200,150,50,new MenuGrid(0,2));
        shareScoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shareScoreButton.setChecked(false);
            }
        });

        text = new BitmapFont();
        text.setColor(Color.WHITE);

    }

    public void showText()
    {
        text.draw(batch,"SCORE: " + Managers.getGameManager().getScore(), 50, 475); // TODO score and higscore need to be added
        text.draw(batch,"HIGH SCORE: ", 50,450);
        text.draw(batch,"UPGRADES",400,525); //TODO level buttons need to be added
        text.draw(batch,"GLASS CANON", 300,450);
        text.draw(batch,"BIG BULLETS", 300,375);
        text.draw(batch,"THICK SKIN",300,300);
        text.draw(batch,"EMP",300,225);
        text.draw(batch,"FAST BULLETS",300,150);
    }

    @Override
    public void render(float delta) {

        super.render(delta);

        batch.begin();
        showText();
        batch.end();
    }
}
