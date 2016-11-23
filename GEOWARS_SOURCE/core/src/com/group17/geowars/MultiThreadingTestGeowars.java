package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.screens.MenuScreen;

public class MultiThreadingTestGeowars extends ApplicationAdapter {
	private SpriteBatch batch;
	//private GameScreen screen;

	@Override
	public void create () {

		batch = new SpriteBatch();

		//screen = new GameScreen();
		MenuScreen beginScreen = Managers.getMenuManager().getScreen("mainmenu");
		Managers.getMenuManager().setScreen(beginScreen);



		ThreadingTest test = new ThreadingTest("Thread 1");
		test.start();
		ThreadingTest test2 = new ThreadingTest("Thread 2");
		test2.start();
	}


	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
						GL20.GL_DEPTH_BUFFER_BIT |
						(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		System.out.print("");


		Managers.update();
		Managers.render(batch);

	}

	@Override
	public void dispose () {
		batch.dispose();
	}


	@Override
	public void resize(int width, int height) {
	}

}
