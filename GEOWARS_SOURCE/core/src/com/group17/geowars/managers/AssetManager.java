package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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

    private Dictionary<String, Texture> textures;
    private Dictionary<String, Sound> sounds;

    public AssetManager () {

        textures = new Hashtable<String, Texture>();
        sounds= new Hashtable<String, Sound> ();
    }

    public void init()
    {

    }

    public Texture getTexture(String name)
    {
        return addTexture(name);
    }
    public Sound getSounds(String name)
    {
        return addSound(name);
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

    private Sound addSound(String name)
    {
        Sound sound = sounds.get(name);

        if(sound != null)
            return sound;

        File f = new File(name+".wav");
        if(f.exists()) {
            sound =   Gdx.audio.newSound(Gdx.files.internal(name + ".wav"));
            sounds.put(name, sound);
            return sound;
        }else
        {
            sound = Gdx.audio.newSound(Gdx.files.internal("error.wav"));
            sounds.put(name, sound);
            return sound;
        }
    }



}
