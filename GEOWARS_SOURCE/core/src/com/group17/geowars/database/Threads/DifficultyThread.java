package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class DifficultyThread implements Runnable {

    private ArrayList data = null;
    private String difficulty = null;
    private Thread t;
    private float difficultyModifier;

    public DifficultyThread(String difficulty) {
        this.difficulty = difficulty;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        data = DBManager.getInstance().DBSelectDifficulty(difficulty);
    }

    public Float getDifficultyModifier() {
        if (data != null) {

            difficultyModifier = Float.parseFloat(data.get(0).toString());
        }
        return difficultyModifier;
    }

    Integer timer = 0;

    public boolean finished() {
        if (data != null || timer > 300) {
            System.out.println("timer");
            return true;
        } else {
            timer++;
            return false;
        }
    }
}
