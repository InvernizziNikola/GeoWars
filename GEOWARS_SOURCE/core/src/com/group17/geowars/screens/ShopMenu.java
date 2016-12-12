package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michield on 12/12/2016.
 */
public class ShopMenu extends MenuScreen implements hasStage{

    private BitmapFont text;
    private Batch batch;

    public ShopMenu()
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

        final TextButton backButton = newButton("BACK",640,50,150,50, new MenuGrid(0,2));
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                backButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("mainmenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });

    }

    public void showText()
    {
        int width = Gdx.graphics.getWidth()/2;
        text.draw(batch,"SHOP",width-30,550);
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
