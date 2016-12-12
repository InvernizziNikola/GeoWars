package com.group17.geowars.managers;

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
