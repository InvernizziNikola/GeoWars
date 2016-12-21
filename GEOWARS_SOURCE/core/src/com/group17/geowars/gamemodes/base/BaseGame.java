package com.group17.geowars.gamemodes.base;

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


}
