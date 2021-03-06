package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.PowerUps.POWERUPTYPE;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.gameobjects.PowerUps.PowerUp_Nuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kevin on 12/11/2016.
 */
public class PowerUpManager implements GOInterface {

    private List<PowerUp> powerUpList;
    private List<PowerUp> toRemove;
    private List<PowerUp> toAdd;

    public PowerUpManager(){
        powerUpList = new ArrayList<PowerUp>();
        toRemove = new ArrayList<PowerUp>();
        toAdd = new ArrayList<PowerUp>();
    }

    public void init()
    {

    }

    public void addPowerUp(PowerUp powerUp)
    {
        toAdd.add(powerUp);
    }

    @Override
    public void render(Batch batch) {
        for (PowerUp pow : powerUpList)
            pow.render(batch);
    }

    @Override
    public void update() {

        powerUpList.addAll(toAdd);
        toAdd.clear();

        for (PowerUp pow : powerUpList)
            pow.update();

        powerUpList.removeAll(toRemove);
        toRemove.clear();
    }

    public void activatePowerUp(PowerUp pu)
    {
        pu.activate();
        pu.setDestroy(true);
    }
    public void removePowerUp(PowerUp pu)
    {
        toRemove.add(pu);
    }

    public void reset()
    {
        powerUpList.clear();
        toRemove.clear();
    }
    public List<PowerUp> getPowerUpList() {
        return powerUpList;
    }

    public PowerUp GetRandomPowerup()
    {
        Random r = new Random();

        switch(r.nextInt(3))
        {
            case 0:
            {
                break;
            }
            case 1:
            {
                break;
            }
            case 2:
            {
                break;
            }
            case 3:
            {
                break;
            }
        }
        return new PowerUp_Nuke(new Vector2(300,300));
    }
}
