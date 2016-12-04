package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

/**
 * Created by michiel on 4/12/2016.
 */
public class UpgradeMenu extends MenuScreen{
    private BitmapFont text;
    private Batch batch;

    public UpgradeMenu()
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

        final TextButton tankButton = newButton("TANK", 5,490,130,40, new MenuGrid(0,0));
        tankButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                tankButton.setChecked(false);
                tankText();
            }
        });

        final TextButton assaultButton = newButton("ASSAULT",135,490,130,40,new MenuGrid(1,0));
        /*assaultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                assaultButton.setChecked(false);
                assaultText();
            }
        });*/
        final TextButton jetButton = newButton("JET", 265,490,133,40,new MenuGrid(2,0));

        final TextButton supportButton = newButton("SUPPORT",402,490,133,40, new MenuGrid(3,0));

        final TextButton attackButton = newButton("ATTACK",535,490,130,40,new MenuGrid(4,0));

        final TextButton defendButton = newButton("DEFEND", 665,490,130,40, new MenuGrid(5,0));

        final TextButton shipSkillTreeButton = newButton("SKILL TREE",5,240,196,40, new MenuGrid(0,1));

        final TextButton shipStatsButton = newButton("STATS",201,240,196,40,new MenuGrid(1,1));

        final TextButton droneSkillTreeButton = newButton("SKILL TREE", 402,240,196,40,new MenuGrid(2,1));

        final TextButton droneStatsButton = newButton("STATS", 598,240,196,40, new MenuGrid(3,1));

        final TextButton confirmButton = newButton("CONFIRM",5,20,Gdx.graphics.getWidth()-10,40,new MenuGrid(0,2));
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                confirmButton.setChecked(false);
                MenuScreen nextMenu = Managers.getMenuManager().getScreen("profilemenu");
                Managers.getMenuManager().setScreen(nextMenu);
            }
        });
    }

    @SuppressWarnings("Duplicates")
    public void tankText()
    {
        int width = Gdx.graphics.getWidth()/2;
        showText(width);
        text.draw(batch,"-TANK",width-(width/2)+50,420);
        text.draw(batch,"-HIGH HP",width-(width/2)+50,395);
        text.draw(batch,"-LOW ATK",width-(width/2)+50,370);
        text.draw(batch,"HP = 30",50,315);
        text.draw(batch,"ATK = 2", width-(width/2)+50,315);
    }

    @SuppressWarnings("Duplicates")
    public void assaultText()
    {
        int width = Gdx.graphics.getWidth()/2;
        showText(width);
        text.draw(batch,"-ASSAULT",width-(width/2)+50,420);
        text.draw(batch,"-HIGH ATTACK",width-(width/2)+50,395);
        text.draw(batch,"-MED HP",width-(width/2)+50,370);
        text.draw(batch,"HP = 20",50,315);
        text.draw(batch,"ATK = 10", width-(width/2)+50,315);
    }

    public void showText(int width)
    {

        text.draw(batch,"SHIP", (width)/2-15,570);
        text.draw(batch, "DRONE",width+(width/2-30),570);
    }

    @Override
    public void show()
    {

    }


    @SuppressWarnings("Duplicates")
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        tankText();
        batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
