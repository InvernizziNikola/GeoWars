package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.screens.GameScreen;

public class GeoWars extends ApplicationAdapter {
	SpriteBatch batch;
	GameScreen screen;
	@Override
	public void create () {
		batch = new SpriteBatch();

		screen = new GameScreen(batch);
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
						GL20.GL_DEPTH_BUFFER_BIT |
						(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		batch.begin();
		screen.render();
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
