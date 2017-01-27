package com.group17.geowars.gameobjects.PowerUps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 20/12/2016.
 */
public class PowerUp_Nuke extends PowerUp {

    boolean activated = false;
    private Texture textEx;
    private Sprite spriteEx;
    public PowerUp_Nuke(Vector2 pos) {
        super(pos,POWERUPTYPE.NUKE);
        text="BOOM!!!";
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
            spriteEx.setColor(color);
            spriteEx.setSize(30, 30);
            spriteEx.setOrigin(15, 15);
            spriteEx.setPosition(position.x - 15, position.y - 15);
            spriteEx.draw(batch);
        }
    }
    @Override
    public void update()
    {
        System.out.println("Test");
        super.update();
    }

    public void activate()
    {
        activated = true;
    }
}
