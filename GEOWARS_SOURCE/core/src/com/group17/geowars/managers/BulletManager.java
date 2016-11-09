package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */
public class BulletManager implements GOInterface {
    private static BulletManager _instance;

    public static BulletManager GetInstance()
    {
        if(_instance == null)
            _instance = new BulletManager();

        return _instance;
    }

    private List<Bullet> bullets;

    private BulletManager () {
        bullets = new LinkedList<Bullet>();
    }

    public void addBullet(Bullet bullet)
    {
        bullets.add(bullet);
    }

    @Override
    public void render(Batch batch) {
        for (Bullet b: bullets) {
            b.render(batch);

        }
    }

    @Override
    public void update() {
        for (Bullet b: bullets) {
            b.update();
        }
    }

    public void clearAll() {
        bullets.clear();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
