/*package com.group17.geowars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Menu2 {


    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private BitmapFont title;
    private TextButton quitButton;
    private TextButton shopButton;
    private TextButton playButton;
    private TextButton optionButton;
    private TextButton leaderBordButton;
    private TextButton profileButton;
    private TextButtonStyle textButtonStyle;
    private TextButton backButton;

    public Menu2(){
        create();
    }
    public void create(){
        batch = new SpriteBatch();
        title = new BitmapFont();
        title.setColor(Color.WHITE);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
        Pixmap pixmap = new Pixmap(200,50, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        BitmapFont playButtonText = new BitmapFont();
        skin.add("default",playButtonText);

        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.checked = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = Color.BLACK;
        skin.add("default", textButtonStyle);

        playButton=new TextButton("PLAY",textButtonStyle);
        playButton.setPosition(300, 450);
        stage.addActor(playButton);

        profileButton = new TextButton("PROFILE", textButtonStyle);
        profileButton.setPosition(300, 390);
        stage.addActor(profileButton);

        optionButton = new TextButton("OPTIONS", textButtonStyle);
        optionButton.setPosition(300, 330);
        stage.addActor(optionButton);

        leaderBordButton = new TextButton("LEADERBORDS", textButtonStyle);
        leaderBordButton.setPosition(300, 270);
        stage.addActor(leaderBordButton);

        shopButton = new TextButton("SHOP", textButtonStyle);
        shopButton.setPosition(300, 210);
        stage.addActor(shopButton);

        quitButton = new TextButton("QUIT GAME", textButtonStyle);
        quitButton.setPosition(300, 90);
        stage.addActor(quitButton);

    }


    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        title.draw(batch,"GEOMETRYWARS",325,550);
        batch.end();
        stage.draw();

        if (quitButton.isChecked()){
            Gdx.app.exit();
        }




    }


}*/
package com.group17.geowars.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class Menu2 implements Screen {
    private Skin skin;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont title;
    private TextButtonStyle textButtonStyle;



    public Menu2(){
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
        Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        textButtonStyle = new TextButtonStyle();
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
                playMenu();

            }
        });

        // Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
        // Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
        // ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
        // revert the checked state.
        quitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());

                Gdx.app.exit();
                //g.setScreen( new PlayMenu());

            }
        });

    }

    public void playMenu() {

        batch = new SpriteBatch();
        stage = new Stage();
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
        BitmapFont bfont = new BitmapFont();

        skin.add("default", bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.WHITE);
        textButtonStyle.fontColor = Color.BLACK;

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        final TextButton campaignButton = new TextButton("CAMPAIGN",textButtonStyle);
        campaignButton.setPosition(125,400);
        campaignButton.setHeight(50);
        campaignButton.setWidth(150);
        stage.addActor(campaignButton);

        final TextButton arcadeButton = new TextButton("ARCADE",textButtonStyle);
        arcadeButton.setPosition(325,400);
        arcadeButton.setHeight(50);
        arcadeButton.setWidth(150);
        stage.addActor(arcadeButton);

        final TextButton mutliplayerButton = new TextButton("CO-OP",textButtonStyle);
        mutliplayerButton.setPosition(525,400);
        mutliplayerButton.setHeight(50);
        mutliplayerButton.setWidth(150);
        stage.addActor(mutliplayerButton);

        final TextButton backButton = new TextButton("BACK", textButtonStyle);
        backButton.setPosition(325, 250);
        backButton.setHeight(50);
        backButton.setWidth(150);
        stage.addActor(backButton);
        backButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                create();
            }
        });

        arcadeButton.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {

            }
        });

    }


    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
}