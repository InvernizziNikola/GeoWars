package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
	int veranderingPos;
	int r;
	int g;
	boolean up;
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
		veranderingPos=2;
	pos=100;
		up=true;
	}

	@Override
	public void render () {
		Vector2 mousePos = new Vector2(Gdx.input.getX(), -Gdx.input.getY() + 600);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		System.out.println(mousePos.x+", "+mousePos.y);
		r+=2;
		g+=2;
		shipSprite.setSize(50,50);
		droneSprite.setSize(20,20);

		droneSprite.setPosition(mousePos.x,mousePos.y+50);
		shipSprite.setPosition(mousePos.x, mousePos.y);

		/*
		pos+=veranderingPos;
		System.out.println(pos);
		if(up &&pos>548)
		{
			veranderingPos=-2;
			up=false;
		}
		if(!up &&pos<4)
		{
			veranderingPos=2;
			up=true;
		}

		droneSprite.setPosition(100+50,pos+50);
		shipSprite.setPosition(100, pos);
		*/

		shipSprite.setColor(r,g,0,1);
		droneSprite.setColor(r,g,0,1);

		droneSprite.draw(batch);
		shipSprite.draw(batch);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
