package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.managers.Managers;
import com.group17.geowars.managers.MenuManager;
import com.group17.geowars.screens.Menu2;

public class GeoWars4 extends ApplicationAdapter{

	Batch batch;
	@Override
	public void create () {
		Managers.getMenuManager().toString();
	}

	@Override
	public void render () {

		batch = new SpriteBatch();

		Managers.update();

		batch.begin();
		Managers.render(batch);
		batch.end();
	}
}
