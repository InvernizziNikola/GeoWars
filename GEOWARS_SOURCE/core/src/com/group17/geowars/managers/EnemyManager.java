package com.group17.geowars.managers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kevin on 9/11/2016.
 */
public class EnemyManager implements GOInterface {
    private static EnemyManager _instance;

    public static EnemyManager GetInstance()
    {
        if(_instance == null)
            _instance = new EnemyManager();

        return _instance;
    }

    private List<Enemy> enemies;

    private EnemyManager () {
        enemies = new LinkedList<Enemy>();
        enemies.add( new Enemy(LevelManager.GetInstance().getEnemies().get(0),LevelManager.GetInstance().getSpawnLocations().get(0)));//HACK:testen of 1 enemy kan opgevraagd worden
        enemies.add( new Enemy(LevelManager.GetInstance().getEnemies().get(1),LevelManager.GetInstance().getSpawnLocations().get(1)));
    }

    public void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void clearAll() {
        enemies.clear();
    }


    @Override
    public void render(Batch batch) {
        for (Enemy e: enemies) {
            e.render(batch);

        }
    }

    @Override
    public void update() {
        for (Enemy e: enemies) {
            e.update();
        }
    }
}