package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gamemodes.ArcadeCoopGame;
import com.group17.geowars.gamemodes.ArcadeSoloGame;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.utils.GAMESTATE;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 10/11/2016.
 */
public class CoopMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    protected float timer = 0;
    protected boolean t = false;

    public CoopMenuScreen() {
        super();
        create();
    }


    public void create() {
        Gdx.input.setInputProcessor(stage);

        final ImageButton addNewButton = newImageButton("Menu_addplayericon", GeoWars.WIDTH / 3 * 2 - 125, GeoWars.HEIGHT / 5 * 4, 250, 50, new MenuGrid(0, 0));
        addNewButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                addNewButton.setChecked(false);
                if (Managers.getControllerManager().getControllers().size() + 1 > Managers.getAccountManager().getAccounts().size()) {
                    if (t != true) {
                        System.out.println("pressed----------------------------------------------");
                        Managers.getAccountManager().createAccount("Player");// "+(Managers.getAccountManager().getAccounts().size()+1));
                        t = true;
                    }
                }

            }
        });


        final ImageButton startGameButton = newImageButton("Menu_starticon", GeoWars.WIDTH / 2 - 125, GeoWars.HEIGHT / 5 * 2, 250, 50, new MenuGrid(0, 1));startGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startGameButton.setChecked(false);

                if (Managers.getGameManager().getGame() != null)
                    return;

                Managers.getGameManager().newArcadeCoopGame();

                MenuScreen nextMenu = Managers.getScreenManager().getScreen("game");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });


        final ImageButton backButton = newImageButton("Menu_backicon", GeoWars.WIDTH / 2 - 125, GeoWars.HEIGHT / 5, 250, 50, new MenuGrid(0, 2));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }


    public void render(float deltaTime) {
        super.render(deltaTime);
        t = false;
        batch.begin();
        int count = 0;
        int offset = 30;


        text.draw(batch, "Maximum players (" + (Managers.getControllerManager().getControllers().size() + 1) + ")", GeoWars.WIDTH / 3 * 2 - 125, GeoWars.HEIGHT / 5 * 4 + 120);
        text.draw(batch, "Maximum Keyboard users: 1", GeoWars.WIDTH / 3 * 2 - 125, GeoWars.HEIGHT / 5 * 4 + 100);
        text.draw(batch, "Maximum Controller users: " + (Managers.getControllerManager().getControllers().size() - Managers.getControllerManager().getUnusedControllers().size()) + "/" + Managers.getControllerManager().getControllers().size(), GeoWars.WIDTH / 3 * 2 - 125, GeoWars.HEIGHT / 5 * 4 + 80);

        BitmapFont newFont = Managers.getAssetManager().getGameFont(Color.WHITE, 30);
        newFont.draw(batch, "Players:", GeoWars.WIDTH / 3 - 100, GeoWars.HEIGHT / 5 * 4 + 40);

        for (Account a : Managers.getAccountManager().getAccounts()) {
            String input = "keyboard";
            Controller c = a.getController();
            if(c != null)
                input = c.getName();
            text.draw(batch, a.name + " plays with " + input, GeoWars.WIDTH / 3 - 100, GeoWars.HEIGHT / 5 * 4 - offset * count);
            count++;
        }
        batch.end();

    }

    @Override
    public void setActive() {
        if (active)
            return;
        active = true;
    }

    @Override
    public void setInActive() {
        active = false;
    }
}
