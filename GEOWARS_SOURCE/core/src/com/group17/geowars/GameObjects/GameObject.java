package com.group17.geowars.GameObjects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nikola on 08/11/2016.
 */
public abstract class GameObject
{
    protected Vector2 position;

    public GameObject()
    {
        position = new Vector2(0,0);
    }

    public void render(Batch batch)
    {

    }
    public void update()
    {

    }


}
