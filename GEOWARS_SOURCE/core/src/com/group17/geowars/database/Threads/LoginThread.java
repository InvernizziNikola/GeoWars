package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class LoginThread implements Runnable {


    private ArrayList Player = null;
    private String Name = "";
    private String Password = "";
    private Thread t;

    public LoginThread(String Name, String Password) {
        this.Name = Name;
        this.Password = Password;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {


        Player = DBManager.getInstance().DBselectLogin(Name, Password);
        System.out.println(Player);
    }

    public Boolean getLoggedIn() {
        if (Player != null) {
            if (Player.size() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    Integer timer = 0;

    public boolean finished() {
        if (Player != null || timer > 300)
            return true;
        timer++;
        System.out.println(timer);
        return false;
    }
}
