package com.group17.geowars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.group17.geowars.screens.GameScreen;
import com.group17.geowars.utils.Constants;

public class GeoWars extends ApplicationAdapter {
	SpriteBatch batch;
	GameScreen screen;

	private OrthographicCamera camera;
	//private ExtendViewport viewport;
	private World world;
	private Box2DDebugRenderer debugRenderer;

	private Body body, wall;

	@Override
	public void create () {

		Box2D.init();
		batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w/2, h/2);

		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		//viewport = new ExtendViewport(800, 600, camera);
		screen = new GameScreen(batch);


		body = createBox(2, 20, 32, 32, false);
		wall = createBox(10, 0, 10, 32, true);

	}


	@Override
	public void render ()
	{
		cameraUpdate(Gdx.graphics.getDeltaTime());

		int horizontalForce = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			horizontalForce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			horizontalForce += 1;
		}

		body.setLinearVelocity(horizontalForce * 5, body.getLinearVelocity().y);

		world.step(1/60f, 6, 2);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
						GL20.GL_DEPTH_BUFFER_BIT |
						(Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		batch.begin();
		screen.render();
		batch.end();

		debugRenderer.render(world, camera.combined.scl(Constants.PPM));
	}
	
	@Override
	public void dispose () {
		batch.dispose();

		world.dispose();
		debugRenderer.dispose();
	}


	@Override
	public void resize(int width, int height) {
		//viewport.update(width, height, true);
		camera.setToOrtho(false, width/2, height/2);
		//batch.setProjectionMatrix(camera.combined);
	}

	public Body createBox(int x, int y, int width, int height, boolean isStatic)
	{
		Body pBody;

		BodyDef def = new BodyDef();
		if(isStatic)
			def.type = BodyDef.BodyType.StaticBody;
		else
			def.type = BodyDef.BodyType.DynamicBody;

		def.position.set(x / Constants.PPM, y / Constants.PPM);
		def.fixedRotation = true;
		pBody = world.createBody(def);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2 / Constants.PPM, height / 2 / Constants.PPM);

		pBody.createFixture(shape, 1.0f);
		shape.dispose();

		return pBody;
	}

	public void cameraUpdate(float delta)
	{
		Vector3 position = camera.position;
		position.x = body.getPosition().x * Constants.PPM;
		position.y = body.getPosition().y * Constants.PPM;
		camera.position.set(position);

		camera.update();
	}
}
