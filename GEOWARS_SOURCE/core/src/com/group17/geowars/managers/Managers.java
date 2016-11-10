package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;


/**
 * Created by nikola on 09/11/2016.
 */
public class Managers
{
    private static Managers _instance;

    private static Managers getInstance()
    {
        if(_instance == null) {
            _instance = new Managers();
            _instance.init();
        }
        return _instance;
    }

    private AssetManager assetManager;
    public static AssetManager getAssetManager(){

        return getInstance().assetManager;
    }
    private ControllerManager controllerManager;
    public static ControllerManager getControllerManager(){

        return  getInstance().controllerManager;
    }
    private LevelManager levelManager;
    public static LevelManager getLevelManager(){

        return  getInstance().levelManager;
    }
    private ScoreManager scoreManager;
    public static ScoreManager getScoreManager(){

        return  getInstance().scoreManager;
    }
    private BulletManager bulletManager;
    public static BulletManager getBulletManager(){

        return  getInstance().bulletManager;
    }
    private EnemyManager enemyManager;
    public static EnemyManager getEnemyManager(){

        return  getInstance().enemyManager;
    }
    private GeomManager geomManager;
    public static GeomManager getGeomManager(){

        return  getInstance().geomManager;
    }

    // playerManager
    // geomManager


    private Managers()
    {
        assetManager = new AssetManager();
        controllerManager = new ControllerManager();
        levelManager = new LevelManager();
        scoreManager = new ScoreManager();
        bulletManager = new BulletManager();
        enemyManager = new EnemyManager();
        geomManager = new GeomManager();
    }

    public void init()
    {
        assetManager.init();
        controllerManager.init();
        levelManager.init();
        scoreManager.init();
        bulletManager.init();
        enemyManager.init();
        geomManager.init();
    }
    public void render(Batch batch)
    {
        geomManager.render(batch);
        bulletManager.render(batch);
        enemyManager.render(batch);
        levelManager.render(batch);
    }
    public void update()
    {
        geomManager.update();
        bulletManager.update();
        enemyManager.update();
        levelManager.update();
    }

}
