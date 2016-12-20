package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.screens.MenuScreen;
import com.group17.geowars.utils.GAMESTATE;

/**
 * Created by nikola on 10/11/2016.
 */
public class CollisionManager
{

    public CollisionManager()
    {

    }
    public void init()
    {

    }
    public void render(Batch batch)
    {

    }
    public void update()
    {
        for(Bullet b: Managers.getBulletManager().getBullets()){
            if(!b.isFriendly() && !b.destroy) {
                for (Account p : Managers.getAccountManager().getAccounts()) {

                    Vector2 playerPos = p.getPlayer().getShip().getPosition();
                    Vector2 distance = new Vector2(b.getPosition().x - playerPos.x, b.getPosition().y - playerPos.y);

                    if(distance.len() < 25) {

                        Managers.getBulletManager().remove(b);
                        System.out.println("Player hit by enemy bullet");
                    }
                }
            }
            else
            {
                for (Enemy e : Managers.getEnemyManager().getEnemies()) {
                    Vector2 distance = new Vector2(b.getPosition().x - e.getPosition().x, b.getPosition().y - e.getPosition().y);
                    if(distance.len() < e.getSize()/2 && !e.destroy &&!b.destroy)
                    {
                       // System.out.println(distance);
                        e.handleDead(e,b);
                      //  Managers.getEnemyManager().remove(e);
                       // Managers.getBulletManager().remove(b);
                        break;
                    }
                }
            }
        }
        for (Account p : Managers.getAccountManager().getAccounts()) {
            Vector2 playerPos = p.getPlayer().getShip().getPosition();

            for (Geom g : Managers.getGeomManager().getGeomList()) {
                Vector2 distance = new Vector2(playerPos.x - g.getPosition().x, playerPos.y - g.getPosition().y);
                if (distance.len() < 25 && !g.destroy) {

                    p.getPlayer().getShip().handlePickedUp(g);
                    Managers.getGeomManager().removeGeom(g);
                }
            }

            for (PowerUp g : Managers.getpowerUpManager().getPowerUpList()) {
                Vector2 distance = new Vector2(playerPos.x - g.getPosition().x, playerPos.y - g.getPosition().y);
                if (distance.len() < 25 && !g.destroy) {

                    p.getPlayer().getShip().handlePickedUp(g);
                    Managers.getpowerUpManager().removePowerUp(g);
                }
            }

            for(Enemy e: Managers.getEnemyManager().getEnemies())
            {
                Vector2 distance = new Vector2(playerPos.x - e.getPosition().x, playerPos.y - e.getPosition().y);
                if (distance.len() < 25) {

                    p.getPlayer().getShip().setDead();

                    Managers.getGameManager().setEndScore(p.getPlayer().getShip().getScore());
                    Managers.getGameManager().gameState = GAMESTATE.MENU;
                    Managers.getGameManager().resetGame();

                    MenuScreen mainmenu = Managers.getScreenManager().getScreen("endgamemenu");
                    Managers.getScreenManager().setScreen(mainmenu);
                }
            }
        }
    }
}
