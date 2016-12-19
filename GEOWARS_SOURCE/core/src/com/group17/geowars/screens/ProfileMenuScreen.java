package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ProfileThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;


/**
 * Created by michield on 10/11/2016.
 */
public class ProfileMenuScreen extends MenuScreen implements iHasStage, iSetActive {
    private BitmapFont text;
    private Batch batch;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    private ProfileThread ProfileThread;
    private ArrayList PlayerProfile;
    private ArrayList PlayerHighscore;
    private ArrayList PlayerCampaignLvl;


    private boolean loading = false;
    public ProfileMenuScreen()
    {
        super();
        create();
    }

    public void create(){
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);

        final TextButton clanButton = newButton("CLANS",width/10,height/3,150,50, new MenuGrid(0, 0));
        clanButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clanButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("clanmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton backButton = newButton("BACK",width/2-75,height/10,150,50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                System.out.println("CLICKED: " + actor);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton upgradeButton = newButton("CHANGE/UPGRADE",width-width/3,height/3,150,50,new MenuGrid(1,0));
        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("upgrademenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

    }

    public void showText()
    {

        text.draw(batch, "GEOMETRYWARS",width/2-70,height-height/10);
        //TODO values need to be inserted
        text.draw(batch, "USERNAME",width/2-50,height-height/6);
        text.draw(batch, "HIGHSCORE ARCADE: ",width/10,height-height/3);
        text.draw(batch,"CAMPAIGN COMPLETION: %",width/10,height-height/3-50);
        text.draw(batch, "GAMES PLAYED: ",width/10,height-height/3-100);
        text.draw(batch, "HOURS PLAYED: ",width/10,height/2+25);
        text.draw(batch,"LEVEL: ",width/10,height/2-25);
        text.draw(batch, "CURRENT SHIP & DRONE",width-width/3,height-height/3);
        text.draw(batch,"CURRENT SHIP",width-width/6,height-height/3-100);
        text.draw(batch,"CURRENT DRONE", width-width/6,height/2-25);
    }

    @Override
    public void setActive() {
        if(active)
            return;
        active = true;
        getAllData();
    }

    @Override
    public void setInActive() {
        active = false;
    }

    public void showLoading()
    {
        text.draw(batch, "Loading...", 350, 380);
    }
    public void getAllData()
    {
        if(loading)
            return;

        loading = true;
        //TODO playername ophalen
        ProfileThread = new ProfileThread("egoon");
        ProfileThread.start();
    }
    @Override
    public void render(float delta)
    {

        super.render(delta);
        batch.begin();
        if(ProfileThread != null && ProfileThread.finished())
        {
            PlayerProfile = ProfileThread.getPlayerProfile();
            PlayerHighscore = ProfileThread.getPlayerHighscore();
            PlayerCampaignLvl = ProfileThread.getPlayersCampaignLvl();
            ProfileThread = null;

            loading = false;
            showText();
            System.out.println(PlayerProfile);

        }
        if(ProfileThread != null && !ProfileThread.finished())
        {
            showLoading();
        }
        showText();
        batch.end();
    }

}
