package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.database.Threads.DifficultyThread;
import com.group17.geowars.database.Threads.LoadEnemyThread;
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


    private float loadTimer = 10.0f;
    private boolean isLoading = false;
    public BaseGame game;
    private boolean resetGame = false;
    private SaveScoreToDBThread SaveScoreThread;
    private int score = 0;
    public Float difficultyModifier = -1.0f;
    public void setEndScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }
    private DifficultyThread DT;
    private LoadEnemyThread LET;
    public void newGame(BaseGame game)
    {
        this.game = game;
    }

    public void setGameState(GAMESTATE gs)
    {
        game.setGameState(gs);
    }

    public void setDifficulty(String difficulty)
    {
        DT = new DifficultyThread(difficulty);
        DT.start();

        LET = new LoadEnemyThread();
        LET.start();
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
        SaveScoreThread = new SaveScoreToDBThread(Playername,Score,Gamemode);
        SaveScoreThread.start();
    }
    public Float getDifficultyModifier(){
        return difficultyModifier;
    }

    private void gameLoad()
    {
        if (DT != null && DT.finished()) {
            difficultyModifier = DT.getDifficultyModifier();
            DT = null;
        }
        if (LET != null && LET.finished()) {
            Managers.getEnemyManager().setProfiles(LET.getEnemies());
            LET = null;
        }
        if((isLoading && loadTimer < 0) && (difficultyModifier == -1 || Managers.getEnemyManager().getProfiles() == null))
        {
            System.out.println("GET HARDCODED PROFILES OF DIFFICULTY");
            // guess this isnt implemented :/
        }

    }
    public void update()
    {
        if(game instanceof iGame)
            ((iGame)game).update();


        updateManagers();

        switch (game.getGameState())
        {
            case GAMELOAD: {
                gameLoad();
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
        Managers.getFloatingTextManager().update();
    }
    public void newArcadeCoopGame()
    {

    }
    public void newArcadeSoloGame()
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
        Managers.getFloatingTextManager().render(batch);
    }

    public void resetGame()
    {
        Managers.getpowerUpManager().reset();
        Managers.getBulletManager().reset();
        Managers.getEnemyManager().reset();
        Managers.getGeomManager().reset();
        Managers.getLevelManager().reset();
        Managers.getPlayerManager().reset();
        Managers.getFloatingTextManager().reset();
        game = null;
        resetGame = false;
    }
}
