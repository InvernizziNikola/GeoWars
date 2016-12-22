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
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.ENEMYTYPE;
import com.group17.geowars.utils.GAMESTATE;
import com.sun.javafx.css.parser.DeriveColorConverter;

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
        currentwave = 0;
        currentLevel = 1;
    }
    public void init()
    {
        font = Managers.getScreenManager().getGameFont();

        //backgound stuff
        textureBG = Managers.getAssetManager().getTexture("test_background_geoWars");
        spriteBG = new Sprite(textureBG, GeoWars.WIDTH, GeoWars.HEIGHT);
    }

    public void newWave()
    {
        currentwave++;
        if(currentwave > 10) {
            currentwave = 1;
            currentLevel++;
        }

        isSpawning = true;

        float points = (float)currentLevel * 25.0f + (float)currentwave * 2.5f;
        float warpGateCount = points/50.0f;

        Random rand = new Random();
        List<EnemyProfile> enemyProfiles = Managers.getEnemyManager().getProfiles();
        for(float i = 0; i < warpGateCount; i++)
        {
            System.out.println(warpGateCount);
            System.out.println(points);

            float pointsPerWarp =  points / warpGateCount;
            List<EnemyProfile> enemiesToWarp = new ArrayList<EnemyProfile>();

            while (pointsPerWarp > 0) {
                EnemyProfile ep = enemyProfiles.get(rand.nextInt(enemyProfiles.size()));
                if (ep.type != ENEMYTYPE.BOSS) {
                    pointsPerWarp -= ep.difficultyGrade;
                    enemiesToWarp.add(ep);
                }
            }
            Vector2 warpPos;
            boolean okSpawnPoint = true;
            do
            {
                okSpawnPoint = true;
                warpPos = new Vector2(rand.nextInt(GeoWars.ORIGINALWIDTH-240)+120, rand.nextInt(GeoWars.ORIGINALHEIGHT-240)+120);
                for(Player p : Managers.getPlayerManager().getPlayers())
                {
                    if(warpPos.dst(p.getShip().getPosition()) < 100){
                        okSpawnPoint = false;
                        return;
                    }
                }
                if(okSpawnPoint) {
                    for (WarpGate wg : warpGates) {
                        if (warpPos.dst(wg.GetPosition()) < 300) {
                            okSpawnPoint = false;
                            return;
                        }
                    }
                }
            }
            while(!okSpawnPoint);

            warpGates.add(new WarpGate(warpPos, enemiesToWarp));
        }
    }

    public void render(Batch batch) {

        spriteBG.draw(batch);
        font.draw(batch, "Level "+ currentLevel + " Wave " + currentwave, GeoWars.WIDTH/2 - 100, GeoWars.HEIGHT-50);

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
        warpGates.clear();
        currentwave = 0;
        currentLevel = 0;
    }


}
