package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.utils.GAMESTATE;

/**
 * Created by nikola on 10/11/2016.
 */

public class GameManager {


    public GAMESTATE gameState = GAMESTATE.MENU;

    public int score = 0;


    public void setEndScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }
    public GameManager()
    {

    }
    public void init()
    {

    }

    public void update()
    {
        Managers.getControllerManager().update();
        Managers.getAccountManager().update();
        Managers.getGeomManager().update();
        Managers.getBulletManager().update();
        Managers.getEnemyManager().update();
        Managers.getLevelManager().update();
        Managers.getCollisionManager().update();
        Managers.getpowerUpManager().update();
    }

    public void render(Batch batch)
    {
        Managers.getLevelManager().render(batch);
       // Managers.getAccountManager().render(batch);
        Managers.getGeomManager().render(batch);
        Managers.getBulletManager().render(batch);
        Managers.getEnemyManager().render(batch);
        Managers.getAccountManager().render(batch);
        Managers.getCollisionManager().render(batch);
        Managers.getpowerUpManager().render(batch);
    }

    public void resetGame()
    {
        Managers.getpowerUpManager().reset();
        Managers.getAccountManager().reset();
        Managers.getBulletManager().reset();
        Managers.getEnemyManager().reset();
        Managers.getGeomManager().reset();
        Managers.getLevelManager().reset();
    }
}
