package com.group17.geowars.database.Threads;

import com.group17.geowars.database.DBManager;

import java.util.ArrayList;

public class RegisterThread implements Runnable {


    private boolean succes = false;
    private String done = null;
    private String Name = "";
    private String Password = "";
    private Thread t;

    public RegisterThread(String Name, String Password)
    {
        this.Name = Name;
        this.Password = Password;
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


        succes = DBManager.getInstance().DBInsertProfile(Name,Password);
        done = "done";

    }

    public Boolean getSucces()
    {
       return succes;
    }

    public boolean finished()
    {
        if(done != null)
            return true;
        return false;
    }
}
