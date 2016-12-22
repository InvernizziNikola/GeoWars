package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

/**
 * Created by nikola on 06/12/2016.
 */


/*
* -------------HOW TO GET THE ARRAYLIST-------------

     private HighScoreMenuThread hsT;
  public void getHighScore(String difficulty)
    {
        hsT = new HighScoreMenuThread(difficulty);
        hsT.start();
    }
*
* */
public class DifficultyThread implements Runnable {

    private ArrayList data = null;
    private String difficulty = "";
    private Thread t;
    private Integer difficultyModifier;

    public DifficultyThread(String Difficutly)
    {
        this.difficulty = difficulty;
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

        data = DBManager.getInstance().DBSelectDifficulty(difficulty);
    }

    public Integer getDifficultyModifier()
    {

        difficultyModifier = Integer.parseInt(data.get(0).toString());
        return difficultyModifier;
    }

    public boolean finished()
    {
        if(data != null)
            return true;
        return false;
    }
}
