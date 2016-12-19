package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

import java.util.Random;

/**
 * Created by kevin on 19/12/2016.
 */
public class ClusterBullet extends Bullet {
    private int timer;
    private boolean exploded;

    public ClusterBullet(Vector2 pos, Vector2 dir) {
        super(pos, dir, false);
        setSize(30);
        timer = new Random().nextInt(100);
        speed=125;

    }


    public void explode() {


            float angle = 00;
            while (angle < 360) {
                Vector2 dir = new Vector2(1, 1);
                dir.rotate(angle);

                angle += 30;

                Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(dir), false));
            }
        Managers.getBulletManager().remove(this);


    }

    public boolean isExploded() {
        return exploded;
    }

    @Override
    public void update()
    {
        if(position.x < -10 ||
                position.x > Gdx.graphics.getWidth() +10 ||
                position.y < -10 ||
                position.y > Gdx.graphics.getHeight()+10) {
            Managers.getBulletManager().remove(this);
        }

        if (timer > 198) {
            exploded=true;
            explode();
        }
        else{
            timer = new Random().nextInt(200);
        }

        position.mulAdd(direction,speed * Gdx.graphics.getDeltaTime());
    }

}
