package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.AssetManager;
import com.group17.geowars.managers.BulletManager;

/**
 * Created by nikola on 08/11/2016.
 */

public class Bullet extends GameObject implements GOInterface {

    Vector2 direction = new Vector2(0,0);
    float speed = 1;

    public Bullet(Vector2 pos, Vector2 dir) {
        super(pos);
        direction = dir;

        texture = AssetManager.GetInstance().getTexture("thief");

        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());


    }

    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(1,0,0,1));
        sprite.setSize(20,20);
        sprite.setOrigin(10,10);
        //sprite.setRotation(difference.angle());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);

    }

    @Override
    public void update()
    {
        if(position.x < 0 || position.x > Gdx.graphics.getWidth())
            direction.x *= -1;
        if(position.y < 0 || position.y > Gdx.graphics.getHeight())
            direction.y *= -1;

        position.mulAdd(direction,speed * Gdx.graphics.getDeltaTime());
    }
}
