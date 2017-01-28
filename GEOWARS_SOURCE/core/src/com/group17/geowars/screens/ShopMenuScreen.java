package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ShopThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;

/**
 * Created by michield on 12/12/2016.
 */
public class ShopMenuScreen extends MenuScreen implements iHasStage, iSetActive {
    protected Batch batch;
    protected int width = GeoWars.WIDTH;
    // TODO: switch for dynamic
    protected int height = GeoWars.HEIGHT;
    protected Color color;
    protected BitmapFont bigFont;
    protected BitmapFont title;
    public ShopMenuScreen() {
        super();
        create();
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        color = Color.WHITE;
        bigFont = Managers.getAssetManager().getGameFont(color, 15);
        title = Managers.getAssetManager().getGameFont(color,25);
        createButtons();
    }

    public void createButtons() {
        final ImageButton backButton = newImageButton("Menu_backicon", width - width / 6, 20, 150, 50, new MenuGrid(0, 2));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton buyShipTankButton = newImageButton("Menu_buyicon", width / 3, height / 2, 150, 75, new MenuGrid(0, 0));

        final ImageButton buyShipAssaultButton = newImageButton("Menu_buyicon", width / 2 + width / 9, height / 2, 150, 75, new MenuGrid(1, 0));

        final ImageButton buyShipDefenseButton = newImageButton("Menu_buyicon", width - width / 10, height / 2, 150, 75, new MenuGrid(2, 0));

        final ImageButton buyDroneDefenseButton = newImageButton("Menu_buyicon", width / 3, height / 8, 150, 50, new MenuGrid(0, 1));

        final ImageButton buyDroneSupportButton = newImageButton("Menu_buyicon", width / 2 + width / 9, height / 8, 150, 75, new MenuGrid(1, 1));

        final ImageButton buyDroneAttackButton = newImageButton("Menu_buyicon", width - width / 10, height / 8, 150, 75, new MenuGrid(2, 1));
    }

    public void showText() {
        title.draw(batch, "SHOP", width / 2 - 20, height - height / 8);
        bigFont.draw(batch, "SHIP", width / 12, height - height / 6);
        bigFont.draw(batch, "ASSAULT", width / 12, height - height / 4);
        bigFont.draw(batch, "PRICE", width / 3, height - height / 3);
        bigFont.draw(batch, "500", width / 3, height / 2 + height / 12);
        bigFont.draw(batch, "TANK", width / 2-width/15, height - height / 4);
        bigFont.draw(batch, "PRICE", width / 2 + width / 9, height - height / 3);
        bigFont.draw(batch, "500", width / 2 + width / 9, height / 2 + height / 12);
        bigFont.draw(batch, "ARMORED SHIP", width - width / 4 - 50, height - height / 4);
        bigFont.draw(batch, "PRICE", width - width / 10, height - height / 3);
        bigFont.draw(batch, "500", width - width / 10, height / 2 + height / 12);
        bigFont.draw(batch, "DRONE", width / 12, height / 2 - height / 15);
        bigFont.draw(batch, "DEFENSE", width / 12, height / 2 - height / 8);
        bigFont.draw(batch, "PRICE", width / 3, height / 2 - height / 5);
        bigFont.draw(batch, "defenseprice", width / 3, height / 5);
        bigFont.draw(batch, "SUPPORT", width / 2 - width / 15, height / 2 - height / 8);
        bigFont.draw(batch, "PRICE", width / 2 + width / 9, height / 2 - height / 5);
        bigFont.draw(batch, "supportprice", width / 2 + width / 9, height / 5);
        bigFont.draw(batch, "ATTACK", width - width / 4 - 50, height / 2 - height / 8);
        bigFont.draw(batch, "PRICE", width - width / 10, height / 2 - height / 5);
        bigFont.draw(batch, "attackprice", width - width / 10, height / 5);
    }

    public void showImages() {
        final Sprite assault = newImage("Speler_2", 300, 300, width / 10, height / 2 - height / 19);
        final Sprite destroyer = newImage("Destroyer", 300, 300, width / 2 - width / 10, height / 2 - height / 19);
        final Sprite tank = newImage("TankShip", 300, 300, width - width / 3 + 50, height / 2 - height / 19);
        final Sprite defense = newImage("defdrone", 250, 250, width / 9, height / 10);
        final Sprite support = newImage("supportdrone", 250, 250, width / 2 - width / 15, height / 10);
        final Sprite attack = newImage("attackdrone", 250, 250, width - width / 4 - 50, height / 10);
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

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        showText();
        batch.end();
        showImages();
    }
}
