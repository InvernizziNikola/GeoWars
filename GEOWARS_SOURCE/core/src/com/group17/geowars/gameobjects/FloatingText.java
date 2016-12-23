package com.group17.geowars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.group17.geowars.GeoWars;
import com.group17.geowars.managers.Managers;

/**
 * Created by kevin on 23/12/2016.
 */
public class FloatingText extends GameObject implements GOInterface {
    protected String popuptext = ""; //text om af te beelden na en pickup enz
    protected float popuptextTime;
    protected Vector2 popUpTextPos;
    BitmapFont font;
    public boolean destroy = false;

    public FloatingText(BitmapFont font, String popuptext, float popuptextTime, Vector2 popUpTextPos) {
        super(popUpTextPos);
        this.font = font;
        this.popuptext = popuptext;
        this.popuptextTime = popuptextTime;
        this.popUpTextPos = popUpTextPos;
    }
    public FloatingText(BitmapFont font, String popuptext, Vector2 popUpTextPos) {
        this(font,popuptext,5.0f,popUpTextPos);
    }

    public String getPopuptext() {
        return popuptext;
    }

    public float getPopuptextTime() {
        return popuptextTime;
    }

    public Vector2 getPopUpTextPos() {
        return popUpTextPos;
    }

    public BitmapFont getFont() {
        return font;
    }



    @Override
    public void render(Batch batch)
    {
        // TODO DRAW IMAGE CORRRECTLY
       // font.draw(batch, popuptext, 200, 200);

        font.draw(batch, popuptext, popUpTextPos.x, popUpTextPos.y);
        popUpTextPos.y += Gdx.graphics.getDeltaTime() * 150;
        popUpTextPos.x += Gdx.graphics.getDeltaTime() * 100 * MathUtils.cosDeg(popUpTextPos.y);
    }

    public void handleUpdate(){
        popuptextTime-=Gdx.graphics.getDeltaTime();
        if (popuptextTime<0.01f) {

        Managers.getFloatingTextManager().remove(this);
        }
    }


    @Override
    public void update()
    {
        handleUpdate();
    }



}
