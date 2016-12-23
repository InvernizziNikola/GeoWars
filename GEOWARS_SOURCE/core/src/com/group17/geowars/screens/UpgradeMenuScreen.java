package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.Threads.ShopThread;
import com.group17.geowars.gameobjects.playerObjects.DefenceDrone;
import com.group17.geowars.gameobjects.playerObjects.DestroyerShip;
import com.group17.geowars.gameobjects.playerObjects.Ship;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Profile;
import com.group17.geowars.utils.DRONETYPES;
import com.group17.geowars.utils.MenuGrid;
import com.group17.geowars.utils.SHIPTYPES;

import java.util.ArrayList;

/**
 * Created by michiel on 4/12/2016.
 */
public class UpgradeMenuScreen extends MenuScreen implements iHasStage, iSetActive {
    private BitmapFont text;
    private Batch batch;

    private int showShip = 0;
    private int showDrone = 0;
    private int showShipStats = 0;
    private int showDroneStats = 0;
    private ShopThread ShopThread;
    private SHIPTYPES ShipData;
    private Profile profile;
    private DRONETYPES DroneData;
    private boolean loading = false;

    public UpgradeMenuScreen() {
        super();
        create();
        profile =new Profile(SHIPTYPES.DESTROYER, DRONETYPES.DEFEND);
    }

    public void create() {
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        text = new BitmapFont();
        text.setColor(Color.WHITE);
    }

    public void createButtons() {
        final ImageButton assaultButton = newImageButton("ASSAULT", 0, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(0, 0));
        assaultButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(assaultButton);

                ShipData=SHIPTYPES.ASSAULT;
                showShip = 0;
            }
        });

        final ImageButton destroyerButton = newImageButton("DESTROYER", (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(1, 0));
        destroyerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(destroyerButton);
                ShipData=SHIPTYPES.DESTROYER;
                showShip = 1;
            }
        });

        final ImageButton tankButton = newImageButton("TANK", (GeoWars.WIDTH / 2) / 3 + (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(2, 0));
        tankButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(tankButton);
                ShipData=SHIPTYPES.TANK;
                showShip = 2;
            }
        });

        final ImageButton supportButton = newImageButton("SUPPORT", (GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3) - ((GeoWars.WIDTH / 2) / 3) - (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(3, 0));
        supportButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(supportButton);
                DroneData=DRONETYPES.SUPPORT;
                showDrone = 0;
            }
        });

        final ImageButton attackButton = newImageButton("ATTACK", (GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3 - 5) - (GeoWars.WIDTH / 2) / 3, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3, 75, new MenuGrid(4, 0));
        attackButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(attackButton);
                DroneData=DRONETYPES.ATTACK;
                showDrone = 1;
            }
        });

        final ImageButton defendButton = newImageButton("DEFEND", GeoWars.WIDTH - (GeoWars.WIDTH / 2) / 3 - 5, GeoWars.HEIGHT - 75, (GeoWars.WIDTH / 2) / 3 + 5, 75, new MenuGrid(5, 0));
        defendButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(defendButton);
                DroneData=DRONETYPES.DEFEND;
                showDrone = 2;
            }
        });

        final ImageButton shipSkillTreeButton = newImageButton("SKILL TREE", 0, GeoWars.HEIGHT / 2, GeoWars.WIDTH / 2, 75, new MenuGrid(0, 1));
        shipSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(shipSkillTreeButton);
                showShipStats = 0;
            }
        });

        final ImageButton droneSkillTreeButton = newImageButton("SKILL TREE", GeoWars.WIDTH / 2, GeoWars.HEIGHT / 2, GeoWars.WIDTH / 2, 75, new MenuGrid(1, 1));
        droneSkillTreeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectButton(droneSkillTreeButton);
                showDroneStats = 0;
            }
        });

        final ImageButton confirmButton = newImageButton("CONFIRM", 5, 20, Gdx.graphics.getWidth() - 10, 75, new MenuGrid(0, 2));
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                confirmButton.setChecked(false);
                MenuScreen nextMenu = Managers.getScreenManager().getScreen("mainmenu");
                Managers.getScreenManager().setScreen(nextMenu);
                Managers.getAccountManager().getAccounts().get(0).profile=profile;
            }
        });
    }

    public void createShipSkillTreeButtons() {
        final ImageButton extraSpread = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 10, 35, 35, new MenuGrid(0,3));
        extraSpread.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy extra spread");
            }
        });

        final ImageButton bigBullet = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 5 - 18, 35, 35, new MenuGrid(0,3));
        bigBullet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big bullet");
            }
        });

        final ImageButton mirrorShot = newImageButton("skillButton", GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 4 - 18, 35, 35, new MenuGrid(0,3));
        mirrorShot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy mirror shot");
            }
        });

        final ImageButton glassCannon = newImageButton("skillButton", GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 3 - 18, 35, 35, new MenuGrid(0,3));
        glassCannon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy glass cannon");
            }
        });

        final ImageButton smallUnit = newImageButton("skillButton", GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20 - 18, 35, 35, new MenuGrid(0,3));
        smallUnit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy small unit");
            }
        });

    }

    public void createDroneSkillTreeButtons() {
        final ImageButton extraSpread = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 10, 35, 35, new MenuGrid(0,3));
        extraSpread.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy extra spread");
            }
        });

        final ImageButton bigBullet = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 5 - 18, 35, 35, new MenuGrid(0,3));
        bigBullet.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big bullet");
            }
        });

        final ImageButton mirrorShot = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 4 - 18, 35, 35, new MenuGrid(0,3));
        mirrorShot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy mirror shot");
            }
        });

        final ImageButton glassCannon = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 5 - 18, GeoWars.HEIGHT / 3 - 18, 35, 35, new MenuGrid(0,3));
        glassCannon.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy glass cannon");
            }
        });

        final ImageButton bigUnit = newImageButton("skillButton", GeoWars.WIDTH / 2 + GeoWars.WIDTH / 4 - 18, GeoWars.HEIGHT / 3 + GeoWars.HEIGHT / 20 - 18, 35, 35, new MenuGrid(0,3));
        bigUnit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("buy big unit");
            }
        });
    }

    public void selectButton(ImageButton txtB) {
        txtB.setChecked(false);
        deSelectButtons();
        txtB.setStyle(Managers.getScreenManager().getSelectedStyle());
    }

    public void tankText() {
        text.draw(batch, "-TANK", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-HIGH HP", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        text.draw(batch, "-LOW ATK", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "HP = 30", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "ATK = 2", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }


    public void assaultText() {
        text.draw(batch, "-ASSAULT", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-HIGH ATTACK", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        text.draw(batch, "-MED HP", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "HP = 20", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "ATK = 10", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void destroyerText() {
        text.draw(batch, "-DESTROYER", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-HIGH ATK", 50, (float) (GeoWars.HEIGHT - GeoWars.HEIGHT / 4.75));
        text.draw(batch, "-LOW DEF", 50, GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "HP = 10", 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "ATK = 10", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void supportText() {
        text.draw(batch, "-SLOW DOWN ENEMIES", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "ATK= 0", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void attackText() {
        text.draw(batch, "-SHOOTS UNITS", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "ATK= 1", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void defendText() {
        text.draw(batch, "-DEFENDS SHIP", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 6);
        text.draw(batch, "-CAN'T DIE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT - GeoWars.HEIGHT / 4);
        text.draw(batch, "ATK= 0", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
        text.draw(batch, "HP = INFINITE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 + GeoWars.HEIGHT / 10);
    }

    public void shipSkillTreeText() {
        text.draw(batch, "SKILL TREE", 50, GeoWars.HEIGHT / 2 - 50);
        text.draw(batch, "SKILL POINTS 2/2", GeoWars.WIDTH / 3, GeoWars.HEIGHT / 2 - 50);
    }

    public void droneSkillTreeText() {
        text.draw(batch, "SKILL TREE", GeoWars.WIDTH / 2 + 50, GeoWars.HEIGHT / 2 - 50);
        text.draw(batch, "SKILL POINTS 2/2", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 3), GeoWars.HEIGHT / 2 - 50);
    }

    public void showText() {
        text.draw(batch, "SHIP", (GeoWars.WIDTH / 2) / 2 - 15, GeoWars.HEIGHT - 90);
        text.draw(batch, "DRONE", GeoWars.WIDTH / 2 + (GeoWars.WIDTH / 2 / 2 - 30), GeoWars.HEIGHT - 90);
        changeText();
        createButtons();
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
        drawTreeLine(GeoWars.WIDTH / 2, 75, GeoWars.WIDTH / 2, GeoWars.HEIGHT);
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

    public void showLoading() {
        text.draw(batch, "Loading...", 350, 380);
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
        if (ShopThread != null && ShopThread.finished()) {
          //  ShipData = ShopThread.getShipData();
           // DroneData = ShopThread.getDronesData();
            ShopThread = null;

            loading = false;


            System.out.println(ShipData);
        }
        if (ShopThread != null && !ShopThread.finished()) {
            showLoading();
        }
        if (ShopThread == null) {
            showText();
        }
        batch.end();
        if (ShopThread == null) {
            changeImage();
            showSkillTrees();
        }
    }
}
