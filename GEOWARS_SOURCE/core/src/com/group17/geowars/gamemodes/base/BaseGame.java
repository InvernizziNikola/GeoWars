package com.group17.geowars.gamemodes.base;

import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Player;
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
    public void setGame(GAMESTATE gs)
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
        int y=20;
        System.out.println("heyyyyy");
        for (Player p: Managers.getPlayerManager().getPlayers()) {
            p.setPlayerTextpos(new Vector2(10,y));
            System.out.println("spelers"+p);
            y+=50;
        }
    }

}
