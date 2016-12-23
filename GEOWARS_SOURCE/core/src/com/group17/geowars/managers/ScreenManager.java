package com.group17.geowars.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.group17.geowars.screens.*;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Created by michield on 10/11/2016.
 */
public class ScreenManager {
    private LoginScreen StartScreen;

    private MenuScreen showScreen;

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
    private BitmapFont gameFont;

    private Skin skin;
    private Dictionary<String, MenuScreen> menuList;


    public ScreenManager(){

        menuList = new Hashtable<String, MenuScreen>();
        createStyles();
    }

    public MenuScreen currentScreen()
    {
        return showScreen;
    }

    public void init()
    {

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

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Guardians.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        gameFont = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose();

    }
    public void createMenus(){

        menuList.put("mainmenu",  new MainMenuScreen());
        menuList.put("playmenu", new PlayMenuScreen());
        menuList.put("profilemenu", new ProfileMenuScreen());
        menuList.put("highscoremenu", new HighScoreMenuScreen());
        menuList.put("game", new GameScreen());
        menuList.put("endgamemenu",new EndGameMenuScreen());
        menuList.put("optionsmenu",new OptionsMenuScreen());
        menuList.put("LoginScreen", StartScreen = new LoginScreen());
        menuList.put("upgrademenu", new UpgradeMenuScreen());
        menuList.put("shopmenu", new ShopMenuScreen());
        menuList.put("coopmenu", new CoopMenuScreen());
        menuList.put("inGameUpgradeScreen", new IngameUpgradeScreen());
        menuList.put("difficulty", new DifficultyScreen());
        menuList.put("guestErrorScreen", new GuestErrorScreen());
        setScreen(StartScreen);
    }


    public void render(){

        if(showScreen instanceof iGameScreen)
            ((iGameScreen)showScreen).renderGame();
        else
            showScreen.render(Gdx.graphics.getDeltaTime());
    }

    public MenuScreen getScreen(String name)
    {
        MenuScreen screen = menuList.get(name);
        if(screen == null) {
            System.out.println("SHOULDNT HAPPEN!");
            return StartScreen;
        }
        return screen;
    }


    public BitmapFont getGameFont() {
        return gameFont;
    }

    public void setScreen(MenuScreen screen){

        if(screen == null) {
            System.out.println("SHOULDNT HAPPEN!!EVAR!");
            showScreen = StartScreen;
        }
        else {
            if(showScreen instanceof iSetActive){
                ((iSetActive) showScreen).setInActive();
            }
            showScreen = screen;
            if(showScreen instanceof iSetActive) {
                ((iSetActive) showScreen).setActive();
            }
        }
        Gdx.input.setInputProcessor(showScreen.getStage());
    }

    public void resizeStages(int width, int height, boolean center)
    {
        Enumeration<String> enumKey = menuList.keys();
        while(enumKey.hasMoreElements()) {
            String key = enumKey.nextElement();
            menuList.get(key).getStage().getViewport().update(width, height, center);
        }
    }
}
