package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;

/**
 * Created by nikola on 08/11/2016.
 */

public class Bullet extends GameObject implements GOInterface {

    Vector2 direction = new Vector2(0,0);
    public float speed;

    public boolean destroy = false;
    private Color color;
    private int size;

    private boolean isFriendly = true;


    public Bullet(Vector2 pos, Vector2 dir) {
        this(pos,dir,true);
        color=new Color(0.1f,0.8f,0.8f,0.8f);
        speed = 400;
    }
    public Bullet(Vector2 pos, Vector2 dir, boolean isFriendly) {
        super(pos);
        speed = 200;
        color=new Color(0.9f,0.1f,0.1f,0.9f);
        size=15;
        this.isFriendly =isFriendly;
        // normalize just incase
        direction = dir.nor();

        texture = Managers.getAssetManager().getTexture("bullet");

        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

    public boolean isFriendly(){ return isFriendly; }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
        sprite.setColor(color);
        sprite.setSize(size,size);
        sprite.setOrigin(10,10);
        sprite.setRotation(direction.angle() - 90);
        sprite.setPosition(position.x - 10, position.y - 10);
        sprite.draw(batch);

    }

    @Override
    public void update()
    {
        if(position.x < -10 ||
                position.x > GeoWars.ORIGINALWIDTH + 10 ||
                position.y < -10 ||
                position.y > GeoWars.ORIGINALHEIGHT + 10) {
            Managers.getBulletManager().remove(this);
        }

        position.mulAdd(direction,speed * Gdx.graphics.getDeltaTime());
    }
}
