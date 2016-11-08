package com.group17.geowars.managers;

import com.group17.geowars.GameObjects.GameObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nikola on 08/11/2016.
 */
public class GameObjectManager
{

    private static GameObjectManager _instance;

    public static GameObjectManager GetInstance()
    {
        if(_instance == null)
            _instance = new GameObjectManager();

        return _instance;
    }

    private ArrayList<GameObject> gameObjects;

    private GameObjectManager()
    {
        gameObjects = new ArrayList<GameObject>();
    }

    public void addGameObject(GameObject go)
    {
        gameObjects.add(go);
    }

    public void update()
    {
        //for(int i = 0; i < gameObjects.size(); i++)
        //   gameObjects.get(i).render();
    }

    public void render()
    {

    }

}
