package com.group17.geowars.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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

    public Geom(int enemyID, Vector2 pos)
    {
        super(pos);

        texture = new Texture("fighter.png");

        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        setGeomData(enemyID);
    }

    public void setGeomData(int enemyId)
    {
        loot = ScoreManager.GetInstance().getLoot(enemyId);
    }

    public EnemyLoot getLoot()
    {
        return loot;
    }


    public void render(Batch batch)
    {

        //if(Input.Buttons.LEFT)

        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        sprite.setRotation(0);
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);

        // TODO DRAW IMAGE CORRRECTLY
    }
    public void update()
    {

        //timer+= Gdx.graphics.getDeltaTime()*5;

        //position.x += MathUtils.sin(timer) * 5;

        // TODO MOVEMENT
    }

}
