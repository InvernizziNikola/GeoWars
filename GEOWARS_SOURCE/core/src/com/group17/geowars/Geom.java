package com.group17.geowars;

/**
 * Created by nikola on 07/11/2016.
 */




public class Geom
{
    public EnemyLoot loot;

    private Geom()
    {
        //super of dynamicobject
    }

    public Geom(int enemyID)
    {
        // super of dynamic object
        setGeomData(enemyID);
    }

    public void setGeomData(int enemyId)
    {
        loot = ScoreManager.GetInstance().getLoot(enemyId);
    }

    //override
    public void Render()
    {
        // TODO DRAW IMAGE
    }
    //override
    public void Update()
    {
        // TODO MOVEMENT
    }

}
