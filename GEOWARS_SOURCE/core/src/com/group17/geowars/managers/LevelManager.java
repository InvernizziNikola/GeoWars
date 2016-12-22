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
import com.group17.geowars.utils.GAMESTATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kevin on 9/11/2016.
 */

public class LevelManager {

    private List<WarpGate> warpGates;
    private List<WarpGate> toRemove;
    private int currentwave;
    private int currentLevel;
    private BitmapFont font;
    private Texture textureBG;
    private Sprite spriteBG;
    public boolean isSpawning;

    public LevelManager () {

        warpGates = new ArrayList<WarpGate>();
        toRemove = new ArrayList<WarpGate>();
        currentwave = 1;
        currentLevel = 1;
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
        isSpawning = true;

        int points = currentLevel * 20 + currentwave * 3;
        int warpGateCount = MathUtils.ceil(points/35.0f);

        Random rand = new Random();
        List<EnemyProfile> enemyProfiles = Managers.getEnemyManager().getProfiles();
        System.out.println(warpGateCount);
        // TODO MULTIPLE WARPGATES
        {
            List<EnemyProfile> enemiesToWarp = new ArrayList<EnemyProfile>();

            while (points > 0) {
                EnemyProfile ep = enemyProfiles.get(rand.nextInt(enemyProfiles.size()));
                if (ep.type != ENEMYTYPE.BOSS) {
                    points -= ep.difficultyGrade;
                    enemiesToWarp.add(ep);
                }
            }

            warpGates.add(new WarpGate(new Vector2(1200, 800), enemiesToWarp));
        }
        
    }

    public void render(Batch batch) {

        spriteBG.draw(batch);
        font.draw(batch, "Wave " + Managers.getLevelManager().getCurrentwave(), GeoWars.WIDTH/2, GeoWars.HEIGHT-50);

        for(WarpGate wg : warpGates)
            wg.render(batch);
    }

    public void update() {
        for(WarpGate wg : warpGates) {
            wg.update();
            if(wg.finished())
                toRemove.add(wg);
        }

        warpGates.removeAll(toRemove);
        toRemove.clear();

        if(isSpawning == true && warpGates.size() == 0) {
            isSpawning = false;
            Managers.getGameManager().setGameState(GAMESTATE.GAMEPLAYING);
        }
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
