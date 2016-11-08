package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.gameobjects.Drone;
import com.group17.geowars.gameobjects.Ship;
import com.group17.geowars.playerobjects.Player;
import com.group17.geowars.playerobjects.Profile;

public class GeoWars2 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Player player1;
	Sprite shipSprite;
	Sprite droneSprite;
	Integer pos;
	int r;
	int g;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


		//nikoala start geo wars
		Profile proff = new Profile("1");

		//nikoala kiest drone en ship
		Drone dr = proff.getDrones().get(0);
		Ship sp =proff.getShips().get(0);
		proff.setPlayer(dr,sp);

		//controle
		player1 = proff.getPlayer();
		System.out.println(player1.getStats());
		System.out.println(player1.getShip().getSprite());


		shipSprite = player1.getShip().getSprite();
		droneSprite = player1.getDrone().getSprite();

	pos=100;
	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		r+=10;
		g+=10;
		shipSprite.setSize(50,50);
		droneSprite.setSize(20,20);
		pos+=10;
		if(pos>600)
		{
			pos=5;
		}
		//sprite.setCenter(25,25);
		droneSprite.setPosition(500,100);
		shipSprite.setPosition(pos, pos);
		shipSprite.setColor(r,g,0,1);

		droneSprite.draw(batch);
		shipSprite.draw(batch);
		//batch.draw(sprite, 150, 150 ,50,50);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
