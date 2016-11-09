package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.group17.geowars.screens.Menu2;

public class GeoWars4 extends ApplicationAdapter{


	public Menu2 mainmenu;

	@Override
	public void create () {
		mainmenu = new Menu2();
		mainmenu.mainMenu();
	}

	@Override
	public void render () {

		mainmenu.render(Gdx.graphics.getDeltaTime());



	}
}
