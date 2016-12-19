package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 23/11/2016.
 */
public class OptionsMenuScreen extends MenuScreen implements iHasStage {
    private BitmapFont text;
    private Skin skin;
    private TextField txtMovementLeft, txtMovementRight, txtMovementUp, txtMovementDown;
    private Integer SelectedKeyBinding = 1;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;

    private Table table;

    public OptionsMenuScreen() {
        super();
        create();
    }

    public void Buttons() {

        final TextButton arrowkeysButton = newButton("ARROW KEYS", (width/2)-width/4, height-height/3, 150, 50, new MenuGrid(0, 0));
        final TextButton qwertyButton = newButton("QWERTY", width/2-75, height-height/3, 150, 50, new MenuGrid(1, 0));
        final TextButton azertyButton = newButton("AZERTY", (width/2)+width/6, height-height/3, 150, 50, new MenuGrid(2, 0));
        //final TextButton CustomButton = newButton("CUSTOM", (width/2)-width/4, height-height/3-100, 150, 50, new MenuGrid(1, 2));
        TextButton controllerBindings = newButton("VIEW CONTROLLER BINDINGS", width/2-width/4, height/4, 250, 50, new MenuGrid(1, 1));//TODO add action
       // final TextButton ApplyButton = newButton("APPLY", width/2-75, height/4, 150, 50, new MenuGrid(3, 0));
        final TextButton backButton = newButton("BACK", width/2-75, height/6, 150, 50, new MenuGrid(0, 1));


        /*--------------EVENT HANDLER--------------------------*/
        arrowkeysButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                arrowkeysButton.setChecked(false);

                Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setArrowkeys();
                SelectedKeyBinding = 1;
                test();

            }
        });
        qwertyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                qwertyButton.setChecked(false);
                Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setQwerty();
                SelectedKeyBinding = 2;
                test();
            }
        });
        azertyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                azertyButton.setChecked(false);
                Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setAzerty();
                SelectedKeyBinding = 3;
                test();

            }
        });
        /*
        CustomButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                CustomButton.setChecked(false);

               // Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setArrowkeys();
                SelectedKeyBinding = 4;
                test();

            }
        });
        ApplyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                ApplyButton.setChecked(false);

                SelectedKeyBinding = 4;
                String keyleft=txtMovementLeft.getText();
                String keyright=txtMovementRight.getText();
                String keyup=txtMovementUp.getText();
                String keydown=txtMovementDown.getText();
                Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setCustom(keyleft,keyright,keyup,keydown);
                test();
            }
        });*/
        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });



    }

    public void test() {
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
        txtStyle.fontColor = Color.WHITE;
        txtStyle.font = font;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;


/*
        //input fields
        if (SelectedKeyBinding == 1) {
            txtMovementLeft = new TextField("<-", txtStyle);
            txtMovementRight = new TextField("->", txtStyle);
            txtMovementUp = new TextField("->", txtStyle);
            txtMovementDown = new TextField("->", txtStyle);
        } else if (SelectedKeyBinding == 2) {
            txtMovementLeft = new TextField("D", txtStyle);
            txtMovementRight = new TextField("A", txtStyle);
            txtMovementUp = new TextField("W", txtStyle);
            txtMovementDown = new TextField("S", txtStyle);
        } else if (SelectedKeyBinding == 3) {
            txtMovementLeft = new TextField("Q", txtStyle);
            txtMovementRight = new TextField("D", txtStyle);
            txtMovementUp = new TextField("Z", txtStyle);
            txtMovementDown = new TextField("S", txtStyle);
        }else if (SelectedKeyBinding == 4) {
            txtMovementLeft = new TextField("", txtStyle);
            txtMovementRight = new TextField("", txtStyle);
            txtMovementUp = new TextField("", txtStyle);
            txtMovementDown = new TextField("", txtStyle);
        } else {
            System.out.println("error: there is no keybinding selected!!!");
        }

        // txtUsername.setMessageText("test");
        //add to stage


        table = new Table();
        table.setFillParent(true);
        table.add(new Label("movement left", style)).width(200);
        table.add(txtMovementLeft).width(200);
        table.row();
        table.add(new Label("movement right", style)).width(200);

        table.add(txtMovementRight).width(200);
        table.row();
        table.add(new Label("movement up", style)).width(200);

        table.add(txtMovementUp).width(200);
        table.row();
        table.add(new Label("movement down", style)).width(200);

        table.add(txtMovementDown).width(200);
        table.row();


        stage.addActor(table);
        */
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        test();
        
    }

}
