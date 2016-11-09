package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 9/11/2016.
 */
public class LevelManager implements GOInterface {
    private static LevelManager _instance;

    public static LevelManager GetInstance()
    {
        if(_instance == null)
            _instance = new LevelManager();

        return _instance;
    }


    private List<String> enemies;
    private List<Vector2> spawnLocations;
    private int NrOfEnemys;


    private LevelManager () {
        enemies = new LinkedList<String>();
        spawnLocations= new ArrayList<Vector2>();
        spawnLocations.add(new Vector2(400,400));
        spawnLocations.add(new Vector2(111,222));
        spawnLocations.add(new Vector2(400,333));
        spawnLocations.add(new Vector2(100,500));
        spawnLocations.add(new Vector2(500,400));
        spawnLocations.add(new Vector2(300,600));
        NrOfEnemys =5;

        enemies.add("tank");
        enemies.add("something else");
        enemies.add("fighter");
    }

    public void addEnemy(String enemy)
    {
        enemies.add(enemy);
    }
    public void addSpawnLocations(Vector2 location)
    {
        spawnLocations.add(location);
    }

    public List<String> getEnemies() {
        return enemies;
    }
    public void clearSpawn() {
        enemies.clear();
    }

    public List<Vector2> getSpawnLocations() {
        return spawnLocations;
    }


    @Override
    public void render(Batch batch) {

    }

    @Override
    public void update() {

    }

    public int getNrOfEnemys() {
        return NrOfEnemys;
    }
}
