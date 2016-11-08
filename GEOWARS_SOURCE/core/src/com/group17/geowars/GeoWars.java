package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.GameObjects.GameObject;
import com.group17.geowars.GameObjects.Geom;

public class GeoWars extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	Geom geom;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


		// REMOVE: GEOM TEST
		geom = new Geom(2);
		System.out.println(geom.toString());
		System.out.println(geom.getLoot().toString());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		geom.render(batch);

		batch.draw(img, 150, 150);
		batch.end();


		geom.update();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
