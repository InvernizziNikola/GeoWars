package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.gameobjects.GOInterface;
import com.group17.geowars.gameobjects.Geom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 10/11/2016.
 */
public class GeomManager implements GOInterface {

    private List<Geom> geomList;
    private List<Geom> toRemove;



    public GeomManager()
    {
        geomList = new ArrayList<Geom>();
        toRemove = new ArrayList<Geom>();

        // GET LOOTTABLE FROM DATABASE AND INSERT IN HASHTABLE
        // HACK
    }

    public void init()
    {

    }

    public void addGeom(Geom geom)
    {
        geomList.add(geom);
    }

    @Override
    public void render(Batch batch) {
        for (Geom aGeomList : geomList) aGeomList.render(batch);
    }

    @Override
    public void update() {

        for (Geom geom : geomList)
        {
            geom.update();
        }

        geomList.removeAll(toRemove);
    }

    public void removeGeom(Geom g) {

        toRemove.add(g);
        g.destroy = true;
    }

    public void removeGeomOnPos(Vector2 pos) {

    }

    public List<Geom> getGeomList() {
        return geomList;
    }
}
