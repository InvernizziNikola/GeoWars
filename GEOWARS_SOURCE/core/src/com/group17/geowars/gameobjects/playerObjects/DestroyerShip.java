package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 21/12/2016.
 */
public class DestroyerShip extends Ship {
    public DestroyerShip(Vector2 pos, String type) {
        super(pos, type);
        texture = Managers.getAssetManager().getTexture("Destroyer");
        setSprite(new Sprite(texture, texture.getWidth(), texture.getHeight()));
        hp=10;
        fireDelay =0.05f;
        speed=375;
    }

    @Override
    public void shoot()
    {
        if(canShoot) {

            Vector2 sd = new Vector2(shootDir.x, shootDir.y);

            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));
            sd.rotate(-10);
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));
            sd.rotate(20);
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));

            canShoot = false;
        }
    }
}
