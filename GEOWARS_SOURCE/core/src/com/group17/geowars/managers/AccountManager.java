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

    private boolean loggedIn = false;
    public boolean getLoggedIn()
    {
        return loggedIn;
    }
    public void setLoggedIn()
    {
        loggedIn = true;
    }
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
        createDummyAccount().main = true;
    }

    public void removeNonMainAccounts()
    {
        List<Account> toRemove = new ArrayList<Account>();
        for(Account a : accounts)
        {
            if(a.main)
                toRemove.add(a);
        }
        accounts.remove(toRemove);
    }
    public void removeAccount(Account a)
    {
        accounts.remove(a);
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
        dummyAccount = new Account("Player 1");
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

}
