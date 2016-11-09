package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.group17.geowars.screens.Menu;

public class GeoWars3 extends ApplicationAdapter{


	public Menu mainmenu;

	@Override
	public void create () {
		mainmenu = new Menu();
		mainmenu.mainMenu();

	}

	@Override
	public void render () {

		mainmenu.render(Gdx.graphics.getDeltaTime());



	}
}
