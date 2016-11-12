package com.group17.geowars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.group17.geowars.gameobjects.Geom;
import com.group17.geowars.gameobjects.Ship;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.playerobjects.Profile;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by nikola on 08/11/2016.
 */

public class GameWorld
{
    public List<Geom> geoms;
    public List<Ship> ships;
    public List<Profile> profiles;
    private BitmapFont font;
    private boolean played;

    private Batch batch;

    public GameWorld(Batch batch)
    {
        this.batch = batch;

        font = new BitmapFont();

        if (!played){
            played= true;
            try {
                InputStream in = new FileInputStream("pacman_beginning.wav");
                try {
                    AudioStream as = new AudioStream(in);
                    AudioPlayer.player.start(as);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public void update()
    {
        Managers.update();
    }

    public void render()
    {
        Managers.render(batch);

        font.draw(batch, "Wave"+Managers.getLevelManager().getCurrentwave(), 375, 590);
        //Sound sound = Managers.getAssetManager().getSounds("test");
        //Sound sound = Gdx.audio.newSound(Gdx.files.internal("pacman_beginning.wav"));
        //sound.play();



    }

}
