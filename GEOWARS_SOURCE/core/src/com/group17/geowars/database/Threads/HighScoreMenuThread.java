package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class HighScoreMenuThread implements Runnable {


    private ArrayList data = null;
    private String gameMode = "";
    private Thread t;

    public HighScoreMenuThread(String gameMode) {
        this.gameMode = gameMode;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {

        data = DBManager.getInstance().DBselectTOP10Highscore(gameMode);
    }

    public ArrayList getData() {
        return data;
    }

    Integer timer = 0;

    public boolean finished() {
        if (data != null || timer > 300)
            return true;
        timer++;
        return false;
    }
}
