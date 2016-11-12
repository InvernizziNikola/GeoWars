package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
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

    private AccountManager accountManager;
    public static AccountManager getAccountManager(){

        return getInstance().accountManager;
    }

    private AssetManager assetManager;
    public static AssetManager getAssetManager(){

        return getInstance().assetManager;
    }
    private ControllerManager controllerManager;
    public static ControllerManager getControllerManager(){

        return  getInstance().controllerManager;
    }

    private MenuManager menuManager;
    public static MenuManager getMenuManager(){

        return  getInstance().menuManager;
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

    private CollisionManager collisionManager;
    public static CollisionManager getCollisionManager(){

        return  getInstance().collisionManager;
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
        accountManager = new AccountManager();
        collisionManager = new CollisionManager();
        menuManager = new MenuManager();
    }

    public void init()
    {
        accountManager.init();
        assetManager.init();
        controllerManager.init();
        levelManager.init();
        scoreManager.init();
        bulletManager.init();
        enemyManager.init();
        geomManager.init();
        menuManager.init();
        collisionManager.init();
    }
    public static void render(Batch batch)
    {
        batch.begin();
        //getAssetManager().render(batch);
        getAccountManager().render(batch);
        getGeomManager().render(batch);
        getBulletManager().render(batch);
        getEnemyManager().render(batch);
        getLevelManager().render(batch);
        getAccountManager().render(batch);
        getCollisionManager().render(batch);
        //getControllerManager().render(batch);
        batch.end();
        getMenuManager().render(Gdx.graphics.getDeltaTime());
    }
    public static void update()
    {

        //getAssetManager().update();
        //getControllerManager().update();
        getAccountManager().update();
        getGeomManager().update();
        getBulletManager().update();
        getEnemyManager().update();
        getLevelManager().update();
        getCollisionManager().update();
        //getMenuManager().update();
    }

}
