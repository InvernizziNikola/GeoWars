package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by nikola on 08/11/2016.
 */

public class Bullet extends GameObject implements GOInterface {

    Vector2 direction = new Vector2(3,3);
    float speed = 200;

    public Bullet(Vector2 pos) {
        super(pos);



        texture = new Texture("thief.png");

        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());


    }

    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(new Color(0.8f, 0.8f,0,1));
        sprite.setSize(20,20);
        sprite.setOrigin(10,10);
        //sprite.setRotation(difference.angle());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);

    }

    @Override
    public void update()
    {
        position.mulAdd(direction,speed * Gdx.graphics.getDeltaTime());
    }
}
