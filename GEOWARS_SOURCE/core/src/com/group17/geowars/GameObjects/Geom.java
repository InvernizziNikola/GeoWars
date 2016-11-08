package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
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
        float angle = 0;

        Vector2 mousePos = new Vector2(Gdx.input.getX(), -Gdx.input.getY() + 600);

        Vector2 difference = mousePos.sub(position.x + 25, position.y + 25);

        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(50,50);
        sprite.setOrigin(25,25);
        sprite.setRotation(difference.angle());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);



        // TODO DRAW IMAGE CORRRECTLY
    }

    @Override
    public void update()
    {

        // TODO MOVEMENT
    }

}
