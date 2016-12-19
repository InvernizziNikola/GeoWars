package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.screens.MenuScreen;

public class GeoWars extends ApplicationAdapter{

	public Viewport viewport;
	static public Camera camera;
	static public int WIDTH = 1240;
	static public int HEIGHT = 720;

	@Override
	public void create () {

		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		viewport.apply(true);
		MenuScreen beginScreen = Managers.getScreenManager().getScreen("mainmenu");
		Managers.getScreenManager().setScreen(beginScreen);
	}


	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
						GL20.GL_DEPTH_BUFFER_BIT |
						(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		Managers.getScreenManager().render();
	}

	@Override
	public void dispose (){
	}

	@Override
	public void resize(int width, int height) {
		HEIGHT = height;
		WIDTH = width;

		viewport.update(width, height);
		Managers.getScreenManager().resizeStages(width, height, false);
	}

}
