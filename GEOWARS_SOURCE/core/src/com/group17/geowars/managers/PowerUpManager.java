package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.PowerUp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 12/11/2016.
 */
public class PowerUpManager implements GOInterface {

    private List<PowerUp> powerUpList;
    private List<PowerUp> toRemove;

    public PowerUpManager(){
        powerUpList = new ArrayList<PowerUp>();
        toRemove = new ArrayList<PowerUp>();
    }

    public void init()
    {

    }

    public void addPowerUp(PowerUp powerUp)
    {
        powerUpList.add(powerUp);
    }

    @Override
    public void render(Batch batch) {
        for (PowerUp pow : powerUpList) pow.render(batch);
    }

    @Override
    public void update() {

        for (PowerUp pow : powerUpList)
        {
            pow.update();
        }

        powerUpList.removeAll(toRemove);
    }

    public void removeGeom(PowerUp g) {

        toRemove.add(g);
        g.destroy = true;
    }

    public List<PowerUp> getPowerUpList() {
        return powerUpList;
    }
}
