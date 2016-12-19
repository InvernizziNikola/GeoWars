package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
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
    private Texture texture;
    private Sprite sprite;



    public LevelManager () {
        enemies = new LinkedList<String>();
        spawnLocations= new ArrayList<Vector2>();
        waveList = new ArrayList<Integer>();
        currentwave=1;
    }

    public void reset()
    {
        currentwave = 0;
    }

    public void init()
    {
        font = new BitmapFont();
        spawnLocations.add(new Vector2(GeoWars.WIDTH-100,GeoWars.HEIGHT-100));
        spawnLocations.add(new Vector2(GeoWars.WIDTH-100,GeoWars.HEIGHT-200));
        spawnLocations.add(new Vector2(GeoWars.WIDTH-200,GeoWars.HEIGHT-100));
        spawnLocations.add(new Vector2(GeoWars.WIDTH-10,GeoWars.HEIGHT-50));
        spawnLocations.add(new Vector2(GeoWars.WIDTH-10,GeoWars.HEIGHT-150));

        waveList.add(10);
        enemies.add("tank");
        enemies.add("something_else");
        enemies.add("fighter");

        //backgound stuff
        texture = Managers.getAssetManager().getTexture("test_background_geoWars");
        sprite = new Sprite(texture, GeoWars.WIDTH, GeoWars.HEIGHT);

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

        sprite.draw(batch);
        font.draw(batch, "Wave "+Managers.getLevelManager().getCurrentwave(), GeoWars.WIDTH/2, GeoWars.HEIGHT-50);
    }

    @Override
    public void update() {

    }

}
