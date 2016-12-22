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

    public void init() {
        newEnemyList(0);
    }
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
                enemies.add(tempEnemy);
                break;
            }
            case KAMIKAZE:{
                tempEnemy = new ScoutEnemy(position);
                enemies.add(tempEnemy);
                break;
            }
            case DREADNOUGHT:{
                tempEnemy = new ScoutEnemy(position);
                enemies.add(tempEnemy);
                break;
            }
            case BOSS:{
                tempEnemy = new DreadnoughtBoss(position);
                enemies.add(tempEnemy);
                break;
            }
        }
    }

    public void newEnemyList(int currentWave) {

        /*if (Managers.getLevelManager().getCurrentwave() == 2) {
            List<Vector2> spawnlist = Managers.getLevelManager().getSpawnLocations();
            enemies.add(new DreadnoughtBoss(spawnlist.get(new Random().nextInt(spawnlist.size() - 1))));
        } else {
            for (int i = Managers.getLevelManager().getWaveList().get(currentWave); i > 0; i--) {

                List<Vector2> spawnlist = Managers.getLevelManager().getSpawnLocations();
                //Managers.getLevelManager().getEnemies().get(new Random().nextInt(Managers.getLevelManager().getEnemies().size()))
                int randomHackval = new Random().nextInt(10);

                if (randomHackval <= 3) {
                    enemies.add(new ScoutEnemy(spawnlist.get(new Random().nextInt(spawnlist.size() - 1))));
                } else {
                    if (randomHackval > 3 && randomHackval <= 8) {
                        enemies.add(new SuicideUnitEnemy(spawnlist.get(new Random().nextInt(spawnlist.size() - 1))));
                    } else {
                        enemies.add(new DreadnoughtEnemy(spawnlist.get(new Random().nextInt(spawnlist.size() - 1))));
                    }
                }
            }
        }*/
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
        enemies.clear();
        toRemove.clear();
    }

    @Override
    public void update() {

        if(LET != null && LET.finished()) {

            enemyProfiles = LET.getEnemies();
            LET = null;
        }
        for (Enemy e : enemies) {
            e.update();
        }

        enemies.removeAll(toRemove);
        toRemove.clear();
    }

    public void remove(Enemy enemy) {
        enemy.destroy = true; //TODO to private
        toRemove.add(enemy);
    }
}
