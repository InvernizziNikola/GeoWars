package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

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
    Integer i =0;
    public boolean finished()
    {
        if(data != null||i>300)
            return true;
        i++;
        return false;
    }
}
