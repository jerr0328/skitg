package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.ui.UIFireButton;
import edu.ucf.cop4331.skitg.ui.UISpinner;
import edu.ucf.cop4331.skitg.ui.UIWeaponSelector;

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
	// Player 3 - Testing
	//private Tank tank3;
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
	//private UISpinner moves;
	// Fire Button UI element
	private UIFireButton fire;
	// Weapon selector
	private UIWeaponSelector weaponSelector;
	
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
	
	// Temporary variables to prevent too much garbage
	private int tempAngle, tempPower;
	
	/**
	 * Initialize engine
	 */
	public Engine(){
		loadTextures();
		
		map = new Map();
		int tank1x = 400;
		int tank2x = 450;
		
		
		tank1 = new Tank(texTank, texWeapons, true, tank1x, map.getHeight(tank1x), map.getAngle(tank1x));
		tank2 = new Tank(texTank, texWeapons, false, tank2x, map.getHeight(tank2x), map.getAngle(tank2x));
		
		/*Third Tank for Testing Purposes
		int tank3x = 500;
		tank3 = new Tank(texTank, texWeapons, false, tank3x, map.getHeight(tank3x), map.getAngle(tank3x));
		*/
		
		angle = new UISpinner(texArrow,font,"Angle",60,360,50,0);
		power = new UISpinner(texArrow,font,"Power",50,100,200,0);
		// Probably need a different UI element for moving
		fire = new UIFireButton(texFireButton,400,0);
		weaponSelector = new UIWeaponSelector(texArrow,font,500,0,tank1.getWeapons());
		
		batch = new SpriteBatch();
	}
	
	/**
	 * Updates triggered often, should update all game elements
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		// TODO: If we're changing from player 1 to player 2, update the UI
		angle.update(delta);
		power.update(delta);
		fire.update(delta);
		weaponSelector.update(delta);
		
		// TODO: If the player pressed a button, we need to update that value in the tank
		tank1.update(delta);
		tank2.update(delta);
		
		//tank3.update(delta);
		
		map.update(delta);
	}
	
	/**
	 * Render the game
	 * @param delta Time elapsed
	 */
	public void render(float delta){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin rendering sprites in order from back to front
		batch.begin();
		map.render(batch);
		// Tank rendering not fully implemented yet
		tank1.render(batch);
		tank2.render(batch);
		//tank3.render(batch);
		// UI rendering not fully implemented yet
		angle.render(batch);
		power.render(batch);
		fire.render(batch);
		weaponSelector.render(batch);
		batch.end();
	}
	
	/**
	 * Load the textures, initialize the TextureRegions
	 */
	private void loadTextures(){
		texture = new Texture(Gdx.files.internal("assets/png.png"));
		texFireButton = new TextureRegion(texture, 0, 0, 64, 16);
		texArrow = new TextureRegion(texture, 65, 0, 8, 16);
		texTank = new TextureRegion(texture, 83, 0, 32, 16);
		texCannon = new TextureRegion(texture, 159, 0, 16, 8);
		
		// Set up weapons
		texWeapons = new TextureRegion[5];
		// BigShot
		texWeapons[0] = new TextureRegion(texture,116,0,16,16);
		// Shot
		texWeapons[1] = new TextureRegion(texture,133,0,8,8);
		// Sniper
		texWeapons[2] = new TextureRegion(texture,142,0,4,4);
		// Rocket
		texWeapons[3] = new TextureRegion(texture,147,0,8,16);
		// Laser
		texWeapons[4] = new TextureRegion(texture,156,0,2,8);
		
		font = new BitmapFont(Gdx.files.internal("assets/arial12.fnt"),
		         Gdx.files.internal("assets/arial12.png"), false);
	}
	
}
