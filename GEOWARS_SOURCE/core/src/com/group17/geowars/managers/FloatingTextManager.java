package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.gameobjects.FloatingText;
import com.group17.geowars.gameobjects.GOInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */

public class FloatingTextManager implements GOInterface {

    private List<FloatingText> textList;
    private List<FloatingText> toRemove;
    private List<FloatingText> toAdd;
    public FloatingTextManager () {

        textList = new LinkedList<FloatingText>();
        toRemove = new LinkedList<FloatingText>();
        toAdd = new LinkedList<FloatingText>();
    }

    public void init()
    {

    }
    public void addText(FloatingText text)
    {
        toAdd.add(text);

    }

    @Override
    public void render(Batch batch) {
        for (FloatingText b: textList) {

            b.render(batch);

        }
    }

    @Override
    public void update() {
        for (FloatingText b: textList) {
            b.update();
        }

        textList.removeAll(toRemove);
        toRemove.clear();
        textList.addAll(toAdd);
        toAdd.clear();
    }

    public void reset()
    {
        textList.clear();
        toRemove.clear();
        toAdd.clear();
    }

    public void clearAll() {
        textList.clear();
    }

    public void remove(FloatingText b)
    {
        b.destroy = true;
        toRemove.add(b);
    }

    public List<FloatingText> getTextList() {
        return textList;
    }
}
