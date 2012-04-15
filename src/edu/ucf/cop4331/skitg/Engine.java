package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Handles game logic and physics
 * @author Jeremy Mayeres
 *
 */
public class Engine {
	
	// Player 1
	private Tank tank1;
	// Player 2
	private Tank tank2;
	// Game map
	private Map map;
	// Handles rendering textures
	private SpriteBatch batch;
	
	public Engine(){
		tank1 = new Tank(true);
		tank2 = new Tank(false);
		
		map = new Map();
		
		batch = new SpriteBatch();
	}
	
	public void update(float delta){
		tank1.update(delta);
		tank2.update(delta);
		map.update(delta);
	}
	
	public void render(float delta){
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin rendering sprites in order from back to front
		batch.begin();
		//Drawing using the map.render() function didn't work. It's because somehow the batch.end() was being called before it or something (according to the error). This error also comes up when I uncomment the tank lines below
		batch.draw(map.region, 0, 0);
		//tank1.render(batch);
		//tank2.render(batch);
		batch.end();
	}
	
}
