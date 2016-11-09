package com.group17.geowars.managers;

import com.badlogic.gdx.controllers.Controller;
import com.group17.geowars.gameobjects.Enemy;


import java.util.Set;

/**
 * Created by kevin on 9/11/2016.
 */
public class ControllerManager {

    private static ControllerManager _instance;

    public static ControllerManager GetInstance()
    {
        if(_instance == null)
            _instance = new ControllerManager();

        return _instance;
    }

    private Set<Controller> controllers;

}
