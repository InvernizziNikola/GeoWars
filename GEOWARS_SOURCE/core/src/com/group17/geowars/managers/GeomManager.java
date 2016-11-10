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
    private List<Geom> geomsToRemove;

    public GeomManager()
    {
        geomList = new ArrayList<Geom>();
        geomsToRemove = new ArrayList<Geom>();

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

        for (Geom aGeomList : geomList)
        {aGeomList.update();}
        geomList.removeAll(geomsToRemove);
    }

    public void removeGeom(Geom geom) {

        geomsToRemove.add(geom);
    }

    public void removeGeomOnPos(Vector2 pos) {

    }

    public List<Geom> getGeomList() {
        return geomList;
    }
}
