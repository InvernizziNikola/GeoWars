package com.group17.geowars.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.group17.geowars.database.EnemyLoot;
import com.group17.geowars.managers.ScoreManager;

/**
 * Created by nikola on 07/11/2016.
 */
public class Geom extends GameObject
{
    private EnemyLoot loot;
    private float timer;

    public Geom(int enemyID)
    {
        super();
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

    @Override
    public void render(Batch batch)
    {
        Texture img = new Texture("badlogic.jpg");
        batch.draw(img, position.x, position.y);
        // TODO DRAW IMAGE
    }

    @Override
    public void update()
    {
        timer+= Gdx.graphics.getDeltaTime();

        position.x += MathUtils.sin(timer);

        // TODO MOVEMENT
    }

}
