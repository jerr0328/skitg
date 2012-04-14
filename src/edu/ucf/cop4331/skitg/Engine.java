package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.ucf.cop4331.skitg.ui.UISpinner;

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
	// True if Tank 1 is up, False is Tank 2 is up
	private boolean tank1active = true;
	// Texture file
	private Texture texture;
	// Font
	private BitmapFont font;
	// Angle UI element
	private UISpinner angle;
	// Power UI element
	private UISpinner power;
	// Moves UI element
	private UISpinner moves;
	
	/**
	 * Initialize engine
	 */
	public Engine(){
		tank1 = new Tank(true);
		tank2 = new Tank(false);
		
		map = new Map();
		
		batch = new SpriteBatch();
		
	}
	
	/**
	 * Updates triggered often, should update all game elements
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		tank1.update(delta);
		tank2.update(delta);
		map.update(delta);
	}
	
	/**
	 * Render the game
	 * @param delta Time elapsed
	 */
	public void render(float delta){
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin rendering sprites in order from back to front
		batch.begin();
		map.render(batch);
		tank1.render(batch);
		tank2.render(batch);
		batch.end();
	}
	
}
