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

        final ImageButton arrowkeysButton = newImageButton("ARROW KEYS", (width/2)-width/4, height-height/3, 150, 50, new MenuGrid(0, 0));
        final ImageButton qwertyButton = newImageButton("QWERTY", width/2-75, height-height/3, 150, 50, new MenuGrid(1, 0));
        final ImageButton azertyButton = newImageButton("AZERTY", (width/2)+width/6, height-height/3, 150, 50, new MenuGrid(2, 0));
        final ImageButton controllerBindings = newImageButton("VIEW CONTROLLER BINDINGS", width/2-125, height/4, 250, 50, new MenuGrid(0, 1));//TODO add action
        final ImageButton backButton = newImageButton("BACK", width/2-75, height/6, 150, 50, new MenuGrid(0, 2));


        /*--------------EVENT HANDLER--------------------------*/
        arrowkeysButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                arrowkeysButton.setChecked(false);

                //Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setArrowkeys();
                SelectedKeyBinding = 1;
                test();

            }
        });
        qwertyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                qwertyButton.setChecked(false);
                //Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setQwerty();
                SelectedKeyBinding = 2;
                test();
            }
        });
        azertyButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                azertyButton.setChecked(false);
                //Managers.getAccountManager().getAccounts().get(0).getPlayer().getPlayerInput().setAzerty();
                SelectedKeyBinding = 3;
                test();

            }
        });

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

    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        test();
        
    }

}
