package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.group17.geowars.screens.Menu2;

public class GeoWars4 extends ApplicationAdapter{


	private Menu2 menu;

	@Override
	public void create () {
		menu = new Menu2();
		menu.create();

	}

	@Override
	public void render () {
		menu.render(Gdx.graphics.getDeltaTime());

	}
}
