package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nikola on 08/11/2016.
 */
public interface GOInterface
{
    void render(Batch batch);
    void update();
}
