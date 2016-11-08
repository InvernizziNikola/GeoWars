package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.managers.ScoreManager;

/**
 * Created by nikola on 07/11/2016.
 */
public class Geom extends GameObject implements GOInterface
{
    private EnemyLoot loot;
    private float timer;
    private Vector2 direction;

    public Geom(int enemyID, Vector2 pos)
    {
        super(pos);

        texture = new Texture("fighter.png");

        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        setGeomData(enemyID);
    }

    private void setGeomData(int enemyId)
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
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        sprite.setRotation(direction.angle());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    public void update()
    {
        // TODO MOVEMENT

        Vector2 mousePos = new Vector2(Gdx.input.getX(), -Gdx.input.getY() + 600);
        direction = mousePos.sub(position.x + 25, position.y + 25);



        // TODO HACK TILL WE GET CONTROLLERS
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            position = new Vector2(position.x + (-200 * Gdx.graphics.getDeltaTime()), position.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            position = new Vector2(position.x + (200 * Gdx.graphics.getDeltaTime()), position.y);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            position = new Vector2(position.x, position.y + (-200 * Gdx.graphics.getDeltaTime()));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            position = new Vector2(position.x, position.y + (200 * Gdx.graphics.getDeltaTime()));
        }


    }

}
