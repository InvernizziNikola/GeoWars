package com.group17.geowars.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by nikola on 06/12/2016.
 */
public class ExtendeImageButton extends ImageButton {

    ImageButtonStyle defaultStyle;
    ImageButtonStyle overStyle;

    public ExtendeImageButton(ImageButtonStyle defaultStyle, ImageButtonStyle overStyle) {
        super(defaultStyle);
        this.defaultStyle = defaultStyle;
        this.overStyle = overStyle;
    }

    public void setDefaultStyle()
    {
        super.setStyle(defaultStyle);
    }
    public void setHoverStyle()
    {
        super.setStyle(overStyle);
    }
}
