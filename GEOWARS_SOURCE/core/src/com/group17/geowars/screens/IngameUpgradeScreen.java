package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.GAMESTATE;
import com.group17.geowars.utils.MenuGrid;

public class IngameUpgradeScreen extends MenuScreen implements iHasStage, iSetActive {

    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;
    private boolean quit=false;

    public IngameUpgradeScreen() {
        super();
        create();
    }
// next level, Quite, speed, firepower,heal

    public void create() {
        Gdx.input.setInputProcessor(stage);

        final ImageButton speedButton = newImageButton("Menu_speedicon", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0,0));
        speedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                speedButton.setChecked(false);
                //set speed here
                //set firepower here
                for(Player p : Managers.getPlayerManager().getPlayers())
                {
                    p.getShip().upgradeSpeed(20);
                }

            }
        });

        final ImageButton firePowerButton = newImageButton("Menu_damageicon", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0,1));
        firePowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                firePowerButton.setChecked(false);
                //set firepower here
                for(Player p : Managers.getPlayerManager().getPlayers())
                {
                    p.getShip().upgradeFireRate(0.05f);
                }
            }
        });

        final ImageButton healButton = newImageButton("Menu_healthicon", (width/2)-(width/2)/2,height/2+100,150,50, new MenuGrid(0,2));
        healButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                healButton.setChecked(false);
                //set heal here
                for(Player p : Managers.getPlayerManager().getPlayers())
                {
                    p.getShip().upgradeMaxHp(5);
                }
            }
        });

        final ImageButton nextLvlButton = newImageButton("Menu_nextlevelicon", (width/2)-(width/2)/2,height/2+200,150,50, new MenuGrid(0,3));
        nextLvlButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextLvlButton.setChecked(false);
                //set next level here
                Managers.getLevelManager().addLevel();
                Managers.getLevelManager().setCurrentwave(1);

                Managers.getGameManager().getGame().setGameState(GAMESTATE.GAMEPLAYING);
                MenuScreen upgradeMenu = Managers.getScreenManager().getScreen("game");
                Managers.getScreenManager().setScreen(upgradeMenu);
            }
        });
        final ImageButton QuitButton = newImageButton("Menu_speedicon", (width/2)-(width/2)/2,height/2+250,150,50, new MenuGrid(0,4));
        QuitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                QuitButton.setChecked(false);

                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });
    }
    
    public void render (float deltaTime) {
        super.render(deltaTime);
    }

    @Override
    public void setActive() {
        if(active)
            return;
        active = true;
    }

    @Override
    public void setInActive() {
        active = false;
    }
}
