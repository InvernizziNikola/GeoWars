package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.WarpGate;
import com.group17.geowars.utils.ENEMYTYPE;
import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by kevin on 9/11/2016.
 */

public class LevelManager {

    private List<WarpGate> warpGates;
    private int currentwave;
    private int currentLevel;
    private BitmapFont font;
    private Texture textureBG;
    private Sprite spriteBG;


    public LevelManager () {

        warpGates = new ArrayList<WarpGate>();
        currentwave = 1;
        currentLevel = 0;
    }
    public void init()
    {
        font = new BitmapFont();

        //backgound stuff
        textureBG = Managers.getAssetManager().getTexture("test_background_geoWars");
        spriteBG = new Sprite(textureBG, GeoWars.WIDTH, GeoWars.HEIGHT);
    }

    public void newWave()
    {
        int points = currentLevel * 30 + currentwave * 5;
        int warpGateCount = MathUtils.ceil(points/35.0f);

        Random rand = new Random();
        List<EnemyProfile> possibleEnemies = Managers.getEnemyManager().getProfiles();

        List<EnemyProfile> enemiesToWarp = new ArrayList<EnemyProfile>();

        while(points > 0)
        {
            EnemyProfile ep = possibleEnemies.get(rand.nextInt(possibleEnemies.size()));
            if(ep.type != ENEMYTYPE.BOSS)
            {
                points -= ep.difficultyGrade;
                enemiesToWarp.add(ep);
            }
        }

        warpGates.add(new WarpGate(new Vector2(200,200), enemiesToWarp));
    }

    public void render(Batch batch) {

        spriteBG.draw(batch);
        font.draw(batch, "Wave " + Managers.getLevelManager().getCurrentwave(), GeoWars.WIDTH/2, GeoWars.HEIGHT-50);

        for(WarpGate wg : warpGates)
            wg.render(batch);
    }

    public void update() {
        for(WarpGate wg : warpGates)
            wg.update();
    }


    public Integer getCurrentwave() {
        return currentwave;
    }
    public void setCurrentwave(Integer currentwave) {
        this.currentwave = currentwave;
    }

    public void reset()
    {
        currentwave = 0;
    }


}
