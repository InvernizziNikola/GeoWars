package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
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

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}