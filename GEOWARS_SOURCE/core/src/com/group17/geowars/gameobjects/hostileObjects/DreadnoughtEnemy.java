package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.*;
import com.group17.geowars.managers.Managers;

import java.util.Random;

/**
 * Created by kevin on 19/12/2016.
 */
public class DreadnoughtEnemy extends Enemy implements GOInterface {
    protected boolean canShoot = true;
    private float timer = 0;


    public DreadnoughtEnemy(Vector2 spawnLocation) {
        super("tank", spawnLocation);
        speed = 50;
        setSize(150);
        maxHp = 10;
        hp = maxHp;
        fireRange = 450;
        firedelay = 4.0f;
    }

    public void shoot() {
        if (canShoot) {
            Managers.getBulletManager().addBullet(new ClusterBullet(new Vector2(position), new Vector2(lookAt)));
            canShoot = false;

        }
    }


    @Override
    public void update() {
        //TODO spawn in field ( cant get out of field

        target = findTarget();

        timer += Gdx.graphics.getDeltaTime();
        if (timer > firedelay) {
            timer %= firedelay;
            canShoot = true;
        }

        if (target == null)
            return;

        Vector2 dist = new Vector2(target.getShip().getPosition().x - getPosition().x, target.getShip().getPosition().y - getPosition().y);

        //aggro
        if (dist.len() < 750) {
            lookAt = new Vector2(dist).nor();

            if (dist.len() < fireRange) {
                if (new Random().nextInt(3) < 2) {//1/2 kans om te schieten
                    shoot();
                    canShoot = false;
                }

            }
            if (dist.len() < 300) {
                lookAt = new Vector2(-lookAt.x, -lookAt.y);
            }
        } else {
            lookAt = direction.nor();
        }

        super.update();
    }
}
