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
import com.group17.geowars.playerobjects.PlayerInput;
import com.group17.geowars.utils.MenuGrid;

import javax.swing.*;

/**
 * Created by michield on 23/11/2016.
 */
public class OptionsMenu extends MenuScreen implements hasStage {
    private BitmapFont text;
    private Skin skin;
    private TextField txtMovementLeft,txtMovementRight,txtMovementUp,txtMovementDown, txtPassword;

    private Table table;
    public OptionsMenu()
    {
        super();
        create();
    }

    public void Buttons(){

        final TextButton arrowkeys = newButton("Arrow keys",152,400,150,50, new MenuGrid(0, 0));
        final TextButton qwerty = newButton("Qwerty",325,400,150,50, new MenuGrid(1, 0));

        final TextButton azerty = newButton("Azerty",525,400,150,50, new MenuGrid(2, 0));

        final TextButton backButton = newButton("BACK", 325,100,150,50, new MenuGrid(0, 1));


        /*--------------EVENT HANDLER--------------------------*/
        arrowkeys.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                arrowkeys.setChecked(false);
                //not working
                PlayerInput manager = new PlayerInput(null,null);

                manager.setArrowkeys();
            }
        });
        qwerty.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                qwerty.setChecked(false);
                PlayerInput manager = new PlayerInput(null,null);

                manager.setQwerty();
            }
        });
        azerty.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                azerty.setChecked(false);
                PlayerInput manager = new PlayerInput(null,null);

                manager.setAzerty();
            }
        });

        backButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);

            }
        });
        test();
    }
    public void test(){
        //skin and style
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
        txtMovementLeft = new TextField("<-", txtStyle);
        txtMovementRight = new TextField("->", txtStyle);
        txtMovementUp = new TextField("->", txtStyle);
        txtMovementDown = new TextField("->", txtStyle);
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

    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        Buttons();
        TextButton controllerBindings = newButton("VIEW CONTROLLER BINDINGs",20,20,150,50, new MenuGrid(0,0));
    }

}
