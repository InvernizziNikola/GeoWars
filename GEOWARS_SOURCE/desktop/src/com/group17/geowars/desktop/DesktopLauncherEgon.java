package com.group17.geowars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group17.geowars.GeoWars2;
import com.group17.geowars.GeoWarsEgon;

import com.group17.geowars.screens.Menu;

public class DesktopLauncherEgon {
    public static void main(String[] arg) {
        new GeoWarsEgon().DBTest();
        /*LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new GeoWarsEgon(), config);*/

        
    }
}
