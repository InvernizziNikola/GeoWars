package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    private boolean loading = false;
    private BitmapFont text;
    private Batch batch;
    private int width = GeoWars.WIDTH;
    // TODO: switch for dynamic
    private int height = GeoWars.HEIGHT;
    private ShopThread ShopThread;
    private ArrayList ShipData;
    private ArrayList DroneData;
    private String assaultName = "";
    private String assaultPrice = "";
    private String tankName = "";
    private String tankPrice = "";
    private String armoredShipName = "";
    private String armoredShipPrice = "";

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
    }

    public void createButtons()
    {
        final TextButton backButton = newButton("BACK",width-width/6,20,150,50, new MenuGrid(0,2));
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
        text.draw(batch,assaultName,width/12,height-height/4);
        text.draw(batch,"PRICE",width/3,height-height/3);
        text.draw(batch,assaultPrice,width/3,height/2+height/12);
        text.draw(batch,tankName,width/2-width/9,height-height/4);
        text.draw(batch,"PRICE",width/2+width/9,height-height/3);
        text.draw(batch,tankPrice,width/2+width/9,height/2+height/12);
        text.draw(batch,armoredShipName,width-width/3,height-height/4);
        text.draw(batch,"PRICE",width-width/10,height-height/3);
        text.draw(batch,armoredShipPrice, width-width/10,height/2+height/12);
        text.draw(batch,"DRONE",width/12,height/2-height/15);
        text.draw(batch,"_______",width/12,height/2-height/15-1);
        text.draw(batch,"DEFENSE",width/12,height/2-height/8);
        text.draw(batch,"PRICE",width/3,height/2-height/5);
        text.draw(batch,"defenseprice",width/3,height/5);
        text.draw(batch,"SUPPORT",width/2-width/9,height/2-height/8);
        text.draw(batch,"PRICE",width/2+width/9,height/2-height/5);
        text.draw(batch,"supportprice",width/2+width/9,height/5);
        text.draw(batch,"ATTACK",width-width/3,height/2-height/8);
        text.draw(batch,"PRICE",width-width/10,height/2-height/5);
        text.draw(batch,"attackprice",width-width/10,height/5);
    }

    public void showImages()
    {
        final Sprite assault = newImage("Speler_2", 300, 300, width/10, height/2-height/19);
        final Sprite destroyer = newImage("Destroyer", 300, 300, width/2-width/10, height/2-height/19);
        final Sprite tank = newImage("TankShip", 300, 300, width-width/3+50, height/2-height/19);
        final Sprite defense = newImage("defdrone",250 , 250, width/9, height/10);
        final Sprite support = newImage("supportdrone", 250, 250, width/2-width/15, height/10);
        final Sprite attack = newImage("attackdrone", 250, 250, width-width/4-50, height/10);
    }

    @Override
    public void setActive() {
        if(active)
            return;
        active = true;
        getAllData();
    }

    @Override
    public void setInActive(){
        active = false;

    }
    public void showLoading()
    {
        text.draw(batch, "Loading...", width/2, height/2);
    }
    public void getAllData()
    {
        if(loading)
            return;

        loading = true;
        ShopThread = new ShopThread();
        ShopThread.start();
    }
    @Override
    public void render(float delta)
    {

        super.render(delta);
        batch.begin();
        if(ShopThread != null && !ShopThread.finished())
        {
            showLoading();
        }
        if(ShopThread != null && ShopThread.finished())
        {
            ShipData = ShopThread.getShipData();
            DroneData = ShopThread.getDronesData();
            ShopThread = null;
            loading = false;

            System.out.println(ShipData);
        }
        if(ShopThread == null)
        {
            assaultName = ShipData.get(0).toString();
            assaultPrice = ShipData.get(4).toString();
            tankName = ShipData.get(5).toString();
            tankPrice = ShipData.get(9).toString();
            armoredShipName = ShipData.get(10).toString();
            armoredShipPrice = ShipData.get(14).toString();
            showText();
            createButtons();
        }
        batch.end();
        if (ShopThread == null)
        showImages();
    }
}
