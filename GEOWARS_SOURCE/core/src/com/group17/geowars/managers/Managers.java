package com.group17.geowars.managers;


/**
 * Created by nikola on 09/11/2016.
 */

public class Managers {
    private static Managers _instance;

    private static Managers getInstance()
    {
        if(_instance == null) {
            _instance = new Managers();
            _instance.init();
        }
        return _instance;
    }
    private GameManager gameManager;
    public static GameManager getGameManager(){

        return getInstance().gameManager;
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

    private ScreenManager screenManager;
    public static ScreenManager getScreenManager(){

        return  getInstance().screenManager;
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


    //PowerUpManager
    private PowerUpManager powerUpManager;
    public static PowerUpManager getpowerUpManager(){

        return  getInstance().powerUpManager;
    }


    private PlayerManager playerManager;
    public static PlayerManager getPlayerManager(){

        return  getInstance().playerManager;
    }

    // playerManager


    private Managers()
    {
        playerManager = new PlayerManager();
        assetManager = new AssetManager();
        controllerManager = new ControllerManager();
        levelManager = new LevelManager();
        scoreManager = new ScoreManager();
        bulletManager = new BulletManager();
        enemyManager = new EnemyManager();
        geomManager = new GeomManager();
        accountManager = new AccountManager();
        collisionManager = new CollisionManager();
        screenManager = new ScreenManager();
        powerUpManager=new PowerUpManager();
        gameManager = new GameManager();

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
        screenManager.init();
        collisionManager.init();
        powerUpManager.init();
        gameManager.init();
    }

}
