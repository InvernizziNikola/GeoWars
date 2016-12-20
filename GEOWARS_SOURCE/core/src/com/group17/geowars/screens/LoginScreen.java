package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

public class LoginScreen extends MenuScreen implements iHasStage {
    private BitmapFont text;
    private Skin skin;
    private TextField TxtUsername, TxtPassword;
    private Integer SelectedKeyBinding = 1;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    private Batch batch;
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
        skin = new Skin();
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont font = new BitmapFont();
        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle();
        TextField.TextFieldStyle txtStylePassword = new TextField.TextFieldStyle();
        txtStyle.fontColor = Color.WHITE;
        txtStyle.font = font;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;




            TxtUsername = new TextField("", txtStyle);
            TxtPassword = new TextField("", txtStyle);
        TxtUsername.setMessageText("type username here");
        TxtPassword.setMessageText("type password here");
            TxtPassword.setPasswordMode(true);
            TxtPassword.setPasswordCharacter('*');




        Label errorlable = new Label("", style);

        table = new Table();
        table.setFillParent(true);
        table.add(errorlable);
        table.row();
        table.add(new Label("Username", style)).width(200);
        table.add(TxtUsername).width(200);
        table.row();
        table.add(new Label("Password", style)).width(200);
        table.add(TxtPassword).width(200);
        table.row();

        final TextButton loginButton = newButton("Login",GeoWars.WIDTH/2-100, GeoWars.HEIGHT-GeoWars.HEIGHT/4, 200, 75, new MenuGrid(0, 0));
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                loginButton.setChecked(false);
                System.out.println("username: "+ TxtUsername.getText()+" Password: "+TxtPassword.getText());
                //getLogin(TxtUsername.getText(),TxtPassword.getText());
                if(loading)
                    return;

                loading = true;
                System.out.println("test");
                LT = new LoginThread(TxtUsername.getText(),TxtPassword.getText());
                LT.start();


            }
        });

        stage.addActor(table);

    }
    public void render(float deltaTime) {
        super.render(deltaTime);

        if(LT != null && LT.finished())
        {
            loggedIn = LT.getLoggedIn();
            LT = null;

            loading = false;
        }
        if(LT != null && !LT.finished())
        {
            showLoading();
        }

    }

    public void showLoading()
    {
//        text.draw(batch, "Loading...", 350, 380);
    }
    public void setPlayername(){
       PlayerName = TxtUsername.getText();
        //TODO SET Username

    }
    public void getLogin(String Username,String Password) {
        if (loading) {
            System.out.println("i ran x amount of times");


            Player = DBManager.getInstance().DBselectLogin(Username, Password);
            loading = false;
            if (Player.size() >= 1) {
                System.out.println("You are loggedin!");
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            } else {
                System.out.println("Login failed!");
                //errorlable.setText("Incorrect username or password");

            }
        }
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);

        setStage();
        
    }

}
