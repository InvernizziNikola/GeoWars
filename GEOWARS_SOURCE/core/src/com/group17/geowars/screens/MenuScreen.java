package com.group17.geowars.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.group17.geowars.database.XBOX360KeyMapping;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikola on 23/11/2016.
 */
public class MenuScreen implements Screen {


    protected Map<MenuGrid, TextButton> menuButtons = new HashMap<MenuGrid, TextButton>();
    protected int selectedButtonX = 0;
    protected int selectedButtonY = 0;
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

        if(Controllers.getControllers().size < 1)
            return ;

        Controller c = Controllers.getControllers().first();
        if(c.getButton(0) && !pressed)
        {
            selectedButtonY++;

            if(selectedButtonY > menuButtons.size()-1)
                selectedButtonY = 0;
            pressed = true;
        }
        if(c.getButton(3) && !pressed)
        {
            selectedButtonY--;
            if(selectedButtonY < 0)
                selectedButtonY = menuButtons.size()-1;
            pressed = true;
        }
        if(!c.getButton(0) && !c.getButton(3) && pressed)
        {
            pressed = false;
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_DOWN)
        {
            System.out.println("down");
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_LEFT)
        {
            System.out.println("left");
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_RIGHT)
        {
            System.out.println("right");
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_UP)
        {
            System.out.println("up");
        }
        if(c.getButton(1) || c.getButton(2))
        {
            pressButton(new MenuGrid(selectedButtonX, selectedButtonY));
        }

        selectButton(new MenuGrid(selectedButtonX, selectedButtonY));
    }
    public void pressButton(MenuGrid menugrid)
    {
        TextButton tempButton = getButton(menugrid);
        if(tempButton != null){
            tempButton.setChecked(true);
        }
    }

    public void selectButton(MenuGrid menuGrid)
    {
        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet())
        {
            if(button.getKey().equals(new MenuGrid(selectedButtonX, selectedButtonY)))
            {
                button.getValue().setStyle(styleSelected);
            }
            else
            {
                button.getValue().setStyle(styleDefault);
            }
        }
    }

    public TextButton getButton(MenuGrid menuGrid)
    {
        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet())
        {
            if(button.getKey().equals(new MenuGrid(selectedButtonX, selectedButtonY)))
            {
                return button.getValue();
            }
        }
        return null;
    }



    protected TextButton newButton(String name, int x, int y, int width, int height, MenuGrid position)
    {
        TextButton tempButton = null;
        try{
            tempButton = new TextButton(name, styleDefault);
            tempButton.setPosition(x, y);
            tempButton.setWidth(width);
            tempButton.setHeight(height);

            menuButtons.put(position, tempButton);
            stage.addActor(tempButton);
        }
        catch (Exception e)
        {
            System.out.println("Error creating new button(" + name + ") at position: " + position);
        }

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
