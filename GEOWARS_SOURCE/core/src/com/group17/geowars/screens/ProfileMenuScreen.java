package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ProfileThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;

public class ProfileMenuScreen extends MenuScreen implements iHasStage, iSetActive {
    private BitmapFont text;
    private Batch batch;
    private ProfileThread ProfileThread;
    private ArrayList PlayerProfile;
    private Integer PlayerHighscore;
    private Table table;
    private String playerName,playerScore,gamesPlayed,hoursPlayed,playerLvl,currentShip,currentDrone;


    private boolean loading = false;

    public ProfileMenuScreen() {
        super();
        create();
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);
    }
    public void createButtons()
    {
        final TextButton clanButton = newButton("CLANS", GeoWars.WIDTH / 10, GeoWars.HEIGHT / 3, 150, 50, new MenuGrid(0, 0));
        clanButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clanButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("clanmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton backButton = newButton("BACK", GeoWars.HEIGHT / 2 - 75, GeoWars.HEIGHT / 10, 150, 50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                System.out.println("CLICKED: " + actor);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton upgradeButton = newButton("CHANGE/UPGRADE", GeoWars.WIDTH - GeoWars.WIDTH / 3, GeoWars.HEIGHT / 3, 150, 50, new MenuGrid(1, 0));
        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("upgrademenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }
    public void showText() {

        text.draw(batch, "GEOMETRYWARS", GeoWars.WIDTH / 2 - 70, GeoWars.HEIGHT - GeoWars.HEIGHT / 10);


        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;

        table = new Table();
        table.setFillParent(true);
        table.add(new Label("Username :", style)).width(200);
        table.add(new Label(playerName, style)).width(200);
        table.row();
        table.add(new Label("HIGHSCORE ARCADE: ", style)).width(200);
        table.add(new Label(playerScore,style)).width(200);
        table.row();
        table.add(new Label("GAMES PLAYED:", style)).width(200);
        table.add(new Label(gamesPlayed,style)).width(200);
        table.row();
        table.add(new Label("HOURS PLAYED:", style)).width(200);
        table.add(new Label(hoursPlayed,style)).width(200);
        table.row();
        table.add(new Label("LEVEL: ", style)).width(200);
        table.add(new Label(playerLvl,style)).width(200);
        table.row();
        table.add(new Label("CURRENT SHIP: ", style)).width(200);
        table.add(new Label(currentShip,style)).width(200);
        table.row();
        table.add(new Label("CURRENT Drone: ", style)).width(200);
        table.add(new Label(currentDrone,style)).width(200);

        table.row();

        stage.addActor(table);
    }

    public void setData(){
        playerScore = PlayerHighscore.toString();
        playerName = Managers.getAccountManager().getAccounts().get(0).name.toString();
        //// TODO: sql count highscore amount
        gamesPlayed=PlayerProfile.get(1).toString();
        //will come from steam
        hoursPlayed=PlayerProfile.get(2).toString();
        playerLvl=PlayerProfile.get(0).toString();
        //TODO  SET CURRENT SHIP and drone
        //currentShip=PlayerHighscore.get(0).toString();;
        //currentDrone=PlayerHighscore.get(0).toString();;
        currentShip = "Assault";
        currentDrone ="Fighter";
    }

    @Override
    public void setActive() {
        if (active)
            return;
        active = true;
        getAllData();
    }

    @Override
    public void setInActive() {
        active = false;
    }

    public void showLoading() {
        text.draw(batch, "Loading...", GeoWars.WIDTH/2-25, GeoWars.HEIGHT/2);
    }

    public void getAllData() {
        if (loading)
            return;

        loading = true;
        ProfileThread = new ProfileThread(Managers.getAccountManager().getAccounts().get(0).name.toString());

        ProfileThread.start();
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        batch.begin();
        if (ProfileThread != null && ProfileThread.finished()) {

            PlayerProfile = ProfileThread.getPlayerProfile();
            PlayerHighscore = ProfileThread.getPlayerHighscore();
            ProfileThread = null;

            loading = false;
            setData();
            showText();

        }
        if (ProfileThread != null && !ProfileThread.finished()) {
            showLoading();
        }
        if(ProfileThread == null)
        {
            showText();
            createButtons();
        }
        batch.end();
    }

}
