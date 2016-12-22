package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.database.Threads.HighScoreMenuThread;
import com.group17.geowars.database.Threads.LoginThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;

public class LoginScreen extends MenuScreen implements iHasStage, iSetActive {
    private Skin skin;
    private TextField TxtUsername, TxtPassword;
    private Integer SelectedKeyBinding = 1;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    private boolean loading = false;
    private boolean loggedIn = false;
    private Label errorlable;
    private ArrayList Player;
    private String PlayerName;

    private LoginThread LT;

    private Table table;

    public LoginScreen() {
        super();
        create();
    }

    public void Buttons() {

       final TextButton backButton = newButton("continue", width/2-75, height/6, 150, 50, new MenuGrid(0, 1));

        /*--------------EVENT HANDLER--------------------------*/
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }

    public void setStage() {
        //skin and style
        stage.clear();
        Buttons();
        Label errorlable = new Label("", style());


        final TextField username = newTextField();
        final TextField password = newPwField();
        table = new Table();
        table.setFillParent(true);
        table.add(errorlable);
        table.row();

        table.add(new Label("Username", style())).width(200);
        table.add(username).width(200);
        table.row();
        table.add(new Label("Password", style())).width(200);
        table.add(password).width(200);
        table.row();

        final TextButton loginButton = newButton("Login",GeoWars.WIDTH/2-100, GeoWars.HEIGHT-GeoWars.HEIGHT/4, 200, 75, new MenuGrid(0, 0));
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                loginButton.setChecked(false);
                System.out.println("username: "+ TxtUsername.getText()+" Password: "+TxtPassword.getText());
                //getLogin(TxtUsername.getText(),TxtPassword.getText());
                TxtPassword.setDisabled(true);
                TxtUsername.setDisabled(true);
                if(loading)
                    return;
                loading = true;
                System.out.println("Login button pressed");
                LT = new LoginThread(TxtUsername.getText(),TxtPassword.getText());
                LT.start();
            }
        });

        stage.addActor(table);

    }
    public void render(float deltaTime) {
        super.render(deltaTime);

        if(LT != null ) {
            if (LT.finished()) {
                loggedIn = LT.getLoggedIn();
                LT = null;
                loading = false;
                System.out.println(loggedIn);
                if(loggedIn)
                {
                    Managers.getAccountManager().createAccount(TxtUsername.getText()).main = true;


                    MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                    Managers.getScreenManager().setScreen(nextMenu);
                }
                TxtPassword.setDisabled(false);
                TxtUsername.setDisabled(false);
            }
            else{
                showLoading();
            }
        }

    }

    public void showLoading()
    {
        batch.begin();
        text.draw(batch, "Loading...", 350, 380);
        batch.end();
    }
    public void setPlayername(){
       PlayerName = TxtUsername.getText();
        //TODO SET Username

    }

    public void create() {
        Gdx.input.setInputProcessor(stage);

        setStage();
    }

    @Override
    public void setActive() {
        if(active)
            return;
        active = true;
    }

    @Override
    public void setInActive() {
        active = false;

    }
}
