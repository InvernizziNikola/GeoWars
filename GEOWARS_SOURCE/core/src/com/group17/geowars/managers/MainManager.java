package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;


/**
 * Created by nikola on 09/11/2016.
 */
public class MainManager
{
    private static MainManager _instance;

    public static MainManager getInstance()
    {
        if(_instance == null) {
            _instance = new MainManager();
            _instance.init();
        }
        return _instance;
    }

    private AssetManager assetManager;
    public AssetManager getAssetManager(){return assetManager;}
    private ControllerManager controllerManager;
    public ControllerManager getControllerManager(){return  controllerManager;}
    private LevelManager levelManager;
    public LevelManager getLevelManager(){return  levelManager;}
    private ScoreManager scoreManager;
    public ScoreManager getScoreManager(){return  scoreManager;}
    private BulletManager bulletManager;
    public BulletManager getBulletManager(){return  bulletManager;}
    private EnemyManager enemyManager;
    public EnemyManager getEnemyManager(){return  enemyManager;}

    // playerManager
    // geomManager


    private MainManager ()
    {

    }
    private void init()
    {
        assetManager = new AssetManager();
        controllerManager = new ControllerManager();
        levelManager = new LevelManager();
        scoreManager = new ScoreManager();
        bulletManager = new BulletManager();
        enemyManager = new EnemyManager();

        // CHANGE TO INITIALIZE
    }
    public void render(Batch batch)
    {
        bulletManager.render(batch);
        enemyManager.render(batch);
        levelManager.render(batch);
    }
    public void update()
    {
        bulletManager.update();
        enemyManager.update();
        levelManager.update();
    }

}
