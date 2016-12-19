package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 12/12/2016.
 */
public class ShopMenuScreen extends MenuScreen implements hasStage{

    private BitmapFont text;
    private Batch batch;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;

    public ShopMenuScreen()
    {
        super();
        create();
    }

    public void create()
    {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);

        final TextButton backButton = newButton("BACK",width-width/10,20,150,50, new MenuGrid(0,2));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final TextButton buyShipTankButton = newButton("BUY",width/3,height/2,55,25, new MenuGrid(0,0));

        final TextButton buyShipAssaultButton = newButton("BUY", width/2+width/9,height/2,55,25, new MenuGrid(1,0));

        final TextButton buyShipDefenseButton = newButton("BUY",width-width/10,height/2,55,25, new MenuGrid(2,0));

        final TextButton buyDroneDefenseButton = newButton("BUY",width/3,height/8,55,25, new MenuGrid(0,1));

        final TextButton buyDroneSupportButton = newButton("BUY", width/2+width/9,height/8,55,25,new MenuGrid(1,1));

        final TextButton buyDroneAttackButton = newButton("BUY",width-width/10,height/8,55,25,new MenuGrid(2,1));
    }

    public void showText()
    {

        text.draw(batch,"SHOP",width/2-20,height-height/8);
        text.draw(batch,"SHIP",width/12,height-height/6);
        text.draw(batch,"____",width/12,height-height/6-1);
        text.draw(batch,"TANK",width/12,height-height/4);
        text.draw(batch,"PRICE",width/3,height-height/3);
        text.draw(batch,"tankprice",width/3,height/2+height/12);
        text.draw(batch,"ASSAULT",width/2-width/9,height-height/4);
        text.draw(batch,"PRICE",width/2+width/9,height-height/3);
        text.draw(batch,"assaultprice",width/2+width/9,height/2+height/12);
        text.draw(batch,"DEFENSE",width-width/3,height-height/4);
        text.draw(batch,"PRICE",width-width/10,height-height/3);
        text.draw(batch,"defenseprice", width-width/10,height/2+height/12);
        text.draw(batch,"DRONE",width/12,height/2-height/15);
        text.draw(batch,"_______",width/12,height/2-height/15-1);
        text.draw(batch,"DEFENCE",width/12,height/2-height/8);
        text.draw(batch,"PRICE",width/3,height/2-height/5);
        text.draw(batch,"defenseprice",width/3,height/5);
        text.draw(batch,"SUPPORT",width/2-width/9,height/2-height/8);
        text.draw(batch,"PRICE",width/2+width/9,height/2-height/5);
        text.draw(batch,"supportprice",width/2+width/9,height/5);
        text.draw(batch,"ATTACK",width-width/3,height/2-height/8);
        text.draw(batch,"PRICE",width-width/10,height/2-height/5);
        text.draw(batch,"attackprice",width-width/10,height/5);
    }

    @Override
    public void render(float delta)
    {
        super.render(delta);
        batch.begin();
        showText();
        batch.end();
    }
}
