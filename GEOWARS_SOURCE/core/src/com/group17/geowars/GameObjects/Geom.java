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
    private float maxScale = 1.2f;
    private float minScale = 0.8f;
    private float scale = 1;
    private float scaleSpeed = 1;
    private float maxSpeed = 250;
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
        // TODO COLOR IN CONSTRUCTOR WITH VARIABLE NT NEW COLOR EVERY FRAME
        sprite.setColor(new Color(1.0f, 1.0f, 0, 0.90f));

        sprite.setSize(50 * scale, 50 * scale);
        sprite.setOrigin(25 * scale,25 * scale);
        sprite.setRotation(angle);
        sprite.setPosition(position.x - 25*scale, position.y - 25*scale);
        sprite.draw(batch);
    }

    @Override
    public void update()
    {

        // TODO MOVEMENT

        // geom movement code
        Vector2 playerPos = Managers.getPlayerManager().getPlayers().get(0).getShip().getPosition();//TODO find target
        Vector2 dist = new Vector2(playerPos.x - position.x, playerPos.y - position.y);

        if (dist.len() < 120)
        {
            scale = MathUtils.lerp(scale, (maxScale+minScale) / 2, Math.abs(scaleSpeed) * Gdx.graphics.getDeltaTime());

            // zet afstand vector van afstand om naar lengte 1
            dir = new Vector2(dist).nor();

            speed = MathUtils.lerp(speed, maxSpeed, Gdx.graphics.getDeltaTime()*20);

            position = new Vector2(position.x + dir.x * speed * Gdx.graphics.getDeltaTime(),
                    position.y + dir.y * speed * Gdx.graphics.getDeltaTime());
        }else
        {
            scale += scaleSpeed * Gdx.graphics.getDeltaTime();
            if(scale < minScale)
            {
                scale = minScale;
                scaleSpeed *= -1;
            }
            else if(scale > maxScale)
            {
                scale = maxScale;
                scaleSpeed *= -1;
            }

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
