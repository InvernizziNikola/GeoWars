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
public class UpgradeMenu extends MenuScreen implements hasStage{
    private BitmapFont text;
    private Batch batch;

    private int showShip = 0;
    private int showDrone = 0;
    private int showShipStats = 0;
    private int showDroneStats = 0;
    private int width = Gdx.graphics.getWidth()/2;

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
                showShip = 0;
            }
        });

        final TextButton assaultButton = newButton("ASSAULT",135,490,130,40,new MenuGrid(1,0));
        assaultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                assaultButton.setChecked(false);
                showShip = 1;
            }
        });

        final TextButton jetButton = newButton("JET", 265,490,133,40,new MenuGrid(2,0));
        jetButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                jetButton.setChecked(false);
                showShip = 2;
            }
        });

        final TextButton supportButton = newButton("SUPPORT",402,490,133,40, new MenuGrid(3,0));
        supportButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                supportButton.setChecked(false);
                showDrone = 0;
            }
        });

        final TextButton attackButton = newButton("ATTACK",535,490,130,40,new MenuGrid(4,0));
        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                attackButton.setChecked(false);
                showDrone = 1;
            }
        });

        final TextButton defendButton = newButton("DEFEND", 665,490,130,40, new MenuGrid(5,0));
        defendButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                defendButton.setChecked(false);
                showDrone = 2;
            }
        });

        final TextButton shipSkillTreeButton = newButton("SKILL TREE",5,240,196,40, new MenuGrid(0,1));
        shipSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shipSkillTreeButton.setChecked(false);
                showShipStats = 0;
            }
        });

        final TextButton shipStatsButton = newButton("STATS",201,240,196,40,new MenuGrid(1,1));
        shipStatsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                shipStatsButton.setChecked(false);
                showShipStats = 1;
            }
        });

        final TextButton droneSkillTreeButton = newButton("SKILL TREE", 402,240,196,40,new MenuGrid(2,1));
        droneSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                droneSkillTreeButton.setChecked(false);
                showDroneStats = 0;
            }
        });

        final TextButton droneStatsButton = newButton("STATS", 598,240,196,40, new MenuGrid(3,1));
        droneStatsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                droneStatsButton.setChecked(false);
                showDroneStats = 1;
            }
        });

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

    public void tankText(int width)
    {
        text.draw(batch,"-TANK",width-(width/2)+50,420);
        text.draw(batch,"-HIGH HP",width-(width/2)+50,395);
        text.draw(batch,"-LOW ATK",width-(width/2)+50,370);
        text.draw(batch,"HP = 30",50,315);
        text.draw(batch,"ATK = 2", width-(width/2)+50,315);
    }


    public void assaultText(int width)
    {
        text.draw(batch,"-ASSAULT",width-(width/2)+50,420);
        text.draw(batch,"-HIGH ATTACK",width-(width/2)+50,395);
        text.draw(batch,"-MED HP",width-(width/2)+50,370);
        text.draw(batch,"HP = 20",50,315);
        text.draw(batch,"ATK = 10", width-(width/2)+50,315);
    }

    public void jetText(int width)
    {
        text.draw(batch,"-JET",width-(width/2)+50,420);
        text.draw(batch,"-HIGH ATK",width-(width/2)+50,395);
        text.draw(batch,"-LOW DEF",width-(width/2)+50,370);
        text.draw(batch,"HP = 10",50,315);
        text.draw(batch,"ATK = 10", width-(width/2)+50,315);
    }

    public void supportText(int width)
    {
        text.draw(batch,"-SLOW DOWN ENEMIES",width+(width/2),420);
        text.draw(batch,"-CAN'T DIE",width+(width/2),395);
        text.draw(batch,"ATK= 0", width+50,315);
        text.draw(batch,"HP = INFINITE", width+(width/2),315);
    }

    public void attackText(int width)
    {
        text.draw(batch,"-SHOOTS UNITS",width+(width/2),420);
        text.draw(batch,"-CAN'T DIE",width+(width/2),395);
        text.draw(batch,"ATK= 1", width+50,315);
        text.draw(batch,"HP = INFINITE", width+(width/2),315);
    }

    public void defendText(int width)
    {
        text.draw(batch,"-DEFENDS SHIP",width+(width/2),420);
        text.draw(batch,"-CAN'T DIE",width+(width/2),395);
        text.draw(batch,"ATK= 0", width+50,315);
        text.draw(batch,"HP = INFINITE", width+(width/2),315);
    }

    public void shipSkilltreeText(int width)
    {
        text.draw(batch,"SKILL TREE",15,220);
        text.draw(batch,"SKILL POINTS 2/2",width-(width/2)+40,220);
    }

    public void shipStatsText(int width) //width meegegeven voor geval dat het nodig zou zijn
    {
        text.draw(batch,"-KILLS: ",15,200);
        text.draw(batch,"-HOURS PLAYED: ",15,160);
    }

    public void droneSkillTreeText(int width)
    {
        text.draw(batch,"SKILL TREE",width+40,220);
        text.draw(batch,"SKILL POINTS 2/2",width+(width/2)+40,220);
    }

    public void droneStatsText(int width)
    {
        text.draw(batch,"-KILLS: ",width+25,200);
        text.draw(batch,"-HOURS PLAYED: ",width+25,160);
    }

    public void showText(int width)
    {
        text.draw(batch,"SHIP", (width)/2-15,570);
        text.draw(batch, "DRONE",width+(width/2-30),570);
    }

    public void changeText()
    {
        switch (showShip)
        {
            case 0:
            {
                tankText(width);
                break;
            }
            case 1:
            {
                assaultText(width);
                break;
            }
            case 2:
            {
                jetText(width);
                break;
            }
        }

        switch (showDrone)
        {
            case 0:
            {
                supportText(width);
                break;
            }
            case 1:
            {
                attackText(width);
                break;
            }
            case 2:
            {
                defendText(width);
                break;
            }
        }
        switch (showShipStats)
        {
            case 0:
                shipSkilltreeText(width);
                break;
            case 1:
                shipStatsText(width);
                break;
        }

        switch (showDroneStats)
        {
            case 0:
                droneSkillTreeText(width);
                break;
            case 1:
                droneStatsText(width);
                break;
        }
    }
    @Override
    public void show()
    {

    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        showText(width);
        changeText();
        batch.end();
    }
}
