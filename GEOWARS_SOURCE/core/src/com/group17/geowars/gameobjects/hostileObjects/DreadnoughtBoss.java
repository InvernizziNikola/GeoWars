package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.gameobjects.ClusterBullet;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 20/12/2016.
 */
public class DreadnoughtBoss extends DreadnoughtEnemy {

    float timer = 0;
    float timerSpawnEnemies = 0;
    int spawnEnemies = 5;
    public DreadnoughtBoss(Vector2 spawnLocation) {
        super(spawnLocation);
        maxHp = 100;
        hp = 100;
        size = 250;
    }

    public void shoot() {
        if (canShoot) {
            Managers.getBulletManager().addBullet(new ClusterBullet(new Vector2(position), new Vector2(lookAt),true));
            canShoot = false;
        }
    }

    @Override
    public void fighting() {

        super.fighting();

        if(spawnEnemies == 0) {
            timer += Gdx.graphics.getDeltaTime();
            if (timer > 20) {
                timer = 0;
                spawnEnemies = 5;
            }
        }
        else {
            spawnEnemies();
        }
    }

    @Override
    public void update()
    {
        super.update();

    }

    public void spawnEnemies()
    {
        timerSpawnEnemies += Gdx.graphics.getDeltaTime();

        if(timerSpawnEnemies > 2.0f)
        {
            timerSpawnEnemies = 0;

            //spawnenemy

            Managers.getEnemyManager().spawnEnemy(new EnemyProfile(), position);

        }
    }

    @Override
    public void render(Batch batch) {
        super.render(batch);
    }
}
