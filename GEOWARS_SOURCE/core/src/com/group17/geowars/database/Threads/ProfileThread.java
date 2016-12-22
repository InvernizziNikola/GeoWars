package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class ProfileThread implements Runnable {


    private ArrayList PlayerProfile = null;
    private ArrayList PlayerHighscoreTemp = null;
    private Integer PlayerHighscore = null;
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

        PlayerProfile = DBManager.getInstance().DBselectProfile(Name);
        PlayerHighscoreTemp = DBManager.getInstance().DBselectPlayersHighscore(Name);
        if (PlayerHighscoreTemp==null){
            PlayerHighscore = 0;

        }else {
            PlayerHighscore = Integer.parseInt(PlayerHighscoreTemp.get(0).toString());
        }
    }

    public ArrayList getPlayerProfile()
    {
        return PlayerProfile;
    }
    public Integer getPlayerHighscore()
    {
        System.out.println("highscore"+PlayerHighscore);
        return PlayerHighscore;
    }
    public boolean finished()
    {
        if(PlayerProfile != null&&PlayerHighscore!=null) {
            System.out.println("im done!");
            return true;
        }
        return false;
    }
}
