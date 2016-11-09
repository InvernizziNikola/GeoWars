package com.group17.geowars.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.Ship;
import com.group17.geowars.managers.BulletManager;
import com.group17.geowars.managers.GameWorld;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameScreen extends ScreenAdapter
{

    private GameWorld world;


    public GameScreen(Batch batch)
    {
        world = new GameWorld(batch);
        world.ships.add(new Ship(new Vector2(50,50), "Test"));


        world.geoms.add(new Geom(1, new Vector2(300,50)));
        world.geoms.add(new Geom(1, new Vector2(100,300)));
        world.geoms.add(new Geom(1, new Vector2(300,300)));
    }

    public void render()
    {
        world.update();
        world.render();
    }
}
