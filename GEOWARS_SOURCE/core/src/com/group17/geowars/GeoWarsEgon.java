package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.database.*;
import com.group17.geowars.screens.GameScreen;
import com.group17.geowars.screens.Menu;

public class GeoWarsEgon extends ApplicationAdapter {

	public void DBTest () {
		DBManager manager = new DBManager();
		/*System.out.println(manager.DBupdate("profile","name","egoon2","name","egoon"));
		testselect System.out.println(manager.DBselect("*","profile","IDProfile","1"));*/
		System.out.println("test: "+manager.DBInsertShipsInProfile("egoon","Thief"));


	}
/*
	public Menu mainmenu;

	@Override
	public void create () {
		mainmenu = new Menu();
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
