package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 10/11/2016.
 */
public class AccountManager {


    private List<Account> accounts;
    public List<Account> getAccounts()
    {
        return accounts;
    }

    public AccountManager()
    {
        accounts = new ArrayList<Account>();
    }

    public void addAccount(Account p)
    {
        accounts.add(p);
    }

    public void init()
    {
    }

    public void update()
    {
        for (Account p: accounts) {
            p.getPlayer().update();
        }
    }
    public void render(Batch batch)
    {
        for (Account p: accounts) {
            p.getPlayer().render(batch);
        }
    }
    public void reset()
    {
        // TODO

        for (Account a: accounts) {
            a.reset();
        }
    }
}
