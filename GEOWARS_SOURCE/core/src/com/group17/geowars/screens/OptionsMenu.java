package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.managers.MenuManager;
import com.group17.geowars.playerobjects.PlayerInput;
import com.group17.geowars.utils.MenuGrid;

import javax.swing.*;

/**
 * Created by michield on 23/11/2016.
 */
public class OptionsMenu extends MenuScreen implements hasStage {
    private BitmapFont text;
    private Skin skin;
    private TextField txtMovementLeft, txtMovementRight, txtMovementUp, txtMovementDown;
    private Integer SelectedKeyBinding = 1;

    private Table table;

    public OptionsMenu() {
        super();
        create();
    }

    public void Buttons() {

        final TextButton arrowkeysButton = newButton("Arrow keys", 152, 400, 150, 50, new MenuGrid(0, 0));
        final TextButton qwertyButton = newButton("Qwerty", 325, 400, 150, 50, new MenuGrid(1, 0));

        final TextButton azertyButton = newButton("Azerty", 525, 400, 150, 50, new MenuGrid(2, 0));

        final TextButton backButton = newButton("BACK", 325, 100, 150, 50, new MenuGrid(0, 1));


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

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);


            }
        });
        // TODO: 12/12/2016
        TextButton controllerBindings = newButton("VIEW CONTROLLER BINDINGs", 20, 20, 150, 50, new MenuGrid(1, 1));

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
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        test();
        
    }

}
