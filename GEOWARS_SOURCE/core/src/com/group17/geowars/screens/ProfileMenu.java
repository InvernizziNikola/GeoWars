package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;


/**
 * Created by michield on 10/11/2016.
 */
public class ProfileMenu extends MenuScreen implements hasStage {
    private BitmapFont text;
    private Batch batch;
    public ProfileMenu()
    {
        super();
        create();
    }

    public void create(){
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        final TextButton clanButton = newButton("CLANS",50,150,150,50, new MenuGrid(0, 0));
        clanButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clanButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("clanmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton backButton = newButton("BACK",640,50,150,50, new MenuGrid(1, 1));
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                System.out.println("CLICKED: " + actor);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

        final TextButton upgradeButton = newButton("CHANGE/UPGRADE",550,150,150,50,new MenuGrid(1,0));
        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("upgrademenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });
        text = new BitmapFont();
        text.setColor(Color.WHITE);




    }

    public void showText()
    {
        text.draw(batch, "GEOMETRYWARS",Gdx.graphics.getWidth()/2-70,550);
        //TODO values need to be inserted
        text.draw(batch, "USERNAME",Gdx.graphics.getWidth()/2-50,500);
        text.draw(batch, "HIGHSCORE ARCADE: ",50,400);
        text.draw(batch,"CAMPAIGN COMPLETION: %",50,375);
        text.draw(batch, "GAMES PLAYED: ",50,350);
        text.draw(batch, "HOURS PLAYED: ",50,325);
        text.draw(batch,"LEVEL: ",50,300);
        text.draw(batch, "CURRENT SHIP & DRONE",Gdx.graphics.getWidth()/2+100,425);
        text.draw(batch,"CURRENT SHIP",Gdx.graphics.getWidth()/2+200,375);
        text.draw(batch,"CURRENT DRONE", Gdx.graphics.getWidth()/2+200,300);
    }

    @Override
    public void render(float delta) {

        batch.begin();
        showText();
        batch.end();

        super.render(delta);
    }

}
