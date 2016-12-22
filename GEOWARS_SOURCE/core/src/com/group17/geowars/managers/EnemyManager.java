package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.database.Threads.LoadEnemyThread;
import com.group17.geowars.gameobjects.hostileObjects.*;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.utils.ENEMYTYPE;

import java.util.*;

/**
 * Created by kevin on 9/11/2016.
 */
public class EnemyManager implements GOInterface {

    private LoadEnemyThread LET;
    private List<EnemyProfile> enemyProfiles;
    public List<EnemyProfile> getProfiles()
    {
        return enemyProfiles;
    }
    private List<Enemy> enemies;
    private List<Enemy> toRemove;

    public EnemyManager() {

        LET = new LoadEnemyThread();
        LET.start();

        enemies = new LinkedList<Enemy>();
        toRemove = new LinkedList<Enemy>();
    }

    public void init() { }
    public void addEnemy(Enemy e)
    {
        enemies.add(e);
    }
    public void spawnEnemy(EnemyProfile profile, Vector2 position)
    {
        ENEMYTYPE type = profile.type;
        Enemy tempEnemy;
        switch(type)
        {
            case SCOUT:{
                tempEnemy = new ScoutEnemy(position);
                addEnemy(tempEnemy);
                break;
            }
            case KAMIKAZE:{
                tempEnemy = new SuicideUnitEnemy(position);
                addEnemy(tempEnemy);
                break;
            }
            case DREADNOUGHT:{
                tempEnemy = new DreadnoughtEnemy(position);
                addEnemy(tempEnemy);
                break;
            }
            case BOSS:{
                tempEnemy = new DreadnoughtBoss(position);
                addEnemy(tempEnemy);
                break;
            }
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void clearAll() {
        enemies.clear();
    }


    @Override
    public void render(Batch batch) {

        for (Enemy e : enemies) {
            e.render(batch);
        }
    }

    public void reset() {
        clearAll();
        toRemove.clear();
    }

    @Override
    public void update() {

        loadEnemies();
        for (Enemy e : enemies) {
            e.update();
        }
        enemies.removeAll(toRemove);
        toRemove.clear();
    }

    public void loadEnemies()
    {
        if(LET != null && LET.finished()) {

            enemyProfiles = LET.getEnemies();
            LET = null;
        }
    }
    public void remove(Enemy enemy) {
        enemy.destroy = true; //TODO to private
        toRemove.add(enemy);
    }
}
