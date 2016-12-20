package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.hostileObjects.KamikazieEnemy;
import com.group17.geowars.managers.Managers;

import java.util.Random;

/**
 * Created by kevin on 19/12/2016.
 */
public class ClusterBullet extends Bullet {
    private float timer = 0;

    public ClusterBullet(Vector2 pos, Vector2 dir) {
        super(pos, dir, false);
        setSize(30);

        speed=125;
    }


    public void explode() {
        float angle = 0;
        while (angle < 360) {
            Vector2 dir = new Vector2(1, 1);
            dir.rotate(angle);

            angle += 30;
           // Managers.getEnemyManager().addEnemy(new KamikazieEnemy(new Vector2(position)));
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(dir), false));
        }
        Managers.getBulletManager().remove(this);
    }

    @Override
    public void update()
    {
        timer += Gdx.graphics.getDeltaTime();
        if (timer > 3.0f) {
            timer %= 3.0f;
            explode();

        }
        handleUpdate();
    }

}
