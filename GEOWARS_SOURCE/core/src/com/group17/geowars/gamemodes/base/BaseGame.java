package com.group17.geowars.gamemodes.base;

import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.utils.ENEMYTYPE;
import com.group17.geowars.utils.GAMESTATE;

/**
 * Created by nikola on 21/12/2016.
 */
public class BaseGame {

    protected String mode = "";
    protected GAMESTATE gameState = GAMESTATE.GAMELOAD;

    public GAMESTATE getGameState()
    {
        return gameState;
    }
    public void setGameState(GAMESTATE gs)
    {
        gameState = gs;
    }
    public String getMode()
    {
        return mode;
    }
    public BaseGame()
    {



    }

    public void placePlayerTextFields()
    {
        int y=30;
        for (Player p: Managers.getPlayerManager().getPlayers()) {
            p.setPlayerTextpos(new Vector2(10,y));
            y+=50;
        }
    }

    public void createEnemyProfile()
    {
        Managers.getEnemyManager().resetProfiles();

        EnemyProfile eProfile = new EnemyProfile();
        eProfile.type = ENEMYTYPE.DREADNOUGHT;
        //eProfile.imageName = "Dreadnought";
        Managers.getEnemyManager().AddEnemyProfile(eProfile);

        eProfile = new EnemyProfile();
        eProfile.type = ENEMYTYPE.SCOUT;
        //eProfile.imageName = "Dreadnought";
        Managers.getEnemyManager().AddEnemyProfile(eProfile);

        eProfile = new EnemyProfile();
        eProfile.type = ENEMYTYPE.KAMIKAZE;
        //eProfile.imageName = "Dreadnought";
        Managers.getEnemyManager().AddEnemyProfile(eProfile);
    }

}
