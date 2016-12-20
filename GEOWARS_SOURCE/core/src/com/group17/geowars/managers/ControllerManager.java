package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.group17.geowars.playerobjects.Account;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikola ;D on 9/11/2016.
 */

public class ControllerManager {

    private List<Controller> controllersList;
    public List<Controller> getControllers()
    {
        return controllersList;
    }

    public ControllerManager()
    {
        controllersList = new ArrayList<Controller>();
        for(Controller c : Controllers.getControllers())
        {
            if(!controllersList.contains(c))
                controllersList.add(c);
        }
    }

    public Controller getUnusedController()
    {

        for(Controller c : controllersList)
        {
            if(Managers.getAccountManager().getAccountByController(c) == null)
            {
                return c;
            }
        }

        return null;
    }

    public void init()
    {

    }

    public void update()
    {
    }
}
