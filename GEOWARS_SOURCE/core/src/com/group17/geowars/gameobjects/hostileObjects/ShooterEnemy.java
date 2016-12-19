package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 19/12/2016.
 */
public class ShooterEnemy extends Enemy implements GOInterface {
    private int EnemyType;
    private int hp;
    private int attack;
    private boolean canShoot = true;
    private float timer = 0;

    private Vector2 shootDir = new Vector2(0,0);
    private Vector2 moveDir = new Vector2(0,0);
    private Vector2 lookAt = new Vector2(0,0);


    public ShooterEnemy(Vector2 spawnLocation)
    {
        super("fighter",new Vector2(0,0));
    }
    public void shoot()
    {
        System.out.println(canShoot);
        if(canShoot) {
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(lookAt),false));
            canShoot = false;
        }
    }

    @Override
    public void update() {

        if(!insidePlayingField) {
            target = new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 2);
            if (position.x > 1
                    && position.x < Gdx.graphics.getWidth() - 1
                    && position.y > 1
                    && position.y < Gdx.graphics.getHeight() - 1) {
                insidePlayingField = true;
                offset = -0;
                target = Managers.getAccountManager().getAccounts().get(0).getPlayer().getShip().getPosition();
            }
        }
        if(insidePlayingField) {
            if (position.x < -offset || position.x > Gdx.graphics.getWidth() + offset)
                direction.x *= -1;
            if (position.y <= -offset || position.y > Gdx.graphics.getHeight() + offset)
                direction.y *= -1;
        }
        timer += Gdx.graphics.getDeltaTime();
        if(timer > 0.15f) {
            timer %= 0.15f;
            canShoot = true;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        //aggro
        if (dist.len() < 500 || !insidePlayingField)
        {
            shoot();
            lookAt = dist.nor();

        }else
        {
            lookAt = direction.nor();
        }
        position.mulAdd(lookAt.nor(), 125 * Gdx.graphics.getDeltaTime());
    }
}