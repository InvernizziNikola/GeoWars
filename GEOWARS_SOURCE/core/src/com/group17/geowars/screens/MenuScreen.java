package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.group17.geowars.database.XBOX360KeyMapping;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.MenuGrid;
import javafx.scene.control.MenuButton;

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
    public void render(float delta)
    {

        if(Controllers.getControllers().size < 1 || menuButtons.size() == 0)
            return ;

        Controller c = Controllers.getControllers().first();

        if(c.getPov(0) == PovDirection.center && pressed)
        {
            pressed = false;
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_UP && !pressed)
        {
            if(menuButtons.get(new MenuGrid(selectedButtonX, selectedButtonY - 1)) != null)
            {
                selectedButtonY--;
            }
            pressed = true;
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_LEFT && !pressed)
        {
            if(menuButtons.get(new MenuGrid(selectedButtonX - 1, selectedButtonY)) != null)
            {
                selectedButtonX--;
            }
            pressed = true;
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_RIGHT && !pressed)
        {
            if(menuButtons.get(new MenuGrid(selectedButtonX + 1, selectedButtonY)) != null)
            {
                selectedButtonX++;
            }
            pressed = true;
        }
        if(c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_DOWN && !pressed)
        {
            if(menuButtons.get(new MenuGrid(selectedButtonX, selectedButtonY + 1)) != null)
            {
                selectedButtonY++;
            }
            pressed = true;
        }
        if(c.getButton(1) || c.getButton(2) || c.getButton(3) || c.getButton(4))
        {
            pressButton(new MenuGrid(selectedButtonX, selectedButtonY));
        }

        deSelectButtons();
        menuButtons.get(new MenuGrid(selectedButtonX, selectedButtonY)).setStyle(styleSelected);
    }

    public void getClosestButtonOnRow(MenuGrid preferedMG)
    {
        int preferedX = preferedMG.X();
        int preferedY = preferedMG.Y();

        Button closestB = null;
        MenuGrid closest = null;

        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet())
        {
            if(button.getKey().Y() == preferedY)
            {
                if(closest == null)
                    closestB = button.getValue();
            }
        }
    }
    public void getClosestButtonOnColumn(MenuGrid menugrid)
    {

    }

    public void pressButton(MenuGrid menugrid)
    {
        TextButton tempButton = menuButtons.get(menugrid);
        if(tempButton != null){
            tempButton.setChecked(true);
        }
    }

    public void deSelectButtons()
    {
        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet())
        {
            button.getValue().setStyle(styleDefault);

        }
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
