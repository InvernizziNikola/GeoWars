package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.group17.geowars.screens.HighScore;

public class GeoWars3 extends ApplicationAdapter{


	public HighScore mainmenu;

	@Override
	public void create () {
		mainmenu = new HighScore();
		mainmenu.HighScore();

	}

	@Override
	public void render () {

		mainmenu.render(Gdx.graphics.getDeltaTime());



	}
}
