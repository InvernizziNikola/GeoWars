package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group17.geowars.screens.MainMenu;
import com.group17.geowars.screens.PlayMenu;


/**
 * Created by michield on 10/11/2016.
 */
public class MenuManager {
    private MainMenu main = new MainMenu();
    private PlayMenu play = new PlayMenu();
    private Screen showScreen;

    public MenuManager(){
        create();
    }

    public void create(){
        main.create();
        play.create();
        showScreen = main;
    }


    public void render(float delta){
        showScreen.render(delta);
    }



    public void setScreen(Screen screen){
        showScreen = screen;
    }

}
