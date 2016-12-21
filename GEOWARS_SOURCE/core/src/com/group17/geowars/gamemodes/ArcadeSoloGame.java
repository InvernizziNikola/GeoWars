package com.group17.geowars.gamemodes;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.gamemodes.base.SoloGame;
import com.group17.geowars.gamemodes.base.iGame;
import com.group17.geowars.gameobjects.playerObjects.*;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;

/**
 * Created by nikola on 21/12/2016.
 */
public class ArcadeSoloGame extends SoloGame implements iGame {


    public ArcadeSoloGame() {
        super.modeName = "Arcade";

        String name = Managers.getAccountManager().getAccounts().get(0).name;
        Controller c = Managers.getAccountManager().getAccounts().get(0).getController();
        Player p = Managers.getPlayerManager().createPlayer(name, c);

        Vector2 spawnPoint = new Vector2(GeoWars.WIDTH/3,GeoWars.HEIGHT/3);

        Ship s = new DestroyerShip(spawnPoint);
        s.setPlayer(p);
        p.setShip(s);

        Drone d = new AttackDrone(spawnPoint); // TODO doesnt need accoutn as parameter
        d.setPlayer(p);
        p.setDrone(d);

    }

    @Override
    public void update() {
    }
}
