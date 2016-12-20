package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.playerobjects.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikola on 10/11/2016.
 */
public class AccountManager {

    private Account dummyAccount;
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
        createDummyAccount();
    }


    public Account createAccount(String username)
    {
        if(dummyAccount != null) {
            accounts.remove(dummyAccount);
            dummyAccount = null;
        }

        Account newAcc = new Account(username);
        if(Managers.getControllerManager().getUnusedControllers().size() > 0)
            newAcc.setController(Managers.getControllerManager().getUnusedControllers().get(0));

        accounts.add(newAcc);

        return newAcc;
    }

    public Account createDummyAccount()
    {
        dummyAccount = new Account("Guest");
        if(Managers.getControllerManager().getUnusedControllers().size() > 0)
            dummyAccount.setController(Managers.getControllerManager().getUnusedControllers().get(0));

        accounts.add(dummyAccount);

        return dummyAccount;
    }

    public Account getAccountByController(Controller c)
    {
        for(Account a : accounts)
        {
            if(a.getController() == c)
            {
                return a;
            }
        }

        return null;
    }

    public Account getDummyAccount()
    {
        return dummyAccount;
    }

    public void update()
    {
        /*for (Account p: accounts) {
            p.getPlayer().update();
        }*/
    }
    public void render(Batch batch)
    {
        /*for (Account p: accounts) {
            p.getPlayer().render(batch);
        }*/
    }
    public void reset()
    {
        // TODO

        /*for (Account a: accounts) {
            a.reset();
        }*/
    }
}
