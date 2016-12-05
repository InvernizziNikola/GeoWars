package com.group17.geowars.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.GL20;
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
    protected MenuGrid selectedButton = new MenuGrid(0, 0);
    protected boolean pressed = true;
    protected TextButton.TextButtonStyle styleDefault;
    protected TextButton.TextButtonStyle styleSelected;
    protected Stage stage;

    public MenuScreen()
    {
        stage = new Stage();
        styleDefault = Managers.getMenuManager().getDefaultStyle();
        styleSelected = Managers.getMenuManager().getSelectedStyle();
    }

    @Override
    public void render(float delta)
    {

        if(Controllers.getControllers().size > 1 && menuButtons.size() > 0) {

            Controller c = Controllers.getControllers().first();

            if (c.getPov(0) == PovDirection.center && pressed && !c.getButton(1) && !c.getButton(2) && !c.getButton(3) && !c.getButton(0)) {
                pressed = false;
            }
            if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_UP && !pressed) {
                pressed = true;
                MenuGrid temp = new MenuGrid(selectedButton.X(), selectedButton.Y() - 1);
                lookForButtonOnRow(temp);
            } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_LEFT && !pressed) {
                pressed = true;
                MenuGrid temp = new MenuGrid(selectedButton.X() - 1, selectedButton.Y());
                lookForButtonOnColumn(temp);
            } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_RIGHT && !pressed) {
                pressed = true;
                MenuGrid temp = new MenuGrid(selectedButton.X() + 1, selectedButton.Y());
                lookForButtonOnColumn(temp);
            } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_DOWN && !pressed) {
                pressed = true;
                MenuGrid temp = new MenuGrid(selectedButton.X(), selectedButton.Y() + 1);
                lookForButtonOnRow(temp);
            }
            if ((c.getButton(1) || c.getButton(2) || c.getButton(3) || c.getButton(0)) && !pressed) {
                pressed = true;
                pressButton(selectedButton);
            }

            deSelectButtons();
            setSelectedButton();
        }

        if(this instanceof hasStage) {
            Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();
        }
    }

    public void setSelectedButton()
    {
        if(menuButtons.get(selectedButton) != null)
            menuButtons.get(selectedButton).setStyle(styleSelected);
    }

    public void lookForButtonOnRow(MenuGrid preferredMG)
    {
        int preferredX = preferredMG.X();
        int preferredY = preferredMG.Y();

        int diffx = 0;

        MenuGrid newMG = null;

        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet()) {
            if (button.getKey().Y() == preferredY) {
                if (newMG == null || diffx > Math.abs(button.getKey().X() - preferredX)) {
                    diffx = Math.abs(button.getKey().X() - preferredX);
                    newMG = button.getKey();
                }
            }
        }
        if(newMG != null)
        {
            selectedButton = newMG;
        }
    }
    public void lookForButtonOnColumn(MenuGrid preferredMG)
    {
        int preferredX = preferredMG.X();
        int preferredY = preferredMG.Y();

        MenuGrid newMG = null;

        int diffy = 0;
        for(Map.Entry<MenuGrid, TextButton> button : menuButtons.entrySet()) {
            if (button.getKey().X() == preferredX) {
                if (newMG == null || diffy > Math.abs(button.getKey().Y() - preferredY)) {
                    diffy = Math.abs(button.getKey().Y() - preferredY);
                    newMG = button.getKey();
                }
            }
        }
        if(newMG != null)
        {
            selectedButton = newMG;
        }
    }

    public void pressButton(MenuGrid menugrid)
    {
        pressed = true;
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

    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

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
