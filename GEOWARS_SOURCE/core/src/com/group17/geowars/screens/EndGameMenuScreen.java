package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michiel on 4/12/2016.
 */
public class EndGameMenuScreen extends MenuScreen implements iHasStage, iSetActive {

    private BitmapFont text;
    private Batch batch;
    private int width = GeoWars.WIDTH;
    private int height = GeoWars.HEIGHT;

    public EndGameMenuScreen() {
        super();
        create();
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        final ImageButton replayButton = newImageButton("Menu_replayicon", width / 10, height / 2 + height / 8, 150, 50, new MenuGrid(0, 0));
        replayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                replayButton.setChecked(false);

                Managers.getGameManager().resetGame();
                Managers.getGameManager().newArcadeSoloGame();
            }
        });

        final ImageButton mainMenuButton = newImageButton("Menu_quittomainicon", width / 10, height / 2, 150, 50, new MenuGrid(0, 1));
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainMenuButton.setChecked(false);
                Managers.getGameManager().resetGame();
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
            }
        });

        final ImageButton highscoreButton = newImageButton("Menu_leaderboardicon", width / 10, height / 2 - height / 8, 150, 50, new MenuGrid(0, 2));
        highscoreButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                highscoreButton.setChecked(false);

                MenuScreen nextMenu = Managers.getScreenManager().getScreen("highscoremenu");
                Managers.getScreenManager().setScreen(nextMenu);

            }
        });

        text = new BitmapFont();
        text.setColor(Color.WHITE);
    }

    public void showText() {
        text.draw(batch, "Score:", width / 10, height / 2 + height / 3);

        int count = 0;
        for (Account a : Managers.getAccountManager().getAccounts()) {
            text.draw(batch, a.name + ": " + a.getPlayer().getShip().getScore(), width / 10, height / 2 + height / 3 + ++count * -20);
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
