package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
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


    public void init()
    {
    }
    public void update()
    {
        for(Controller c : Controllers.getControllers())
        {
            System.out.println(c);
        }
    }

}
