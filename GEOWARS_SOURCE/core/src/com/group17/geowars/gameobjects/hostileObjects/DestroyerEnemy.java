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
public class DestroyerEnemy extends Enemy implements GOInterface {
    private boolean canShoot = true;
    private float timer = 0;


    public DestroyerEnemy (Vector2 spawnLocation) {
        super("tank", spawnLocation);
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
        if (!insidePlayingField) {
            target = new Vector2(GeoWars.WIDTH / 2, GeoWars.HEIGHT / 2);
            if (position.x > 1
                    && position.x < Gdx.graphics.getWidth() - 1
                    && position.y > 1
                    && position.y < Gdx.graphics.getHeight() - 1) {
                insidePlayingField = true;
                offset = -0;
                target = Managers.getAccountManager().getAccounts().get(0).getPlayer().getShip().getPosition();
            }
        }
        if (insidePlayingField) {
            if (position.x < -offset || position.x > GeoWars.WIDTH + offset)
                direction.x *= -1;
            if (position.y <= -offset || position.y > GeoWars.HEIGHT + offset)
                direction.y *= -1;
        }
        timer += Gdx.graphics.getDeltaTime();
        if (timer > 0.75f) {
            timer %= 0.75f;
            canShoot = true;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        //aggro
        if (dist.len() < 750 || !insidePlayingField) {//TODO remove ISinPlayingField
            lookAt = new Vector2(dist).nor();

            if (dist.len() < 600) {
                if(new Random().nextInt(2)<1) {//1/2 kans om te schieten
                    shoot();
                }

            }
            if (dist.len() < 300 &&insidePlayingField) {//TODO Don't push out of the screen
                lookAt = new Vector2(-lookAt.x,-lookAt.y);

            }

        } else {
            lookAt = direction.nor();


        }
        position.mulAdd(lookAt.nor(), 125 * Gdx.graphics.getDeltaTime());
    }
}
