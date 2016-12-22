package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.hostileObjects.SuicideUnitEnemy;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 19/12/2016.
 */
public class ClusterBullet extends Bullet {
    private float timer = 0;
    private Boolean isBoss;


    public ClusterBullet(Vector2 pos, Vector2 dir, Boolean boss) {
        super(pos, dir, false);
        setSize(30);
        isBoss = boss;
        speed = 125;
        damage=5;
    }

    public ClusterBullet(Vector2 pos, Vector2 dir) {
        this(pos, dir, false);
    }


    public void explode() {
        float angle = 0;
        while (angle < 360) {
            Vector2 dir = new Vector2(1, 1);
            dir.rotate(angle);

            angle += 30;
            if (isBoss) {
                Managers.getEnemyManager().addEnemy(new SuicideUnitEnemy(new Vector2(position)));
            } else {
                Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(dir), false));
            }
        }
        Managers.getBulletManager().remove(this);
    }

    @Override
    public void update() {
        timer += Gdx.graphics.getDeltaTime();
        if (timer > 3.0f) {
            timer %= 3.0f;
            explode();

        }
        handleUpdate();
    }

}
