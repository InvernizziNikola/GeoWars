package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.database.Threads.DifficultyThread;
import com.group17.geowars.database.Threads.LoadEnemyThread;
import com.group17.geowars.database.Threads.SaveScoreToDBThread;
import com.group17.geowars.gamemodes.ArcadeCoopGame;
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
    private BaseGame game;
    public BaseGame getGame()
    {
        return game;
    }
    private boolean resetGame = false;
    private SaveScoreToDBThread SaveScoreThread;
    private int score = 0;
    private float difficultyModifier = -1.0f;
    private boolean isDifficultySet = false;
    public void setDifficultyModifier(float dm)
    {
        LET = new LoadEnemyThread();
        LET.start();

        difficultyModifier = dm;
        isDifficultySet = true;
    }
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

    public void startThreads(String difficulty)
    {
        isDifficultySet = false;
        DT = new DifficultyThread(difficulty);
        DT.start();

        Managers.getEnemyManager().resetProfiles();
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

    private void loadGame()
    {
        loadTimer-= Gdx.graphics.getDeltaTime();

        if (DT != null && DT.finished()) {
            difficultyModifier = DT.getDifficultyModifier();
            DT = null;
            isDifficultySet = true;
        }
        if (LET != null && LET.finished()) {
            Managers.getEnemyManager().setProfiles(LET.getEnemies());
            LET = null;
        }

        if((loadTimer < 0) && (!isDifficultySet || Managers.getEnemyManager().getProfiles() == null))
        {
            //TODO fallback when no internet
            // uess this isnt implemented :'( Srry Dirk :(
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
                loadGame();
                if (Managers.getEnemyManager().getProfiles() != null && isDifficultySet) {
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
                    if(Managers.getLevelManager().getCurrentwave() == 10)
                    {
                        game.setGameState(GAMESTATE.GAMEMENU);
                        MenuScreen upgradeMenu = Managers.getScreenManager().getScreen("upgrademenuingame");
                        Managers.getScreenManager().setScreen(upgradeMenu);
                    }else {
                        game.setGameState(GAMESTATE.GAMESPAWNINGWAVE);
                    }
                }
                break;
            }
            case GAMEMENU:{
                break;
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
        setDifficultyModifier(1.0f);
        ArcadeCoopGame game = new ArcadeCoopGame();
        Managers.getGameManager().newGame(game);
    }
    public void newArcadeSoloGame()
    {
        ArcadeSoloGame game = new ArcadeSoloGame();
        Managers.getGameManager().newGame(game);
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
