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
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("shopmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton extraLifeButton = newImageButton("extra_life", 50, GeoWars.HEIGHT/2 + GeoWars.HEIGHT/4 -40, 250, 50, new MenuGrid(0, 0));
        extraLifeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 500;
                if (price < coins) {
                    if (extraLife < max) {
                        buy(price);
                        extraLife++;
                        Managers.getAccountManager().getAccounts().get(0).setHpUpgrade(50);
                    }
                }
            }
        });

        final ImageButton shieldButton = newImageButton("shield", 50, GeoWars.HEIGHT/2 + GeoWars.HEIGHT/8 -40, 250, 50, new MenuGrid(0, 1));
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

        final ImageButton extraFirePowerButton = newImageButton("extra_fire_power", 50, GeoWars.HEIGHT/2 - 40, 250, 50, new MenuGrid(0, 2));
        extraFirePowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 400;
                if (price < coins) {
                    if (extraFirePower < max) {
                        buy(price);
                        extraFirePower ++;
                        Managers.getAccountManager().getAccounts().get(0).setFirePowerUpgrade(0.15f);

                    }
                }
            }
        });

        final ImageButton extraSpeedButton = newImageButton("extra_speed", 50, GeoWars.HEIGHT / 2 - GeoWars.HEIGHT/6, 250, 50, new MenuGrid(0, 3));
        extraSpeedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max = 1;
                price = 350;
                if (price < coins) {
                    if (extraSpeed < max) {
                        buy(price);
                        extraSpeed ++;
                        Managers.getAccountManager().getAccounts().get(0).setSpeedPowerUpgrade(100);

                    }
                }
            }
        });

        final ImageButton currencyButton = newImageButton("credit ;)", GeoWars.WIDTH - GeoWars.WIDTH/4 - 40, GeoWars.HEIGHT - 85, 50, 50, new MenuGrid(1, 0));

        final ImageButton pouchOfCrystalsButton = newImageButton("shop2", GeoWars.WIDTH/2 + 50, GeoWars.HEIGHT/2 + GeoWars.HEIGHT/20, 200, 400, new MenuGrid(1,0));
        pouchOfCrystalsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyCredits(220);
            }
        });

        final ImageButton bagOfCrystalsButton = newImageButton("shop5", GeoWars.WIDTH/2 + 260, GeoWars.HEIGHT/2 + GeoWars.HEIGHT/20, 200, 400, new MenuGrid(2,0));
        bagOfCrystalsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyCredits(610);
            }
        });

        final ImageButton boxOfCrystalsButton = newImageButton("shop10", GeoWars.WIDTH/2 + GeoWars.WIDTH/4 - 10, GeoWars.HEIGHT/2 + GeoWars.HEIGHT/20, 200, 400, new MenuGrid(3, 0));
        boxOfCrystalsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyCredits(1340);
            }
        });

        final ImageButton chestOfCrystalsButton = newImageButton("shop20", GeoWars.WIDTH/2 + 50, GeoWars.HEIGHT/4 - 20, 200, 400, new MenuGrid(1,1));
        chestOfCrystalsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyCredits(2800);
            }
        });

        final ImageButton crateOfCrystalsButton = newImageButton("shop50", GeoWars.WIDTH/2 + 260, GeoWars.HEIGHT/4 - 20, 200, 400, new MenuGrid(2, 1));
        crateOfCrystalsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buyCredits(7370);
            }
        });
    }

    public void showText() {
        bigFont.draw(batch, "" + coins, GeoWars.WIDTH - GeoWars.WIDTH/4 +10, GeoWars.HEIGHT - 50);
        bigFont.draw(batch, "price 500 current " + extraLife, GeoWars.WIDTH / 4, GeoWars.HEIGHT /2 + GeoWars.HEIGHT/4);
        bigFont.draw(batch, "price 450 current " + shield, GeoWars.WIDTH / 4, GeoWars.HEIGHT /2 + GeoWars.HEIGHT/8);
        bigFont.draw(batch, "price 400 current " + extraFirePower, GeoWars.WIDTH / 4, GeoWars.HEIGHT/2);
        bigFont.draw(batch, "price 350 current " + extraSpeed, GeoWars.WIDTH / 4, GeoWars.HEIGHT / 2 - GeoWars.HEIGHT/6 + 45);
    }


    public int buy(int price) {

        if (coins < price) {
            return coins;
        } else {
            coins -= price;
            return coins;
        }

    }

    public int buyCredits(int amount)
    {
        coins += amount;
        return coins;
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
