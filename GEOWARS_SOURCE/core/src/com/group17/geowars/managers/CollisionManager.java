package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.hostileObjects.Enemy;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.PowerUps.PowerUp;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;
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
    public void update()
    {

        for(Bullet b: Managers.getBulletManager().getBullets()){
            if(!b.isFriendly() && !b.destroy) {
                for (Player p : Managers.getPlayerManager().getPlayers()) {

                    Vector2 playerPos = p.getShip().getPosition();
                    Vector2 distance = new Vector2(b.getPosition().x - playerPos.x, b.getPosition().y - playerPos.y);

                    if(distance.len() < 25) {
                        Managers.getBulletManager().remove(b);
                        p.getShip().handleHit(b.getDamage());
                    }
                }
            }
            else
            {
                for (Enemy e : Managers.getEnemyManager().getEnemies()) {
                    Vector2 distance = new Vector2(b.getPosition().x - e.getPosition().x, b.getPosition().y - e.getPosition().y);
                    if(distance.len() < e.getSize()/2 && !e.destroy &&!b.destroy)
                    {
                        e.handleDead(e);
                        Managers.getBulletManager().remove(b);

                        break;
                    }
                }
            }
        }
        for (Player p : Managers.getPlayerManager().getPlayers()) {
            Vector2 playerPos = p.getShip().getPosition();

            for (Geom g : Managers.getGeomManager().getGeomList()) {
                Vector2 distance = new Vector2(playerPos.x - g.getPosition().x, playerPos.y - g.getPosition().y);
                if (distance.len() < 25 && !g.destroy) {

                    p.getShip().handlePickedUp(g);
                    Managers.getGeomManager().removeGeom(g);
                }
            }

            for (PowerUp g : Managers.getpowerUpManager().getPowerUpList()) {
                Vector2 distance = new Vector2(playerPos.x - g.getPosition().x, playerPos.y - g.getPosition().y);
                if (distance.len() < 25 && !g.isDestroy()) {

                    p.getShip().handlePickedUp(g);
                    Managers.getpowerUpManager().removePowerUp(g);
                }
            }

            for(Enemy e: Managers.getEnemyManager().getEnemies())
            {
                Vector2 distance = new Vector2(playerPos.x - e.getPosition().x, playerPos.y - e.getPosition().y);
                if (distance.len() < 25) {

                    p.getShip().handleEnemyCrash(e);


                    //Managers.getGameManager().setEndScore(p.getShip().getScore());
                   // Managers.getGameManager().gameState = GAMESTATE.MENU;


                }
            }
        }
    }
}
