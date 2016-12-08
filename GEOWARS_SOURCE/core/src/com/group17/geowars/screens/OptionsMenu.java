package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
    }

    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        Buttons();
        TextButton controllerBindings = newButton("VIEW CONTROLLER BINDINGs",20,20,150,50, new MenuGrid(0,0));
    }
}
