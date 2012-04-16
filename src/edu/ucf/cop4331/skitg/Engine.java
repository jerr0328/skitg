package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	// Angle UI element
	private UISpinner angle;
	// Power UI element
	private UISpinner power;
	// Moves UI element
	private UISpinner moves;
	
	// Texture stuff
	
	// Texture file
	private Texture texture;
	// Arrow
	private TextureRegion texArrow;
	// Cannon
	private TextureRegion texCannon;
	// Fire Button
	private TextureRegion texFireButton;
	// Tank
	private TextureRegion texTank;
	// Weapons
	private TextureRegion[] texWeapons;
	// Font
	private BitmapFont font;
	
	/**
	 * Initialize engine
	 */
	public Engine(){
		loadTextures();
		
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
		//Drawing using the map.render() function didn't work. It's because somehow the batch.end() was being called before it or something (according to the error). This error also comes up when I uncomment the tank lines below
		batch.draw(map.region, 0, 0);
		//tank1.render(batch);
		//tank2.render(batch);
		batch.end();
	}
	
	/**
	 * Load the textures, initialize the TextureRegions
	 */
	private void loadTextures(){
		texture = new Texture(Gdx.files.internal("assets/png.png"));
		// TODO: Set up each TextureRegion
	}
	
}
