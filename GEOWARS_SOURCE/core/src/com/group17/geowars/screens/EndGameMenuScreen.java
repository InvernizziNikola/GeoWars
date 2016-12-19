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
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.HighScoreMenuThread;
import com.group17.geowars.database.Threads.SaveScoreToDBThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.utils.GAMESTATE;
/**
 * Created by michiel on 4/12/2016.
 */
public class EndGameMenuScreen extends MenuScreen implements iHasStage{

    private BitmapFont text;
    private Batch batch;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    private SaveScoreToDBThread SaveScoreThread;
    private int Score = 0;
    private String GameMode;
    private String PlayerName;
    public EndGameMenuScreen()
    {
        super();
        create();
    }

    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        final TextButton replayButton = newButton("REPLAY", width/10,height/2+height/8,150,50, new MenuGrid(0,0));
        replayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                replayButton.setChecked(false);
                Managers.getGameManager().gameState = GAMESTATE.GAMEPLAYING;
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("game");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton mainMenuButton = newButton("MAIN MENU",width/10,height/2,150,50, new MenuGrid(0,1));
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMenuButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton shareScoreButton = newButton("SHARE SCORE",width/10,height/2-height/8,150,50,new MenuGrid(0,2));
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
        Score = Managers.getGameManager().getScore();
        text.draw(batch,"SCORE: " + Score, width/10, height/2+height/3); // TODO score and higscore need to be added
        text.draw(batch,"HIGH SCORE: ", width/10,height/2+height/4);
        text.draw(batch,"UPGRADES",width/2+width/20,height/2+height/3); //TODO level buttons need to be added
        text.draw(batch,"GLASS CANON", 300,450);
        text.draw(batch,"BIG BULLETS", 300,375);
        text.draw(batch,"THICK SKIN",300,300);
        text.draw(batch,"EMP",300,225);
        text.draw(batch,"FAST BULLETS",300,150);
        //TODO getGameMode
        //TODO getPlayerName
        GameMode = "Arcade";
        PlayerName = "egoon";
        setHighScore(PlayerName,Score,GameMode);
    }
    public void setHighScore(String Playername,Integer Score,String Gamemode)
    {
        SaveScoreThread = new SaveScoreToDBThread(Playername,Score,Gamemode);
        SaveScoreThread.start();
    }
    @Override
    public void render(float delta) {

        super.render(delta);

        batch.begin();
        showText();
        batch.end();
    }
}
