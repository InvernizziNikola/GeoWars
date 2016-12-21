package com.group17.geowars.gameobjects.hostileObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 19/12/2016.
 */
public class SuicideUnitEnemy extends Enemy implements GOInterface {

    public SuicideUnitEnemy(Vector2 spawnLocation) {
        super("fighter", spawnLocation);
        speed =100;
    }

    @Override
    public void update() {


        target = findTarget();

        if(target == null)
            return;

        Vector2 dist = new Vector2(target.getShip().getPosition().x - getPosition().x, target.getShip().getPosition().y - getPosition().y);

        //aggro
        if (dist.len() < 500)
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

        super.update();
    }
}
