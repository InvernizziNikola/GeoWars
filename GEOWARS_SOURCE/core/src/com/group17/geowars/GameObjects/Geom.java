package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.managers.Managers;

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

    private float speed = 0;
    private float maxSpeed = 100;
    private Vector2 dir = new Vector2(0,0);

    public boolean destroy = false;

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


    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(0.8f, 0.8f,0,0.6f));
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




        Vector2 playerPos = Managers.getAccountManager().getAccounts().get(0).getPlayer().getShip().getPosition();

        Vector2 dist = new Vector2(playerPos.x - position.x, playerPos.y - position.y);

        if (dist.len() < 120)
        {
            dir = new Vector2(dist).nor();

            speed = MathUtils.lerp(speed, maxSpeed, Gdx.graphics.getDeltaTime()*20);

            position = new Vector2(position.x + dir.x * speed * Gdx.graphics.getDeltaTime(),
                    position.y + dir.y * speed * Gdx.graphics.getDeltaTime());
        }else
        {
            speed = MathUtils.lerp(speed, 0, Gdx.graphics.getDeltaTime());

            position = new Vector2(position.x + dir.x * speed * Gdx.graphics.getDeltaTime(),
                    position.y + dir.y * speed * Gdx.graphics.getDeltaTime());
        }

        angle += Gdx.graphics.getDeltaTime() * rotateSpeed * rotateDirection;
        angle = angle % 360;


    }
        // ROTATE
        // move
}
