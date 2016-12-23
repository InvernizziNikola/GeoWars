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

        final ImageButton speedButton = newImageButton("Speed", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0,0));
        speedButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                speedButton.setChecked(false);
                //set speed here

            }
        });

        final ImageButton firePowerButton = newImageButton("FirePower", (width/2)-(width/2)/2,height/2+50,150,50, new MenuGrid(0,1));
        firePowerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                firePowerButton.setChecked(false);
                //set firepower here

            }
        });

        final ImageButton healButton = newImageButton("HealButton", (width/2)-(width/2)/2,height/2+100,150,50, new MenuGrid(0,2));
        healButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                healButton.setChecked(false);
                //set heal here

            }
        });

        final ImageButton nextLvlButton = newImageButton("NextLevel", (width/2)-(width/2)/2,height/2+200,150,50, new MenuGrid(0,3));
        nextLvlButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                nextLvlButton.setChecked(false);
                //set next level here

            }
        });
        final ImageButton QuitButton = newImageButton("Speed", (width/2)-(width/2)/2,height/2+250,150,50, new MenuGrid(0,4));
        QuitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                QuitButton.setChecked(false);
                Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
                Dialog dialog = new Dialog("Warning", skin, "dialog") {
                    public void result(Boolean quit) {
                        if (quit) {
                            Gdx.app.exit();
                            System.out.println("result " + quit);
                        }
                    }
                };
                dialog.text("Are you sure you want to quit?");
                dialog.button("Yes", true); //sends "true" as the result
                dialog.button("No", false);  //sends "false" as the result
                dialog.show(stage);


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
