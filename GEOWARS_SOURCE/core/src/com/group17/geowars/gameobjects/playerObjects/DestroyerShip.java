package com.group17.geowars.gameobjects.playerObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.Bullet;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 21/12/2016.
 */
public class DestroyerShip extends Ship {
    public DestroyerShip(Vector2 pos) {
        super(pos,"Destroyer");
        maxHp=100;
        hp=maxHp;
        fireDelay =0.25f;
        speed=375;

    }
Integer i = 0;
    @Override
    public void shoot()
    {
        if(canShoot) {
            /*if (i==0) {
                sound.play(0.2f);
                i++;
            }else if(i==1){
                sound.play(0.17f);
                i++;
            }else {
                sound.play(0.14f);
                i=0;
            }
*/
            Vector2 sd = new Vector2(shootDir.x, shootDir.y);

            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));
            sd.rotate(-10);
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));
            sd.rotate(20);
            Managers.getBulletManager().addBullet(new Bullet(new Vector2(position), new Vector2(sd)));

            canShoot = false;
        }
    }
}
