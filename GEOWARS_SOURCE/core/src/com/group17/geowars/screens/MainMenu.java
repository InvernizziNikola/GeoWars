package com.group17.geowars.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/**
 * Created by michield on 10/11/2016.
 */
public class MainMenu implements Screen{
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont title;
    private TextButton.TextButtonStyle textButtonStyle;


    public MainMenu(){
        create();
    }

    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        title = new BitmapFont();
        Gdx.input.setInputProcessor(stage);


        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.fontColor = Color.BLACK;

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton quitButton=new TextButton("QUIT GAME",textButtonStyle);
        quitButton.setPosition(300, 90);
        quitButton.setWidth(150);
        quitButton.setHeight(50);
        stage.addActor(quitButton);

        final TextButton playButton = new TextButton("PLAY", textButtonStyle);
        playButton.setPosition(300,450);
        playButton.setWidth(150);
        playButton.setHeight(50);
        stage.addActor(playButton);

        final TextButton profileButton = new TextButton("PROFILE", textButtonStyle);
        profileButton.setPosition(300, 390);
        profileButton.setWidth(150);
        profileButton.setHeight(50);
        stage.addActor(profileButton);

        final TextButton optionButton = new TextButton("OPTIONS", textButtonStyle);
        optionButton.setPosition(300, 330);
        optionButton.setWidth(150);
        optionButton.setHeight(50);
        stage.addActor(optionButton);

        final TextButton leaderBordButton = new TextButton("LEADERBORDS", textButtonStyle);
        leaderBordButton.setPosition(300, 270);
        leaderBordButton.setWidth(150);
        leaderBordButton.setHeight(50);
        stage.addActor(leaderBordButton);

        final TextButton shopButton = new TextButton("SHOP", textButtonStyle);
        shopButton.setPosition(300, 210);
        shopButton.setWidth(150);
        shopButton.setHeight(50);
        stage.addActor(shopButton);

        playButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        quitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                System.out.println("Clicked! Is checked: ");

                Gdx.app.exit();


            }
        });

    }

    @Override
    public void show() {

    }

    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        //Table.drawDebug(stage);
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
