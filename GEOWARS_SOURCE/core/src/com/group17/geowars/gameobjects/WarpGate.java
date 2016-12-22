package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.WARPSTATE;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nikola on 22/12/2016.
 */

public class WarpGate {

    private Texture texture;
    private Sprite sprite;
    private float size;
    private float maxSize;
    private float angle;
    private float rotateSpeed;
    private Vector2 position;
    private WARPSTATE state;
    private List<EnemyProfile> enemiesToSpawn;
    private Random rand = new Random();
    private float timer = 200;

    public boolean finished()
    {
        if(state == WARPSTATE.FINISHED)
            return true;
        return false;
    }

    public WarpGate(Vector2 position, List<EnemyProfile> enemiesToSpawn)
    {
        this.position = position;
        size = 0;
        this.enemiesToSpawn = enemiesToSpawn;
        maxSize = 200;
        rotateSpeed = 0.5f;
        state = WARPSTATE.LOADING;

        texture = Managers.getAssetManager().getTexture("warp");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public void update()
    {
        switch(state)
        {
            case LOADING:
            {
                size+=2;
                angle -= rotateSpeed*10;
                if(size >= maxSize)
                {
                    size=maxSize;
                    state = WARPSTATE.SPAWNING;
                }
                break;
            }
            case SPAWNING:
            {
                angle -= rotateSpeed*2;
                spawnEnemies();
                if(enemiesToSpawn.size() == 0)
                    state = WARPSTATE.UNLOADING;
                break;
            }
            case UNLOADING:
            {
                size-=2;
                angle -= rotateSpeed*10;
                if(size <= 0)
                {
                    state = WARPSTATE.FINISHED;
                    size=0;
                }
                break;
            }
            case FINISHED:{

            }
        }
    }

    public void spawnEnemies()
    {
        ArrayList<EnemyProfile> toRemove = new ArrayList<EnemyProfile>();
        for(EnemyProfile ep : enemiesToSpawn)
        {
            // SPAWN ENEMY
            float x = rand.nextFloat() % 200;
            float y = rand.nextFloat() % 200;
            x = position.x+(x - 0.5f)*size/2;
            y = position.y+(y - 0.5f)*size/2;

            Managers.getEnemyManager().spawnEnemy(ep, new Vector2(x, y));
            toRemove.add(ep);
        }
        enemiesToSpawn.removeAll(toRemove);
        toRemove.clear();
    }
    public void render(Batch batch)
    {
        //sprite.setColor(color);
        sprite.setSize(size, size);
        sprite.setOrigin(size / 2, size / 2);
        sprite.setRotation(angle);
        sprite.setPosition(position.x - size / 2, position.y - size / 2);
        sprite.draw(batch);
    }
}
