package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private List<String> enemies;
    private List<Vector2> spawnLocations;
    private List<Integer> waveList;
    private Integer currentwave;
    private BitmapFont font;


    public LevelManager () {
        enemies = new LinkedList<String>();
        spawnLocations= new ArrayList<Vector2>();
        waveList = new ArrayList<Integer>();
        currentwave=1;
    }

    

    public void init()
    {
        font = new BitmapFont();
        spawnLocations.add(new Vector2(700,-20));
        spawnLocations.add(new Vector2(-100,500));
        spawnLocations.add(new Vector2(940,-100));
        spawnLocations.add(new Vector2(850,700));
        spawnLocations.add(new Vector2(900,740));
        spawnLocations.add(new Vector2(-70,400));
        waveList.add(20);
        waveList.add(200);
        waveList.add(500);
        enemies.add("tank");
        enemies.add("something_else");
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
    public List<Integer> getWaveList ()
    {
        return waveList;
    }

    public Integer getCurrentwave() {
        return currentwave;
    }

    public void setCurrentwave(Integer currentwave) {
        this.currentwave = currentwave;
    }

    @Override
    public void render(Batch batch) {
        font.draw(batch, "Wave"+Managers.getLevelManager().getCurrentwave(), 375, 590);

    }

    @Override
    public void update() {

    }

}
