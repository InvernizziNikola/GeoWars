package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.gameobjects.Enemy;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.playerobjects.Account;

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
            if(!b.isFriendly()) {
                for (Account p : Managers.getAccountManager().getAccounts()) {

                    Vector2 playerPos = p.getProfile().getShip().getPosition();
                    Vector2 distance = new Vector2(b.getPosition().x - playerPos.x, b.getPosition().y - playerPos.y);

                    if(distance.len() < 25) {

                        Managers.getBulletManager().remove(b);
                        System.out.println("Profile hit by enemy bullet");
                    }
                }
            }
            else
            {
                for (Enemy e : Managers.getEnemyManager().getEnemies()) {
                    Vector2 distance = new Vector2(b.getPosition().x - e.getPosition().x, b.getPosition().y - e.getPosition().y);
                    if(distance.len() < 25 && !e.destroy &&!b.destroy)
                    {
                        e.handleDead();
                        Managers.getEnemyManager().remove(e);
                        Managers.getBulletManager().remove(b);
                        break;
                    }
                }
            }
        }
        for (Account p : Managers.getAccountManager().getAccounts()) {
            Vector2 playerPos = p.getProfile().getShip().getPosition();

            for (Geom g : Managers.getGeomManager().getGeomList()) {
                Vector2 distance = new Vector2(playerPos.x - g.getPosition().x, playerPos.y - g.getPosition().y);
                if (distance.len() < 25 && !g.destroy) {

                    p.getProfile().getShip().handlePickedUp(g);
                    Managers.getGeomManager().removeGeom(g);
                }
            }

            for(Enemy e: Managers.getEnemyManager().getEnemies())
            {
                Vector2 distance = new Vector2(playerPos.x - e.getPosition().x, playerPos.y - e.getPosition().y);
                if (distance.len() < 25) {

                    p.getProfile().getShip().setDead();
                    //Screen mainmenu = Managers.getMenuManager().getScreen("mainmenu");
                    //Managers.getMenuManager().setScreen(mainmenu);
                }
            }
        }
    }
}
