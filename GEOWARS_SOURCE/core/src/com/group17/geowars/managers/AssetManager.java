package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.group17.geowars.gameobjects.Bullet;
import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by nikola on 08/11/2016.
 */
public class AssetManager {

    private Map<String, Texture> textures;
    private Map<String, Sound> sounds;
    private Map<String,Color> colors;
    private Map<String,BitmapFont> fonts;

    public AssetManager() {

        textures = new HashMap<String, Texture>();
        sounds= new HashMap<String, Sound> ();
        colors =new HashMap<String, Color>();
        fonts = new HashMap<String, BitmapFont>();
    }

    public void init()
    {
        colors.put("geom",new Color(1.0f, 1.0f, 0, 0.90f));
        colors.put("shield",new Color(0.1f, 0.8f, 0, 0.5f));
        colors.put("playerbullet",new Color(0.1f,0.8f,0.8f,0.8f));
        colors.put("enemybullet",new Color(0.9f,0.1f,0.1f,0.9f));
    }

    public BitmapFont getGameFont(Color color,int size){

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Guardians.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();
        return font;
    }

    public Texture getTexture(String name)
    {
        return addTexture(name);
    }
    public Sound getSounds(String name)
    {
        return addSound(name);
    }

    public Color getColor(String name){return colors.get(name);}

    private Texture addTexture(String name)
    {
        Texture texture = textures.get(name);

        if(texture != null)
            return texture;


        try{
            texture = new Texture(name + ".png");
            textures.put(name, texture);
            return texture;
        }catch (Exception e)
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

        File f = new File(name);
        if(f.exists()) {
            sound =   Gdx.audio.newSound(Gdx.files.internal(name));
            sounds.put(name, sound);
            return sound;
        }else
        {
            sound = Gdx.audio.newSound(Gdx.files.internal("error.wav"));
            sounds.put(name, sound);
            return sound;
        }
    }
    public void dispose()
    {
        for(Texture t : textures.values())
        {
            t.dispose();
        }
    }
}
