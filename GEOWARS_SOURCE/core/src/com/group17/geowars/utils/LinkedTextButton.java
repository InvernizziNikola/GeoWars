package com.group17.geowars.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by nikola on 06/12/2016.
 */
public class LinkedTextButton extends TextButton {

    LinkedTextButton above = null;
    LinkedTextButton below = null;
    LinkedTextButton left = null;
    LinkedTextButton right = null;

    public LinkedTextButton(String text, Skin skin) {
        super(text, skin);
    }

    public LinkedTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    public LinkedTextButton(String text, TextButtonStyle style) {
        super(text, style);
    }

    public void setNeighbourButtons(LinkedTextButton a, LinkedTextButton b, LinkedTextButton l, LinkedTextButton r)
    {
        above = a;
        below = b;
        left = l;
        right = r;
    }
}
