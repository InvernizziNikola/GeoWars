package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nikola on 08/11/2016.
 */
public abstract class GameObject
{
    protected Vector2 position;
    protected Texture texture;
    protected Sprite sprite;

    public GameObject(Vector2 pos)
    {
        position = pos;
    }
    public Vector2 getPosition()
    {
        return position;
    }
}