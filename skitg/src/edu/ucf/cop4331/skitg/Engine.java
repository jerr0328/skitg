package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.ui.UIFireButton;
import edu.ucf.cop4331.skitg.ui.UIMove;
import edu.ucf.cop4331.skitg.ui.UISpinner;
import edu.ucf.cop4331.skitg.ui.UIText;
import edu.ucf.cop4331.skitg.ui.UIWeaponSelector;

/**
 * Handles game logic and texture loading
 * @author Jeremy Mayeres
 *
 */
public class Engine {
	
	// Singleton
	private static Engine instance = null;
	
	// Player 1
	private Tank tank1;
	// Player 2
	private Tank tank2;
	// Game map
	private Map map;
	// Handles rendering textures
	private SpriteBatch batch;
	// True if Tank 1 is up, False is Tank 2 is up
	private boolean tank1active;
	// Angle UI element
	private UISpinner angle;
	// Power UI element
	private UISpinner power;
	// Moves UI element
	private UIMove moves;
	// Fire Button UI element
	private UIFireButton fire;
	// Weapon selector
	private UIWeaponSelector weaponSelector;
	// Active player indicator
	private UIText playerIndicator;
	// Player 1 score
	private UIText player1score;
	// Player 2 score
	private UIText player2score;
	// Volleys remaining
	private int volleys;
	// Game over?
	private boolean gameOver;
	
	// Textures
	
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
	 * Get an instance of the engine.
	 * Creates a new instance if one doesn't exist.
	 * @return Singleton instance of engine
	 */
	public static Engine getInstance(){
		if(instance == null){
			instance = new Engine();
		}
		return instance;
	}
	
	/**
	 * Private constructor since we are using Singleton pattern
	 */
	private Engine(){
	}
	
	/**
	 * Initialize engine
	 */
	public void initialize(){
		loadTextures();
		
		map = new Map();
		
		// Place tanks at the bottom of the valley
		int tank1x = map.getMinimum(true);
		int tank2x = map.getMinimum(false);
		
		tank1 = new Tank(texTank, texCannon, texWeapons, true, tank1x, map.getPeaksY(tank1x), map);
		tank2 = new Tank(texTank, texCannon, texWeapons, false, tank2x, map.getPeaksY(tank2x), map);
		
		tank1active = true;
		volleys = 5;
		gameOver = false;
		
		// Initialize UI
		angle = new UISpinner(texArrow,font,"Angle",tank1.getAngle(),360,25,0);
		power = new UISpinner(texArrow,font,"Power",tank1.getPower(),100,150,0);
		moves = new UIMove(texArrow,font,tank1.getMoves(),275,0);
		fire = new UIFireButton(texFireButton,400,0);
		weaponSelector = new UIWeaponSelector(texArrow,font,500,0,tank1.getWeapons());
		playerIndicator = new UIText(font,"Player  1",Color.BLACK,380,Skitg.HEIGHT);
		player1score = new UIText(font,"0",Color.BLUE,100,Skitg.HEIGHT);
		player2score = new UIText(font,"0",Color.RED,700,Skitg.HEIGHT);
		
		batch = new SpriteBatch();
	}
	
	/**
	 * Updates triggered often, should update all game elements
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		if(tank1active){
			// If tank1's turn is done, activate tank2
			if(tank1.getState() == Tank.RECEIVING){
				setUIValues(tank2);
				setUIEnabled(true);
				tank1active = false;
				tank2.setState(Tank.WAITING);
				playerIndicator.setText("Player  2");
			}
			else if(tank1.getState() == Tank.WAITING && !gameOver){
				updateTankValues(tank1);
			}
		}
		else{
			// If tank2's turn is done, activate tank1
			if(tank2.getState() == Tank.RECEIVING && !gameOver){
				// At the end of tank2's turn, we need to decrease the remaining volleys
				volleys--;
				if(volleys == 0){
					// If the game is over, we can stop drawing the controls
					gameOver = true;
					playerIndicator.setText("Game over!");
				}else{
					setUIValues(tank1);
					setUIEnabled(true);
					tank1active = true;
					tank1.setState(Tank.WAITING);
					playerIndicator.setText("Player  1");
				}
			}
			else if(tank2.getState() == Tank.WAITING){
				updateTankValues(tank2);
			}
		}
		
		player1score.setText(""+tank1.getScore());
		player2score.setText(""+tank2.getScore());
		
		// Update the controls unless the game is over
		if(!gameOver){
			angle.update(delta);
			power.update(delta);
			fire.update(delta);
			moves.update(delta);
			weaponSelector.update(delta);
		}
		
		map.update(delta);
		
		tank1.update(delta);
		tank2.update(delta);
	}
	
	/**
	 * Render the game
	 * @param delta Time elapsed
	 */
	public void render(float delta){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// Begin rendering in order from back to front
		batch.begin();
		
		map.render(batch);

		tank1.render(batch);
		tank2.render(batch);
		
		// Render the controls unless the game is over
		if(!gameOver){
			angle.render(batch);
			power.render(batch);
			moves.render(batch);
			fire.render(batch);
			weaponSelector.render(batch);
		}
		
		playerIndicator.render(batch);
		player1score.render(batch);
		player2score.render(batch);
		
		// Send to video controller to be rendered
		batch.end();
	}
	
	/**
	 * Load the textures, initialize the TextureRegions
	 */
	private void loadTextures(){
		texture = new Texture(Gdx.files.internal("data/png.png"));
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
		
		font = new BitmapFont(Gdx.files.internal("data/arial12.fnt"),
		         Gdx.files.internal("data/arial12.png"), false);
	}
	
	/**
	 * Enabled or disables UI elements
	 * @param flag True to enable, false to disable
	 */
	private void setUIEnabled(boolean flag){
		angle.setEnabled(flag);
		power.setEnabled(flag);
		moves.setEnabled(flag);
		fire.setEnabled(flag);
		weaponSelector.setEnabled(flag);
	}
	
	/**
	 * Set the UI values to the values from the tank
	 * @param tank Tank to get the values from
	 */
	private void setUIValues(Tank tank){
		angle.setValue(tank.getAngle());
		power.setValue(tank.getPower());
		moves.setValue(tank.getMoves());
		weaponSelector.setWeapons(tank.getWeapons());
		weaponSelector.setActiveWeapon(tank.getActiveWeapon());
	}
	
	/**
	 * Updates tank's values from the UI
	 * @param tank Tank to update
	 */
	private void updateTankValues(Tank tank){
		tank.setAngle(angle.getValue());
		tank.setPower(power.getValue());
		tank.setActiveWeapon(weaponSelector.getActiveWeapon());
		
		// Handle firing
		if(fire.isPressed()){
			setUIEnabled(false);
			tank.fire();
			fire.setPressed(false);
		}
		
		// Handles move
		if(moves.getPressed() != UIMove.NONE){
			setUIEnabled(false);
			if(tank.getMoves() > 0)
			{
				if(moves.getPressed() == UIMove.LEFT)
					tank.move(true);
				else if(moves.getPressed() == UIMove.RIGHT)
					tank.move(false);
				
				tank.decMoves();
			}
			
			moves.unPress();
			setUIValues(tank);
			setUIEnabled(true);
		}
	}
	
	/**
	 * Return a reference to the map
	 * @return The map
	 */
	public Map getMap(){
		return map;
	}
	
	/**
	 * Get the reference of the other tank
	 * @param t Tank we don't want
	 * @return Tank that isn't t
	 */
	public Tank getOtherTank(Tank t){
		if(t != tank1){
			return tank1;
		}
		return tank2;
	}
	
}
