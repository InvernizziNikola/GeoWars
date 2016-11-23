package com.group17.geowars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.group17.geowars.GeoWars;
import com.group17.geowars.MultiThreadingTestGeowars;

public class DesktopLauncherThreadingTest {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		/*config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		*/

		config.width = 800;
		config.height = 600;
		config.fullscreen = false;
		new LwjglApplication(new MultiThreadingTestGeowars(), config);
	}
}