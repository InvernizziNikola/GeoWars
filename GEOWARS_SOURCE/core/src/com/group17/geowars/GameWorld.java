package com.group17.geowars;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.group17.geowars.managers.Managers;

/**
 * Created by nikola on 08/11/2016.
 */

public class GameWorld
{
    //private boolean played;
    private Batch batch;

    public GameWorld(Batch batch)
    {
        this.batch = batch;

        /*if (!played){
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
*/
    }

    public void update()
    {

        Managers.update();
    }

    public void render()
    {
        Managers.render(batch);
        //Sound sound = Managers.getAssetManager().getSounds("test");
        //Sound sound = Gdx.audio.newSound(Gdx.files.internal("pacman_beginning.wav"));
        //sound.play();

    }

}
