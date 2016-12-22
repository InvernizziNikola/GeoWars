package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.database.Threads.SaveScoreToDBThread;
import com.group17.geowars.gamemodes.ArcadeSoloGame;
import com.group17.geowars.gamemodes.base.BaseGame;
import com.group17.geowars.gamemodes.base.iGame;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.screens.MenuScreen;
import com.group17.geowars.utils.GAMESTATE;

/**
 * Created by nikola on 10/11/2016.
 */

public class GameManager {


    public BaseGame game;
    private boolean resetGame = false;
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

    public void setGameState(GAMESTATE gs)
    {
        game.setGameState(gs);
    }
    public void setDifficulty()
    {

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
        game.setGameState(GAMESTATE.GAMEEND);

        for(Account a : Managers.getAccountManager().getAccounts())
        {
            setHighScore(a.name, a.getPlayer().getShip().getScore(),game.getMode());
        }

        resetGame = true;

        MenuScreen mainmenu = Managers.getScreenManager().getScreen("endgamemenu");
        Managers.getScreenManager().setScreen(mainmenu);
    }

    public void setHighScore(String Playername,Integer Score,String Gamemode)
    {
        System.out.println("this is our score"+Score);
        SaveScoreThread = new SaveScoreToDBThread(Playername,Score,Gamemode);
        SaveScoreThread.start();

    }
    public void update()
    {
        if(game instanceof iGame)
            ((iGame)game).update();


        updateManagers();

        switch (game.getGameState())
        {
            case GAMELOAD: {
                if (Managers.getEnemyManager().getProfiles() != null) {
                    game.setGameState(GAMESTATE.GAMEPLAYING);
                }
                break;
            }
            case GAMESPAWNINGWAVE:{
                if(!Managers.getLevelManager().isSpawning)
                    Managers.getLevelManager().newWave();

                break;
            }
            case GAMEPLAYING:{
                if(Managers.getEnemyManager().getEnemies().size() == 0)
                {
                    game.setGameState(GAMESTATE.GAMESPAWNINGWAVE);
                }
            }
        }
    }
    public void updateManagers()
    {
        Managers.getControllerManager().update();
        Managers.getGeomManager().update();
        Managers.getBulletManager().update();
        Managers.getEnemyManager().update();
        Managers.getLevelManager().update();
        Managers.getpowerUpManager().update();
        Managers.getPlayerManager().update();
        Managers.getCollisionManager().update();
    }
    public void newGame()
    {
        ArcadeSoloGame game = new ArcadeSoloGame();
        Managers.getGameManager().newGame(game);

        MenuScreen nextMenu = Managers.getScreenManager().getScreen("game");
        Managers.getScreenManager().setScreen(nextMenu);
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
        game = null;
        resetGame = false;
    }
}
