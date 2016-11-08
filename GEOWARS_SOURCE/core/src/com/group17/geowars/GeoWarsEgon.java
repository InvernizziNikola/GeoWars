package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GeoWarsEgon extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;



	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


	}
	/*
	*
	*     private JdbcTemplate template;


    public static void main(String[] args) {
        JdbcTemplate test = new JdbcTemplate();

        System.out.println(test.DBselect());
	*
	* */

	@Override
	public void render () {


		System.out.println(test.DBselect());


		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(img, 150, 150);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
