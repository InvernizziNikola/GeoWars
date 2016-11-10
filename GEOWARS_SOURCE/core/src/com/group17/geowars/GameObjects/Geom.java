package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.managers.Managers;

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
        
        texture = Managers.getAssetManager().getTexture("geom");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        setGeomLoot(enemyID);
    }

    private void setGeomLoot(int enemyId)
    {
        loot = Managers.getScoreManager().getLoot(enemyId);
    }

    public EnemyLoot getLoot()
    {
        return loot;
    }

    public void handlePickedUp()
    {}

    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(50, 50);
        sprite.setOrigin(25,25);
        sprite.setRotation(angle);
        sprite.setPosition(position.x - 25, position.y - 25);
        sprite.draw(batch);
    }

    @Override
    public void update()
    {
        // TODO MOVEMENT
        boolean toRemove2 = false;

        angle += Gdx.graphics.getDeltaTime() * rotateSpeed * rotateDirection;
        angle = angle % 360;

        List<Bullet> bulletList = Managers.getBulletManager().getBullets();
        List<Bullet> toRemove = new ArrayList<Bullet>();

        for (Bullet b: bulletList) {
            Vector2 distance = new Vector2(b.getPosition().x - getPosition().x, b.getPosition().y - getPosition().y);
            if(distance.len() < 25)
            {
                toRemove.add(b);
                handlePickedUp();
                Managers.getGeomManager().removeGeom(this);
                toRemove2 = true;
                break;
            }
        }
        bulletList.removeAll(toRemove);


    }
        // ROTATE
        // move



}
