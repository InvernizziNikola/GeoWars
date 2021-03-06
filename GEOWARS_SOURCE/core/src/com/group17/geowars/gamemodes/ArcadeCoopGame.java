package com.group17.geowars.gamemodes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gamemodes.base.SoloGame;
import com.group17.geowars.gamemodes.base.iGame;
import com.group17.geowars.gameobjects.playerObjects.*;
import com.group17.geowars.managers.AccountManager;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.ENEMYTYPE;

import java.util.ArrayList;

/**
 * Created by nikola on 21/12/2016.
 */
public class ArcadeCoopGame extends SoloGame implements iGame {


    public ArcadeCoopGame() {
        super.mode = "Arcade Co-op";

        int count = 0;
        for(Account a : Managers.getAccountManager().getAccounts()) {
            String name = a.name;
            Controller c = a.getController();
            Player p = Managers.getPlayerManager().createPlayer(name, c);

            a.setPlayer(p);

            Vector2 spawnPoint = new Vector2(GeoWars.WIDTH / 3 , GeoWars.HEIGHT / 2 - 100 * count);

            Ship s = new DestroyerShip(spawnPoint);
            //HANDLE UPGRADES
            s.upgradeFireRate(a.getFirePowerUpgrade());
            s.upgradeMaxHp(a.getHpUpgrade());
            s.upgradeSpeed(a.getSpeedPowerUpgrade());



            p.setShip(s);
            s.setPlayer(p);

            Drone d = new DefenceDrone(spawnPoint); // TODO doesnt need account as parameter
            p.setDrone(d);
            d.setPlayer(p);
            count++;



        }
        placePlayerTextFields();

        createEnemyProfile();
    }

    @Override
    public void update() {
    }
}
