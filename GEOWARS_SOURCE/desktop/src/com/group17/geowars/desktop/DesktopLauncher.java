package com.group17.geowars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group17.geowars.GeoWars;

public class DesktopLauncher {

	static public int WIDTH = 1920;
	static public int HEIGHT = 1080;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = WIDTH;
		config.height = HEIGHT;
		config.fullscreen = true;

		new LwjglApplication(new GeoWars(WIDTH, HEIGHT), config);
	}
}
