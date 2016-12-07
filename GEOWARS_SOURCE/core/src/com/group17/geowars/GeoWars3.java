package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.group17.geowars.screens.HighScoreMenu;

public class GeoWars3 extends ApplicationAdapter{


	public HighScoreMenu mainmenu;

	@Override
	public void create () {
		mainmenu = new HighScoreMenu();
		//mainmenu.getHighScore("arcade");

	}


	@Override
	public void render () {

		mainmenu.render(Gdx.graphics.getDeltaTime());



	}
}
