package com.group17.geowars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group17.geowars.GeoWars;

public class DesktopLauncher {

	static public int WIDTH = 1240;
	static public int HEIGHT = 720;


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		config.width = WIDTH;
		config.height = HEIGHT;
		config.fullscreen = false;

		/*
		config.width = 800;
		config.height = 600;
		config.fullscreen = false;
		*/
		new LwjglApplication(new GeoWars(), config);
	}
}
