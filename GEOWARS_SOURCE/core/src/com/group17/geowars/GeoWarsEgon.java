package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.group17.geowars.database.*;

public class GeoWarsEgon extends ApplicationAdapter {

	public void DBTest () {
		DBManager manager = new DBManager();
		/*System.out.println(manager.DBupdate("profile","name","egoon2","name","egoon"));
		testselect System.out.println(manager.DBselect("*","profile","IDProfile","1"));*/
		System.out.println("test: "+ manager.DBselectTOP10Highscore("arcade"));


	}
/*
	public showHighscores mainmenu;

	@Override
	public void create () {
		mainmenu = new showHighscores();
		mainmenu.create();
	}

	@Override
	public void render () {

		mainmenu.render(Gdx.graphics.getDeltaTime());


	}

	@Override
	public void dispose () {


	}*/
}
