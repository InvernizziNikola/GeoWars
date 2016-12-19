package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 19/12/2016.
 */
public class KamikazieEnemy  extends Enemy implements GOInterface {

    public KamikazieEnemy(Vector2 spawnLocation) {
        super("fighter", spawnLocation);
        speed =100;
    }

    @Override
    public void update() {

        if(pe != null)
            return;

        if(!insidePlayingField) {
            target = new Vector2(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight() / 2);
            if (position.x > 1
                    && position.x < Gdx.graphics.getWidth() - 1
                    && position.y > 1
                    && position.y < Gdx.graphics.getHeight() - 1) {
                insidePlayingField = true;
                offset = -0;
                target = Managers.getAccountManager().getAccounts().get(0).getPlayer().getShip().getPosition();
            }
        }
        if(insidePlayingField) {
            if (position.x < -offset || position.x > Gdx.graphics.getWidth() + offset)
                direction.x *= -1;
            if (position.y <= -offset || position.y > Gdx.graphics.getHeight() + offset)
                direction.y *= -1;
        }

        Vector2 dist = new Vector2(target.x - getPosition().x, target.y - getPosition().y);

        //aggro
        if (dist.len() < 500 || !insidePlayingField)
        {
            lookAt = dist.nor();
            if (dist.len()<300)
            {
                setColor(new Color(1,0,0,1));
                speed=250;
            }
        }
        else
        {
            lookAt = direction.nor();
        }
        position.mulAdd(lookAt.nor(), speed * Gdx.graphics.getDeltaTime());
    }
}
