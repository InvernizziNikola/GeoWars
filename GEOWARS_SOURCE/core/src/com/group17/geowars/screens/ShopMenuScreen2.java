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


    protected Batch batch;
    protected int coins = 1000;
    protected int max;
    protected int extraFirePower = 0;
    protected int extraLife = 0;
    protected Color color;
    protected BitmapFont bigFont;
    protected int extraSpeed;
    protected int price;
    protected int shield;


    public ShopMenuScreen2() {
        super();
        create();
        color = Color.WHITE;
        bigFont = Managers.getAssetManager().getGameFont(color, 25);
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);
        createButtons();
    }

    public void createButtons() {
        final ImageButton backButton = newImageButton("Menu_backicon", GeoWars.WIDTH - GeoWars.WIDTH / 6, 20, 150, 50, new MenuGrid(1, 3));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton extraLifeButton = newImageButton("extra_life", 50, GeoWars.HEIGHT - 100, 250, 50, new MenuGrid(0, 0));
        extraLifeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 500;
                if (price < coins) {
                    if (extraLife < max) {
                        buy(price);
                        extraLife++;
                    }
                }
            }
        });

        final ImageButton shieldButton = newImageButton("shield", 50, GeoWars.HEIGHT - 250, 250, 50, new MenuGrid(0, 1));
        shieldButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                price = 450;
                max = 1;
                if(price < coins)
                {
                    if (shield < max)
                    {
                        buy(price);
                        shield++;
                    }
                }
            }
        });

        final ImageButton extraFirePowerButton = newImageButton("extra_fire_power", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4 + 50 - GeoWars.HEIGHT / 6, 250, 50, new MenuGrid(0, 2));
        extraFirePowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 400;
                if (price < coins) {
                    if (extraFirePower < max) {
                        buy(price);
                        extraFirePower ++;
                    }
                }
            }
        });

        final ImageButton extraSpeedButton = newImageButton("extra_speed", 50, GeoWars.HEIGHT / 2, 250, 50, new MenuGrid(0, 3));
        extraSpeedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 350;
                if (price < coins) {
                    if (extraSpeed < max) {
                        buy(price);
                        extraSpeed ++;
                    }
                }
            }
        });

        final ImageButton currencyButton = newImageButton("credit ;)", GeoWars.WIDTH - 250, GeoWars.HEIGHT - 85, 50, 50, new MenuGrid(1, 0));
    }

    public void showText() {
        bigFont.draw(batch, "" + coins, GeoWars.WIDTH - 150, GeoWars.HEIGHT - 50);
        bigFont.draw(batch, "price 500 current " + extraLife, GeoWars.WIDTH / 4, GeoWars.HEIGHT - 65);
        bigFont.draw(batch, "price 450 current " + shield, GeoWars.WIDTH / 4, GeoWars.HEIGHT - 215);
        bigFont.draw(batch, "price 400 current " + extraFirePower, GeoWars.WIDTH / 4, GeoWars.HEIGHT - GeoWars.HEIGHT / 4 - GeoWars.HEIGHT / 6 + 85);
        bigFont.draw(batch, "price 350 current " + extraSpeed, GeoWars.WIDTH / 4, GeoWars.HEIGHT / 2 + 35);
    }


    public int buy(int price) {

        if (coins < price) {
            return coins;
        } else {
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
