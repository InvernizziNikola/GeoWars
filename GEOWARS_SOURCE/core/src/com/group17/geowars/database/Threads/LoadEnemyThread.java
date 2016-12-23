package com.group17.geowars.database.Threads;

import com.group17.geowars.DataObjects.EnemyProfile;
import com.group17.geowars.database.DBManager;
import com.group17.geowars.utils.ENEMYTYPE;

import java.util.ArrayList;

public class LoadEnemyThread implements Runnable {

    private ArrayList<EnemyProfile> enemyProfiles = null;
    private ArrayList BuildedSingleArray = null;
    private ArrayList enemy = null;
    private String Name = null;
    private Integer rows;
    private Thread t;

    public LoadEnemyThread(String Name) {
        this.Name = Name;
    }

    public LoadEnemyThread() {
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        if (Name != null) {
            enemy = DBManager.getInstance().DBselectEnemy(Name);
        } else {
            enemy = DBManager.getInstance().DBSelectAllEnemys();
        }

        enemyProfiles = new ArrayList<EnemyProfile>();

        Integer CollumCount = 12;
        rows = enemy.size() / CollumCount;

        for (int i = 0; i < rows; i++) {
            EnemyProfile ep = new EnemyProfile();

            ep.name = enemy.get((i * CollumCount) + 0).toString();
            ep.type = ENEMYTYPE.valueOf(enemy.get((i * CollumCount) + 1).toString().toUpperCase());
            ep.imageName = enemy.get((i * CollumCount) + 2).toString();
            ep.health = Integer.parseInt(enemy.get((i * CollumCount) + 3).toString());
            ep.fireDelay = Integer.parseInt(enemy.get((i * CollumCount) + 4).toString());
            ep.fireRange = Integer.parseInt(enemy.get((i * CollumCount) + 5).toString());
            ep.speed = Integer.parseInt(enemy.get((i * CollumCount) + 6).toString());
            ep.difficultyGrade = Integer.parseInt(enemy.get((i * CollumCount) + 7).toString());
            ep.Spread = Integer.parseInt(enemy.get((i * CollumCount) + 8).toString());
            ep.red = Integer.parseInt(enemy.get((i * CollumCount) + 9).toString());
            ep.green = Integer.parseInt(enemy.get((i * CollumCount) + 10).toString());
            ep.blue = Integer.parseInt(enemy.get((i * CollumCount) + 11).toString());
            enemyProfiles.add(ep);
        }
    }

    public ArrayList getEnemies() {
        return enemyProfiles;
    }

    Integer timer = 0;

    public boolean finished() {
        if (enemyProfiles != null || timer > 300)
            return true;
        timer++;
        return false;
    }
}
