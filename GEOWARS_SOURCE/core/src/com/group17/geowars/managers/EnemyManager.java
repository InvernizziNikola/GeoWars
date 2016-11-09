package com.group17.geowars.managers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
        for (int i=LevelManager.GetInstance().getNrOfEnemys(); i>0;i--)
        {
            enemies.add( new Enemy(LevelManager.GetInstance().getEnemies().get(new Random().nextInt(LevelManager.GetInstance().getEnemies().size()))
                    ,LevelManager.GetInstance().getSpawnLocations().get(new Random().nextInt(LevelManager.GetInstance().getEnemies().size()))));

        }
        //HACK:testen of 1 enemy kan opgevraagd worden

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

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }
}
