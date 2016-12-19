package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.ClusterBullet;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */

public class BulletManager implements GOInterface {

    private List<Bullet> bullets;
    private List<Bullet> toRemove;
    private List<Bullet> toAdd;
    public BulletManager () {

        bullets = new LinkedList<Bullet>();
        toRemove = new LinkedList<Bullet>();
        toAdd = new LinkedList<Bullet>();
    }

    public void init()
    {

    }
    public void addBullet(Bullet bullet)
    {
        toAdd.add(bullet);
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
            if(b instanceof ClusterBullet) {
                ((ClusterBullet) b).explode();
                if(((ClusterBullet) b).isExploded()){toRemove.add(b);}

            }
            b.update();
        }

        bullets.removeAll(toRemove);
        toRemove.clear();
        bullets.addAll(toAdd);
        toAdd.clear();
    }

    public void reset()
    {
        bullets.clear();
        toRemove.clear();
    }

    public void clearAll() {
        bullets.clear();
    }

    public void remove(Bullet b)
    {
        b.destroy = true;
        toRemove.add(b);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }
}
