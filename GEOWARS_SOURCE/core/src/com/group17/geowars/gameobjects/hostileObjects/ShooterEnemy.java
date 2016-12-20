package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.managers.Managers;

import java.util.Random;

/**
 * Created by kevin on 19/12/2016.
 */
public class ShooterEnemy extends Enemy implements GOInterface {
    private boolean canShoot = true;
    private float timer = 0;

    public ShooterEnemy(Vector2 spawnLocation) {
        super("fighter", spawnLocation);
        speed =125;
    }

    public void shoot() {
        if (canShoot) {
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(lookAt), false));
            canShoot = false;
        }
    }

    @Override
    public void update() {
        //TODO spawn in field ( cant get out of field
        isInfield();

        timer += Gdx.graphics.getDeltaTime();
        if (timer > 1.0f) {
            timer %= 1.0f;
            canShoot = true;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        //aggro
        if (dist.len() < 500 || !insidePlayingField) {//TODO remove ISinPlayingField
            lookAt = new Vector2(dist).nor();

            if (dist.len() < 400) {
                if(new Random().nextInt(9)<7) {//1/2 kans om te schieten
                    shoot();
                    canShoot=false;
                }

            }
            if (dist.len() < 200) {//TODO Don't push out of the screen
                lookAt = new Vector2(-lookAt.x,-lookAt.y);

            }

        } else {
            lookAt = direction.nor();


        }
        position.mulAdd(lookAt.nor(), speed * Gdx.graphics.getDeltaTime());
    }
}