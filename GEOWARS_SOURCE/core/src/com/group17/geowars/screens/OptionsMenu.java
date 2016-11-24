package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by michield on 23/11/2016.
 */
public class OptionsMenu extends MenuScreen {

    public OptionsMenu()
    {
        super();
        create();
    }

    public void create()
    {
        Gdx.input.setInputProcessor(stage);

        TextButton controllerBindings = newButton("VIEW CONTROLLER BINDINGs",20,20,150,50);
    }
}
