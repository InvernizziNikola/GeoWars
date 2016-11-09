package com.group17.geowars.managers;

import com.badlogic.gdx.graphics.Texture;
import com.group17.geowars.gameobjects.Bullet;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */
public class AssetManager {
    private static AssetManager _instance;

    public static AssetManager getInstance()
    {
        if(_instance == null)
            _instance = new AssetManager();

        return _instance;
    }

    private Dictionary<String, Texture> textures;

    private AssetManager () {
        textures = new Hashtable<String, Texture>();
    }
    public Texture getTexture(String name)
    {
        return addTexture(name);
    }

    private Texture addTexture(String name)
    {
        Texture texture = textures.get(name);

        if(texture != null)
            return texture;

        File f = new File(name+".png");
        if(f.exists()) {
            texture = new Texture(name + ".png");
            textures.put(name, texture);
            return texture;
        }else
        {
            texture = new Texture("error.png");
            textures.put(name, texture);
            return texture;
        }
    }



}
