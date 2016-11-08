package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group17.geowars.database.*;

public class GeoWarsEgon extends ApplicationAdapter {




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


		DBManager manager = new DBManager();
		System.out.println(manager.DBselect("*","profile","IDProfile","1"));



	}

}
