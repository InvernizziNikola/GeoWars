package com.group17.geowars.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.group17.geowars.managers.Managers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikola on 23/11/2016.
 */
public class MenuScreen implements Screen {


    protected Map<Integer, TextButton> menuButtons = new HashMap<Integer, TextButton>();
    protected int selectedButton = 0;
    protected boolean pressed = false;
    protected TextButton.TextButtonStyle styleDefault;
    protected TextButton.TextButtonStyle styleSelected;
    protected Stage stage;

    public MenuScreen()
    {
        stage = new Stage();
        styleDefault = Managers.getMenuManager().getDefaultStyle();
        styleSelected = Managers.getMenuManager().getSelectedStyle();
    }

    public Stage getStage()
    {
        return stage;
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {


        Controller c = Controllers.getControllers().first();
        if(c.getButton(0) && !pressed)
        {
            selectedButton++;

            if(selectedButton > menuButtons.size()-1)
                selectedButton = 0;
            pressed = true;
        }
        if(c.getButton(3) && !pressed)
        {
            selectedButton--;
            if(selectedButton < 0)
                selectedButton = menuButtons.size()-1;
            pressed = true;
        }
        if(!c.getButton(0) && !c.getButton(3) && pressed)
        {
            pressed = false;
        }

        if(c.getButton(1) || c.getButton(2))
        {
            if(menuButtons.size() > 0)
                menuButtons.get(selectedButton).setChecked(true);
        }


        for(Map.Entry<Integer, TextButton> button : menuButtons.entrySet())
        {
            if(button.getKey() == selectedButton)
            {
                button.getValue().setStyle(styleSelected);
            }
            else
            {
                button.getValue().setStyle(styleDefault);
            }
        }
    }
    protected TextButton newButton(String name, int x, int y, int width, int height)
    {
        TextButton tempButton = new TextButton(name, styleDefault);
        tempButton.setPosition(x, y);
        tempButton.setWidth(width);
        tempButton.setHeight(height);

        menuButtons.put(menuButtons.size(), tempButton);
        stage.addActor(tempButton);

        return tempButton;
    }

    @Override
    public void resize(int width, int height) {

    }

    public void SetActive()
    {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
