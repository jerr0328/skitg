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
		
		tank1 = new Tank(texTank, true);
		tank2 = new Tank(texTank, false);
		
		angle = new UISpinner(texArrow,font,"Angle",60,360,0,0);
		power = new UISpinner(texArrow,font,"Power",50,100,200,0);
		// Probably need a different UI element for moving
		
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
		// Tank rendering not fully implemented yet
		//tank1.render(batch);
		//tank2.render(batch);
		// UI rendering not fully implemented yet
		//angle.render(batch);
		//power.render(batch);
		batch.end();
	}
	
	/**
	 * Load the textures, initialize the TextureRegions
	 */
	private void loadTextures(){
		//texture = new Texture(Gdx.files.internal("assets/png.png"));
		// TODO: Set up each TextureRegion
	}
	
}
