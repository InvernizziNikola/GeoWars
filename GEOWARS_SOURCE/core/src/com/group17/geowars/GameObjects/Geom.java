package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.managers.AssetManager;
import com.group17.geowars.managers.BulletManager;
import com.group17.geowars.managers.ScoreManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nikola on 07/11/2016.
 */
public class Geom extends GameObject implements GOInterface
{
    private EnemyLoot loot;
    private float rotateSpeed = 100;
    private float angle = 0;
    private float rotateDirection = 1;

    public Geom(int enemyID, Vector2 pos)
    {
        super(pos);
        
        Random rand = new Random();
        angle = rand.nextInt();
        rotateSpeed *= rand.nextFloat() + 0.5f;
        rotateDirection = (rand.nextInt(2) * 2) -1;
        
        texture = AssetManager.getInstance().getTexture("geom");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        setGeomLoot(enemyID);
    }

    private void setGeomLoot(int enemyId)
    {
        loot = ScoreManager.GetInstance().getLoot(enemyId);
    }

    public EnemyLoot getLoot()
    {
        return loot;
    }


    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(50, 50);
        sprite.setOrigin(25,25);
        sprite.setRotation(angle);
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    public void update()
    {
        // TODO MOVEMENT
        // ROTATE
        // move


        // TODO REMOVE COLLISION BULLET
        List<Bullet> bulletList = BulletManager.GetInstance().getBullets();

        List<Bullet> toRemove = new ArrayList<Bullet>();


        for (Bullet b: bulletList) {
            Vector2 newV = new Vector2(b.getPosition().x - getPosition().x, b.getPosition().y - getPosition().y);
            if(newV.len() < 10){
                toRemove.add(b);
            }
        }

        bulletList.removeAll(toRemove);

        angle += Gdx.graphics.getDeltaTime() * rotateSpeed * rotateDirection;
        angle = angle % 360;
    }
}
