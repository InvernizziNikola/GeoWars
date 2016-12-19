package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

/**
 * Created by nikola on 06/12/2016.
 */


/*
* -------------HOW TO GET THE ARRAYLIST-------------

     private HighScoreMenuThread hsT;
  public void getHighScore(String gameMode)
    {
        hsT = new HighScoreMenuThread(gameMode);
        hsT.start();
    }
*
* */
public class ProfileThread implements Runnable {


    private ArrayList PlayerProfile = null;
    private ArrayList PlayerHighscore = null;
    private String Name = "";
    private Thread t;

    public ProfileThread(String Name)
    {
        this.Name = Name;
    }

    public void start()
    {
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }

    @Override
    public void run() {
        DBManager manager = new DBManager();

        PlayerProfile = manager.DBselectProfile(Name);
        PlayerHighscore = manager.DBselectPlayersHighscore(Name);
    }

    public ArrayList getPlayerProfile()
    {
        return PlayerProfile;
    }
    public ArrayList getPlayerHighscore()
    {
        return PlayerHighscore;
    }

    public boolean finished()
    {
        if(PlayerProfile != null)
            return true;
        return false;
    }
}
