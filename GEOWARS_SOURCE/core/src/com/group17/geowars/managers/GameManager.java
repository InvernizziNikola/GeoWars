package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.database.Threads.SaveScoreToDBThread;
import com.group17.geowars.gamemodes.base.BaseGame;
import com.group17.geowars.gamemodes.base.iGame;
import com.group17.geowars.playerobjects.Account;

/**
 * Created by nikola on 10/11/2016.
 */

public class GameManager {


    public BaseGame game;

    private SaveScoreToDBThread SaveScoreThread;
    private int score = 0;

    public void setEndScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }

    public void newGame(BaseGame game)
    {
        this.game = game;
    }

    public GameManager()
    {


    }

    public void handleEndGame()
    {


    }
    public void init()
    {

    }

    public void endGame()
    {
        score = Managers.getGameManager().getScore();

        for(Account a : Managers.getAccountManager().getAccounts())
        {
            setHighScore(a.name, a.getPlayer().getScore(),game.getMode());
        }

        game = null;
        Managers.getPlayerManager().reset();

    }

    public void setHighScore(String Playername,Integer Score,String Gamemode)
    {
        SaveScoreThread = new SaveScoreToDBThread(Playername,Score,Gamemode);
        SaveScoreThread.start();

    }
    public void update()
    {
        if(game instanceof iGame)
            ((iGame)game).update();

        Managers.getCollisionManager().update();
        Managers.getControllerManager().update();
        Managers.getGeomManager().update();
        Managers.getBulletManager().update();
        Managers.getEnemyManager().update();
        Managers.getLevelManager().update();
        Managers.getpowerUpManager().update();
        Managers.getPlayerManager().update();
    }

    public void render(Batch batch)
    {
        Managers.getLevelManager().render(batch);
        Managers.getGeomManager().render(batch);
        Managers.getpowerUpManager().render(batch);
        Managers.getBulletManager().render(batch);
        Managers.getEnemyManager().render(batch);
        Managers.getPlayerManager().render(batch);
    }

    public void resetGame()
    {
        Managers.getpowerUpManager().reset();
        Managers.getBulletManager().reset();
        Managers.getEnemyManager().reset();
        Managers.getGeomManager().reset();
        Managers.getLevelManager().reset();
        Managers.getPlayerManager().reset();
    }
}
