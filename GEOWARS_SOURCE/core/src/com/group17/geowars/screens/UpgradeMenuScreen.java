package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ShopThread;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Profile;
import com.group17.geowars.utils.DRONETYPES;
import com.group17.geowars.utils.ExtendeImageButton;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.utils.SHIPTYPES;

/**
 * Created by michiel on 4/12/2016.
 */
public class UpgradeMenuScreen extends MenuScreen implements iHasStage, iSetActive {
    protected Batch batch;
    protected int showShip = 0;
    protected int showDrone = 0;
    protected int showShipStats = 0;
    protected int showDroneStats = 0;
    protected ShopThread ShopThread;
    protected SHIPTYPES shipData;
    protected Profile profile;
    protected DRONETYPES droneData;
    protected boolean loading = false;
    protected Color color;
    protected BitmapFont bigFont;

    public UpgradeMenuScreen() {
        super();
        create();
        profile = new Profile(SHIPTYPES.DESTROYER, DRONETYPES.DEFEND);
        createButtons();
        color = Color.WHITE;
        bigFont = Managers.getAssetManager().getGameFont(color, 15);
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        shipData=SHIPTYPES.ASSAULT;
        droneData=DRONETYPES.SUPPORT;
    }

    public void createButtons() {
        final ExtendeImageButton assaultButton = newImageButton("Menu_assaulticon", 0, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(0, 0));
        assaultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(assaultButton);

                shipData =SHIPTYPES.ASSAULT;
                showShip = 0;
            }
        });

        final ExtendeImageButton destroyerButton = newImageButton("Menu_destroyericon", (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(1, 0));
        destroyerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(destroyerButton);
                shipData =SHIPTYPES.DESTROYER;
                showShip = 1;
            }
        });

        final ExtendeImageButton tankButton = newImageButton("Menu_tankicon", (GeoWars.WIDTH / 2) / 3 + (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(2, 0));
        tankButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(tankButton);
                shipData =SHIPTYPES.TANK;
                showShip = 2;
            }
        });

        final ExtendeImageButton supportButton = newImageButton("Menu_supporticon", (GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3) - ((GeoWars.WIDTH / 2) / 3) - (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(3, 0));
        supportButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(supportButton);
                droneData =DRONETYPES.SUPPORT;
                showDrone = 0;
            }
        });

        final ExtendeImageButton attackButton = newImageButton("Menu_attackicon", (GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3 - 5) - (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(4, 0));
        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(attackButton);
                droneData =DRONETYPES.ATTACK;
                showDrone = 1;
            }
        });

        final ExtendeImageButton defendButton = newImageButton("Menu_defendicon", GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3 - 5, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3 + 5, 75, new MenuGrid(5, 0));
        defendButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(defendButton);
                droneData =DRONETYPES.DEFEND;
                showDrone = 2;
            }
        });

        final ExtendeImageButton shipSkillTreeButton = newImageButton("Menu_skilltreeicon", 0, GeoWars.HEIGHT / 2, GeoWars.WIDTH / 2, 75, new MenuGrid(0, 1));
        shipSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(shipSkillTreeButton);
                showShipStats = 0;
            }
        });

        final ExtendeImageButton droneSkillTreeButton = newImageButton("Menu_skilltreeicon", GeoWars.WIDTH / 2, GeoWars.HEIGHT / 2, GeoWars.WIDTH / 2, 75, new MenuGrid(1, 1));
        droneSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(droneSkillTreeButton);
                showDroneStats = 0;
            }
        });

        final ExtendeImageButton confirmButton = newImageButton("Menu_confirmicon", 5, 20, Gdx.graphics.getWidth() - 10, 75, new MenuGrid(0, 2));
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                confirmButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
                Managers.getAccountManager().getAccounts().get(0).profile=new Profile(shipData,droneData);
            }
        });
    }

    public void createShipSkillTreeButtons() {
        final ExtendeImageButton extraSpread = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 10, 35, 35, new MenuGrid(0,3));
        extraSpread.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy extra spread");
            }
        });

        final ExtendeImageButton bigBullet = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 5 - 18, 35, 35, new MenuGrid(0,3));
        bigBullet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big bullet");
            }
        });

        final ExtendeImageButton mirrorShot = newImageButton("skillButton", GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 4 - 18, 35, 35, new MenuGrid(0,3));
        mirrorShot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy mirror shot");
            }
        });

        final ExtendeImageButton glassCannon = newImageButton("skillButton", GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 3 - 18, 35, 35, new MenuGrid(0,3));
        glassCannon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy glass cannon");
            }
        });

        final ExtendeImageButton smallUnit = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20 - 18, 35, 35, new MenuGrid(0,3));
        smallUnit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy small unit");
            }
        });

    }

    public void createDroneSkillTreeButtons() {
        final ExtendeImageButton extraSpread = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 10, 35, 35, new MenuGrid(0,3));
        extraSpread.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy extra spread");
            }
        });

        final ExtendeImageButton bigBullet = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 5 - 18, 35, 35, new MenuGrid(0,3));
        bigBullet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big bullet");
            }
        });

        final ExtendeImageButton mirrorShot = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 4 - 18, 35, 35, new MenuGrid(0,3));
        mirrorShot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy mirror shot");
            }
        });

        final ExtendeImageButton glassCannon = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 3 - 18, 35, 35, new MenuGrid(0,3));
        glassCannon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy glass cannon");
            }
        });

        final ExtendeImageButton bigUnit = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20 - 18, 35, 35, new MenuGrid(0,3));
        bigUnit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big unit");
            }
        });
    }

    public void selectButton(ExtendeImageButton txtB) {
        txtB.setChecked(false);
        deSelectButtons();
        txtB.setHoverStyle();
    }

    public void tankText() {
        bigFont.draw(batch, "-TANK", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-HIGH HP", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        bigFont.draw(batch, "-LOW ATK", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "HP = 30", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "ATK = 2", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void assaultText() {
        bigFont.draw(batch, "-ASSAULT", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-HIGH ATTACK", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        bigFont.draw(batch, "-MED HP", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "HP = 20", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "ATK = 10", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void destroyerText() {
        bigFont.draw(batch, "-DESTROYER", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-HIGH ATK", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        bigFont.draw(batch, "-LOW DEF", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "HP = 10", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "ATK = 10", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void supportText() {
        bigFont.draw(batch, "-SLOW DOWN ENEMIES", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "ATK= 0", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void attackText() {
        bigFont.draw(batch, "-SHOOTS UNITS", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "ATK= 1", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void defendText() {
        bigFont.draw(batch, "-DEFENDS SHIP", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        bigFont.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        bigFont.draw(batch, "ATK= 0", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        bigFont.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void shipSkillTreeText() {
        bigFont.draw(batch, "SKILL TREE", 50, GeoWars.HEIGHT / 2 - 50);
        bigFont.draw(batch, "SKILL POINTS 2 of 2", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 - 50);
    }

    public void droneSkillTreeText() {
        bigFont.draw(batch, "SKILL TREE", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 - 50);
        bigFont.draw(batch, "SKILL POINTS 2 of 2", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 - 50);
    }

    public void showText() {
        bigFont.draw(batch, "SHIP", (GeoWars.WIDTH / 2) / 2 - 15, GeoWars.HEIGHT - 90);
        bigFont.draw(batch, "DRONE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 2 / 2 - 30), GeoWars.HEIGHT - 90);
        changeText();
    }

    public void changeText() {
        switch (showShip) {
            case 0: {
                assaultText();
                break;
            }
            case 1: {
                destroyerText();
                break;
            }
            case 2: {
                tankText();
                break;
            }
        }

        switch (showDrone) {
            case 0: {
                supportText();
                break;
            }
            case 1: {
                attackText();
                break;
            }
            case 2: {
                defendText();
                break;
            }
        }
        switch (showShipStats) {
            case 0:
                shipSkillTreeText();
                break;
        }

        switch (showDroneStats) {
            case 0:
                droneSkillTreeText();
                break;
        }
    }

    public void changeImage() {
        switch (showShip) {
            case 0:
                final Sprite assault = newImage("Speler_2", 300, 300, GeoWars.WIDTH / 6, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
                break;
            case 1:
                final Sprite destroyer = newImage("Destroyer", 300, 300, GeoWars.WIDTH / 6, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
                break;
            case 2:
                final Sprite tank = newImage("TankShip", 300, 300, GeoWars.WIDTH / 6, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
                break;
        }
        switch (showDrone) {
            case 0:
                final Sprite support = newImage("supportdrone", 250, 250, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 8, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 8);
                break;
            case 1:
                final Sprite attack = newImage("attackdrone", 250, 250, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 8, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 8);
                break;
            case 2:
                final Sprite defense = newImage("defdrone", 250, 250, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 8, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 8);
                break;
        }
    }

    public void shipSkilltree() {
        createShipSkillTreeButtons();
        drawTreeLine(GeoWars.WIDTH / 4, GeoWars.HEIGHT / 9, GeoWars.WIDTH / 4, GeoWars.HEIGHT / 5);
        drawTreeLine(GeoWars.WIDTH / 4, GeoWars.HEIGHT / 5, GeoWars.WIDTH / 5, GeoWars.HEIGHT / 4);
        drawTreeLine(GeoWars.WIDTH / 5, GeoWars.HEIGHT / 4, GeoWars.WIDTH / 5, GeoWars.HEIGHT / 3);
        drawTreeLine(GeoWars.WIDTH / 5, GeoWars.HEIGHT / 3, GeoWars.WIDTH / 4, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20);
        drawTreeLine(GeoWars.WIDTH / 2, 95, GeoWars.WIDTH / 2, GeoWars.HEIGHT);
    }

    public void droneSkillTree() {
        if (showDroneStats == 0) {
            createDroneSkillTreeButtons();
            drawTreeLine(GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4, GeoWars.HEIGHT / 9, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4, GeoWars.HEIGHT / 5);
            drawTreeLine(GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4, GeoWars.HEIGHT / 5, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5, GeoWars.HEIGHT / 4);
            drawTreeLine(GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5, GeoWars.HEIGHT / 4, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5, GeoWars.HEIGHT / 3);
            drawTreeLine(GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5, GeoWars.HEIGHT / 3, GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20);
        }
    }

    public void showSkillTrees()
    {
        switch (showShipStats)
        {
            case 0:
                shipSkilltree();
                break;
            case 1:
                shipSkilltree();
                break;
            case 2:
                shipSkilltree();
                break;
        }

        switch (showDroneStats)
        {
            case 0:
                droneSkillTree();
                break;
            case 1:
                droneSkillTree();
                break;
            case 2:
                droneSkillTree();
                break;
        }
    }

    @Override
    public void show() {

    }


    @Override
    public void setActive() {
        if (active)
            return;
        ;
        active = true;
        getAllData();
    }

    @Override
    public void setInActive() {
        active = false;
    }

    public void getAllData() {
        if (loading)
            return;

        loading = true;
        ShopThread = new ShopThread();
        ShopThread.start();
    }

    @Override
    public void render(float delta) {

        super.render(delta);
        batch.begin();

            showText();

        batch.end();
            changeImage();
            showSkillTrees();

    }
}
