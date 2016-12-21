package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.ClusterBullet;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 20/12/2016.
 */
public class DreadnoughtBoss extends DreadnoughtEnemy {

    public DreadnoughtBoss(Vector2 spawnLocation) {
        super(spawnLocation);
        maxHp=100;
        hp=100;
        size=250;
    }

    public void shoot() {
        if (canShoot) {
            Managers.getBulletManager().addBullet(new ClusterBullet(new Vector2(position), new Vector2(lookAt),true));
            canShoot = false;


        }
    }
}
