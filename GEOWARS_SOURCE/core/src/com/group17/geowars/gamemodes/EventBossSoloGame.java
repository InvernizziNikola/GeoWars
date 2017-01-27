package com.group17.geowars.gamemodes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gamemodes.base.SoloGame;
import com.group17.geowars.gamemodes.base.iGame;
import com.group17.geowars.gameobjects.playerObjects.*;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Account;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.ENEMYTYPE;

/**
 * Created by nikola on 21/12/2016.
 */
public class EventBossSoloGame extends SoloGame implements iGame {


    public EventBossSoloGame() {
        super();
        super.mode = "Arcade";
        Account a = Managers.getAccountManager().getAccounts().get(0);
        String name = a.name;
        Controller c = a.getController();
        Player p = Managers.getPlayerManager().createPlayer(name, c);

        a.setPlayer(p);

        Vector2 spawnPoint = new Vector2(GeoWars.WIDTH / 3, GeoWars.HEIGHT / 3);
        Ship s = new DestroyerShip(spawnPoint);
        Drone d = new DefenceDrone(spawnPoint);


        switch (a.profile.getShipType()){
            case ASSAULT:
                s = new AssaultShip(spawnPoint);
            break;
            case DESTROYER:
                s = new DestroyerShip(spawnPoint);
                break;
            case TANK:
                s = new TankShip(spawnPoint);
                break;
            default:
                s = new AssaultShip(spawnPoint);
        }
        switch (a.profile.getDroneType())
        {
            case ATTACK:
                d = new AttackDrone(spawnPoint);
                break;
            case DEFEND:
                d = new DefenceDrone(spawnPoint);
                break;
            case SUPPORT:
                d = new SupportDrone(spawnPoint);
                break;
            default:
                d = new AttackDrone(spawnPoint);
        }


        s.setPlayer(p);
        p.setShip(s);

         // TODO doesnt need account as parameter
        d.setPlayer(p);
        p.setDrone(d);
        placePlayerTextFields();

        createEnemyBossProfile();
    }

    public void createEnemyBossProfile()
    {
        Managers.getEnemyManager().resetProfiles();

        EnemyProfile eProfile = new EnemyProfile();
        eProfile.type = ENEMYTYPE.BOSS;
        eProfile.difficultyGrade = 6;
        //eProfile.imageName = "Dreadnought";
        Managers.getEnemyManager().AddEnemyProfile(eProfile);
    }

    @Override
    public void update() {
    }
}
