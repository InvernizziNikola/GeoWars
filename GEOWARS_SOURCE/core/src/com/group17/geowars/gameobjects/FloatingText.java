package com.group17.geowars.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kevin on 23/12/2016.
 */
public class FloatingText {
    protected String popuptext = ""; //text om af te beelden na en pickup enz
    protected float popuptextTime;
    protected Vector2 popUpTextPos;
    BitmapFont font;

    public FloatingText(BitmapFont font, String popuptext, float popuptextTime, Vector2 popUpTextPos) {
        this.font = font;
        this.popuptext = popuptext;
        this.popuptextTime = popuptextTime;
        this.popUpTextPos = popUpTextPos;
    }
    public FloatingText(BitmapFont font, String popuptext, Vector2 popUpTextPos) {
        this(font,popuptext,5.0f,popUpTextPos);
    }
}
