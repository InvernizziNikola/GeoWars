package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.group17.geowars.screens.MainMenu;
import com.group17.geowars.screens.PlayMenu;
import com.group17.geowars.screens.ProfileMenu;
import com.group17.geowars.screens.hasStage;

import java.util.Dictionary;
import java.util.Hashtable;


/**
 * Created by michield on 10/11/2016.
 */
public class MenuManager {
    private MainMenu mainMenu;
    private PlayMenu playMenu;
    private ProfileMenu profileMenu;


    private Screen showScreen;

    private Dictionary<String, Screen> menuList;

    public MenuManager(){
        menuList = new Hashtable<String, Screen>();

    }

    public void init()
    {
        create();
    }
    public void create(){
        menuList.put("mainmenu", mainMenu = new MainMenu());
        menuList.put("playmenu", playMenu = new PlayMenu());
        menuList.put("profilemenu", profileMenu = new ProfileMenu());
        setScreen(mainMenu);
    }


    public void render(float deltaTime){
        showScreen.render(deltaTime);
    }

    public Screen getScreen(String name)
    {
        Screen screen = menuList.get(name);
        if(screen == null) {
            System.out.println("SHOULDNT HAPPEN");
            return mainMenu;
        }
        return screen;
    }

    public void setScreen(Screen screen){

        if(screen == null) {
            System.out.println("SHOULDNT HAPPEN!!EVAR!");
            showScreen = mainMenu;
        }
        else {
            showScreen = screen;
        }
        Gdx.input.setInputProcessor(((hasStage)showScreen).getStage());
    }

}
