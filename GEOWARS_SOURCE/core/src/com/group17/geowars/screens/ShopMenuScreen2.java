package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ShopThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.ArrayList;

/**
 * Created by michield on 12/12/2016.
 */
public class ShopMenuScreen2 extends MenuScreen implements iHasStage, iSetActive {

    private BitmapFont text;
    private Batch batch;
    private int coins = 1000;

    public ShopMenuScreen2() {
        super();
        create();
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);
        createButtons();
    }

    public void createButtons() {
        final ImageButton backButton = newImageButton("Menu_backicon", GeoWars.WIDTH - GeoWars.WIDTH / 6, 20, 150, 50, new MenuGrid(1, 2));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton extraLifeButton = newImageButton("extra_life", 50, GeoWars.HEIGHT-100, 250, 50, new MenuGrid(0, 0));
        extraLifeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buy(500);
            }
        });

        final ImageButton shieldButton = newImageButton("shield", 50, GeoWars.HEIGHT-250, 250, 50, new MenuGrid(0, 1));
        shieldButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buy(450);
            }
        });

        final ImageButton extraFirePowerButton = newImageButton("extra_fire_power", 50, GeoWars.HEIGHT - GeoWars.HEIGHT/4 - GeoWars.HEIGHT/6, 250, 50, new MenuGrid(0, 2));
        extraFirePowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buy(400);
            }
        });
    }

    public void showText()
    {
        text.draw(batch, ""+coins, GeoWars.WIDTH-50, GeoWars.HEIGHT-50);
        text.draw(batch, "price: 500", GeoWars.WIDTH/4, GeoWars.HEIGHT-75);
        text.draw(batch, "price: 450", GeoWars.WIDTH/4, GeoWars.HEIGHT - 225);
        text.draw(batch, "price: 400", GeoWars.WIDTH/4, GeoWars.HEIGHT - GeoWars.HEIGHT/4 - GeoWars.HEIGHT/6+25);
    }


    public int buy(int price)
    {
        if(coins < price)
        {
            return coins;
        }else {
            coins -= price;
            return coins;
        }
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
    }
}
