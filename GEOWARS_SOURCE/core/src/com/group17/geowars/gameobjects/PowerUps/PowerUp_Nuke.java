package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.managers.Managers;
import net.dermetfan.gdx.physics.box2d.Breakable;

/**
 * Created by kevin on 20/12/2016.
 */
public class PowerUp_Nuke extends PowerUp {

    private float range = 1200;
    private float size = 0;
    private boolean activated = false;
    private Texture textEx;
    private Sprite spriteEx;
    public PowerUp_Nuke(Vector2 pos) {

        super(pos,POWERUPTYPE.NUKE);
        text="BOOM!!!";
        color = Color.RED;
        texture = Managers.getAssetManager().getTexture("images/powerup_nuke");
        sprite = new Sprite(texture, texture.getWidth(), texture.getHeight());

        textEx = Managers.getAssetManager().getTexture("shield");
        spriteEx = new Sprite(textEx, textEx.getWidth(), textEx.getHeight());
    }

    @Override
    public void render(Batch batch)
    {
        if(!activated) {
            super.render(batch);
        }else {
            spriteEx.setColor(color.r, color.g, color.b, color.a * ((range - size/2.0f) / range));
            spriteEx.setSize(size, size);
            spriteEx.setOrigin(size / 2, size /2);
            spriteEx.setPosition(position.x - (size / 2), (position.y - size /2));
            spriteEx.draw(batch);
        }
    }
    @Override
    public void update()
    {
        if(!activated) {
            super.update();
        }else{
            if(size < range)
            size += Gdx.graphics.getDeltaTime() * 2000;

            enemiesInBlastRadius();

            if(size >= range)
                Managers.getpowerUpManager().removePowerUp(this);
        }
    }


    public void enemiesInBlastRadius()
    {
        for(Enemy e : Managers.getEnemyManager().getEnemies())
        {
            if(!e.destroy) {
                if (e.getPosition().dst(getPosition()) < (size / 2.0f)) {
                    e.handleDead();
                }
            }
        }
    }


    @Override
    public void activate()
    {
        activated = true;
    }
}
