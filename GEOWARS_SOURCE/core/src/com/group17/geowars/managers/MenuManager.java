package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.group17.geowars.screens.*;

import java.util.Dictionary;
import java.util.Hashtable;


/**
 * Created by michield on 10/11/2016.
 */
public class MenuManager {
    private MainMenu mainMenu;
    private PlayMenu playMenu;
    private ProfileMenu profileMenu;
    private HighScore highscore;
    private GameScreen gameScreen;

    private Screen showScreen;

    private TextButtonStyle defaultStyle;
    public TextButtonStyle getDefaultStyle()
    {
        return defaultStyle;
    }
    private TextButtonStyle selectedStyle;
    public TextButtonStyle getSelectedStyle()
    {
        return selectedStyle;
    }

    private Skin skin;
    private Dictionary<String, Screen> menuList;

    public MenuManager(){
        menuList = new Hashtable<String, Screen>();

    }

    public void init()
    {



        createStyles();
        createMenus();
    }
    public void createStyles()
    {
        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        defaultStyle = new TextButton.TextButtonStyle();
        defaultStyle.up = skin.newDrawable("white", Color.WHITE);
        defaultStyle.down = skin.newDrawable("white", Color.BLACK);
        defaultStyle.fontColor = Color.BLACK;
        defaultStyle.font = skin.getFont("default");
        skin.add("default", defaultStyle);

        selectedStyle = new TextButton.TextButtonStyle();
        selectedStyle.up = skin.newDrawable("white", Color.GRAY);
        selectedStyle.fontColor = Color.BLACK;
        selectedStyle.font = skin.getFont("default");
        skin.add("selected", selectedStyle);
    }
    public void createMenus(){

        menuList.put("mainmenu", mainMenu = new MainMenu());
        menuList.put("playmenu", playMenu = new PlayMenu());
        menuList.put("profilemenu", profileMenu = new ProfileMenu());
        menuList.put("highScore", highscore = new HighScore());
        menuList.put("game", gameScreen = new GameScreen());
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
