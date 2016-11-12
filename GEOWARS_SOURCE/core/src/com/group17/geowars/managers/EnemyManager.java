package com.group17.geowars.managers;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by kevin on 9/11/2016.
 */
public class EnemyManager implements GOInterface {

    private List<Enemy> enemies;
    private List<Enemy> toRemove;
    private int nrOfEnemys; //HACK
    public EnemyManager () {
        enemies = new LinkedList<Enemy>();
        toRemove = new LinkedList<Enemy>();
    }

    public void init()
    {
        newEnemyList(0);
        nrOfEnemys=20;

    }

    public void newEnemyList(int currentWave)
    {

        for (int i = Managers.getLevelManager().getWaveList().get(currentWave); i>0; i--) {
            List<Vector2> spawnlist = Managers.getLevelManager().getSpawnLocations();
            enemies.add(new Enemy(Managers.getLevelManager().getEnemies().get(new Random().nextInt(Managers.getLevelManager().getEnemies().size()))
                    ,  spawnlist.get(new Random().nextInt(spawnlist.size()-1))));
        }
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
        if(enemies.size()==0)
        {
            int currentWave =Managers.getLevelManager().getCurrentwave();
            if (currentWave<Managers.getLevelManager().getWaveList().size()) {
                Managers.getLevelManager().setCurrentwave(currentWave+1);
                newEnemyList(currentWave);

            }
            else {
                nrOfEnemys= nrOfEnemys+(currentWave*10);
                System.out.println(nrOfEnemys);
                Managers.getLevelManager().getWaveList().add(nrOfEnemys);
            }

        }
        for (Enemy e: enemies) {
            e.render(batch);

        }
    }

    @Override
    public void update() {
        for (Enemy e: enemies) {
            e.update();
        }

        enemies.removeAll(toRemove);
    }

    public void remove(Enemy enemy) {
        enemy.destroy = true;
        toRemove.add(enemy);
    }
}
