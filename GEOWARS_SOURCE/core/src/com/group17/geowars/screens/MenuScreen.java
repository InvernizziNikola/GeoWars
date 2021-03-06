package com.group17.geowars.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.group17.geowars.GeoWars;
import com.group17.geowars.database.XBOX360KeyMapping;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.utils.ExtendeImageButton;
import com.group17.geowars.utils.MenuGrid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikola on 23/11/2016.
 */
public class MenuScreen implements Screen {

    // TODO CREATE A LINKEDBUTTON ( EXTEND FROM BUTTON )
    // THIS LINKEDBUTTON WILL CONTAIN A MAX OF $ OTHER BUTTON TO WHICH IT IS LINKED
    // UP, DOWN, LEFT AND RIGHT. THIS WAY WE CAN 100% DECIDE TO WHICH BUTTON THE
    // SELECTOR GOES WHEN A KEY ON THE DPAD IS PRESSED

    protected Map<MenuGrid, ExtendeImageButton> menuButtons = new HashMap<MenuGrid, ExtendeImageButton>();
    protected MenuGrid selectedButton = new MenuGrid(0, 0);
    protected boolean pressed = true;
    protected TextButton.TextButtonStyle styleDefault;
    protected TextButton.TextButtonStyle styleSelected;
    protected Stage stage;
    protected Batch batch;
    protected BitmapFont text;

    private BitmapFont myText = Managers.getAssetManager().getGameFont(Color.WHITE,15);

    protected boolean active = false;

    public MenuScreen()
    {
        text = new BitmapFont();
        text.setColor(Color.WHITE);
        stage = new Stage(new FitViewport(GeoWars.WIDTH, GeoWars.HEIGHT));
        styleDefault = Managers.getScreenManager().getDefaultStyle();
        styleSelected = Managers.getScreenManager().getSelectedStyle();
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta)
    {
        // TODO CREATE A LINKEDBUTTON ( EXTEND FROM BUTTON )
        // THIS LINKEDBUTTON WILL CONTAIN A MAX OF $ OTHER BUTTON TO WHICH IT IS LINKED
        // UP, DOWN, LEFT AND RIGHT. THIS WAY WE CAN 100% DECIDE TO WHICH BUTTON THE
        // SELECTOR GOES WHEN A KEY ON THE DPAD IS PRESSED


        if(Managers.getControllerManager().getControllers().size() > 0 && menuButtons.size() > 0) {

            Controller c = Managers.getControllerManager().getControllers().get(0);

            if (c.getPov(0) == PovDirection.center && pressed && !c.getButton(1) && !c.getButton(2) && !c.getButton(3) && !c.getButton(0)) {
                pressed = false;
            }
            if(!pressed) {
                if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_UP) {
                    pressed = true;
                    MenuGrid temp = new MenuGrid(selectedButton.X(), selectedButton.Y() - 1);
                    lookForButtonOnRow(temp);
                } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_LEFT) {
                    pressed = true;
                    MenuGrid temp = new MenuGrid(selectedButton.X() - 1, selectedButton.Y());
                    lookForButtonOnColumn(temp);
                } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_RIGHT) {
                    pressed = true;
                    MenuGrid temp = new MenuGrid(selectedButton.X() + 1, selectedButton.Y());
                    lookForButtonOnColumn(temp);
                } else if (c.getPov(0) == XBOX360KeyMapping.BUTTON_DPAD_DOWN) {
                    pressed = true;
                    MenuGrid temp = new MenuGrid(selectedButton.X(), selectedButton.Y() + 1);
                    lookForButtonOnRow(temp);
                }
                if ((c.getButton(1) || c.getButton(2) || c.getButton(3) || c.getButton(0))) {
                    pressed = true;
                    pressButton(selectedButton);
                }
            }

            deSelectButtons();
            setSelectedButton();
        }

        if(this instanceof iHasStage) {
            Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            drawBackground("menuBackground");

            stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
            stage.draw();

        }
    }

    public void setSelectedButton()
    {
        if(menuButtons.get(selectedButton) != null)
             menuButtons.get(selectedButton).setHoverStyle();
    }

    public void lookForButtonOnRow(MenuGrid preferredMG)
    {
        int preferredX = preferredMG.X();
        int preferredY = preferredMG.Y();

        int diffx = 0;

        MenuGrid newMG = null;

        for(Map.Entry<MenuGrid, ExtendeImageButton> button : menuButtons.entrySet()) {
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
        for(Map.Entry<MenuGrid, ExtendeImageButton> button : menuButtons.entrySet()) {
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
        ImageButton tempButton = menuButtons.get(menugrid);
        if(tempButton != null){
            tempButton.setChecked(true);
        }
    }

    public void deSelectButtons()
    {
        for(Map.Entry<MenuGrid, ExtendeImageButton> button : menuButtons.entrySet())
        {
              button.getValue().setDefaultStyle();
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

            //menuButtons.put(position, tempButton);
            stage.addActor(tempButton);
        }
        catch (Exception e)
        {
            System.out.println("Error creating new button(" + name + ") at position: " + position);
        }

        return tempButton;
    }

    protected ExtendeImageButton newImageButton(String name, int x, int y, int width, int height, MenuGrid position)
    {
        ImageButton.ImageButtonStyle overStyle = new ImageButton.ImageButtonStyle();
        Texture overTexture = Managers.getAssetManager().getTexture(name+"_hover");
        overStyle.imageOver = new TextureRegionDrawable(new TextureRegion(overTexture));
        overStyle.imageDown = new TextureRegionDrawable(new TextureRegion(overTexture));
        overStyle.imageUp = new TextureRegionDrawable(new TextureRegion(overTexture));

        ImageButton.ImageButtonStyle defaultStyle = new ImageButton.ImageButtonStyle();
        Texture defaultTexture = Managers.getAssetManager().getTexture(name+"_default");
        defaultStyle.imageOver = new TextureRegionDrawable(new TextureRegion(overTexture));
        defaultStyle.imageDown = new TextureRegionDrawable(new TextureRegion(overTexture));
        defaultStyle.imageUp = new TextureRegionDrawable(new TextureRegion(defaultTexture));

        ExtendeImageButton tempButton = new ExtendeImageButton(defaultStyle,overStyle);

        tempButton.setPosition(x,y);
        tempButton.setWidth(width);
        tempButton.setHeight(height);
        menuButtons.put(position, tempButton);
        stage.addActor(tempButton);
        return tempButton;
    }

    protected ShapeRenderer drawTreeLine(int x, int y, int x2, int y2)
    {
        ShapeRenderer line = new ShapeRenderer();
        line.begin(ShapeRenderer.ShapeType.Line);
        line.setColor(1, 1, 1, 1);
        line.line(x, y, x2, y2);
        line.end();
        return line;
    }

    protected Sprite newImage(String name, int width, int height, int xpos, int ypos)
    {
        Texture tempImg = Managers.getAssetManager().getTexture(name);
        Sprite img = new Sprite(tempImg);
        img.setSize(width,height);
        img.setPosition(xpos,ypos);
        batch.begin();
        img.draw(batch);
        batch.end();
        return img;
    }

    protected Texture drawBackground(String name)
    {
        Texture background = Managers.getAssetManager().getTexture(name);
        batch.begin();
        batch.setColor(0.5f,0.5f,0.5f,1);
        batch.draw(background,0,0,GeoWars.WIDTH, GeoWars.HEIGHT);
        batch.end();
        return background;
    }

    protected TextField newTextField()
    {
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont font = new BitmapFont();
        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle();
        txtStyle.fontColor = Color.WHITE;
        txtStyle.font = font;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = myText;

        TextField TxtUsername = new TextField("", txtStyle);
        TxtUsername.setMessageText("type username here");

        return TxtUsername;
    }

    protected Label.LabelStyle style()
    {
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        return style;
    }

    protected TextField newPwField()
    {
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        BitmapFont font = new BitmapFont();
        TextField.TextFieldStyle txtStyle = new TextField.TextFieldStyle();
        txtStyle.fontColor = Color.WHITE;
        txtStyle.font = font;
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = myText;

        TextField TxtPw = new TextField("", txtStyle);
        TxtPw.setMessageText("type password here");
        TxtPw.setPasswordMode(true);
        TxtPw.setPasswordCharacter('*');
        return TxtPw;
    }
    public Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
