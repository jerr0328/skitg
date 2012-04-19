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
		int tank1x = 200;
		int tank2x = 700;
		
		
		tank1 = new Tank(texTank, texCannon, texWeapons, true, tank1x, map.getHeight(tank1x), map.getAngle(tank1x), map);
		tank2 = new Tank(texTank, texCannon, texWeapons, false, tank2x, map.getHeight(tank2x), map.getAngle(tank2x), map);
		
		angle = new UISpinner(texArrow,font,"Angle",90,360,25,0);
		power = new UISpinner(texArrow,font,"Power",50,100,150,0);
		moves = new UIMove(texArrow,font,4,275,0);
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
			// If tank1's turn is done
			if(tank1.getState() == Tank.RECEIVING){
				setUIValues(tank2);
				setUIEnabled(true);
				tank1active = false;
				tank2.setState(Tank.WAITING);
				playerIndicator.setText("Player  2");
			}
			else if(tank1.getState() == Tank.WAITING){
				updateTankValues(tank1);
			}
		}
		else{
			// If tank2's turn is done
			if(tank2.getState() == Tank.RECEIVING){
				setUIValues(tank1);
				setUIEnabled(true);
				tank1active = true;
				tank1.setState(Tank.WAITING);
				playerIndicator.setText("Player  1");
			}
			else if(tank2.getState() == Tank.WAITING){
				updateTankValues(tank2);
			}
		}
		player1score.setText(""+tank1.getScore());
		player2score.setText(""+tank2.getScore());
		angle.update(delta);
		power.update(delta);
		fire.update(delta);
		moves.update(delta);
		weaponSelector.update(delta);
		
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
		// UI rendering not fully implemented yet
		angle.render(batch);
		power.render(batch);
		moves.render(batch);
		fire.render(batch);
		weaponSelector.render(batch);
		playerIndicator.render(batch);
		player1score.render(batch);
		player2score.render(batch);
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
		// TODO: Handle move
		tank.setActiveWeapon(weaponSelector.getActiveWeapon());
		if(fire.isPressed()){
			setUIEnabled(false);
			tank.fire();
			fire.setPressed(false);
		}
		
		if(moves.isPressed() > 0){
			setUIEnabled(false);
			if(tank.getMoves() > 0)
			{
				if(moves.isPressed() == 1)
					tank.move(true);
				else if(moves.isPressed() == 2)
					tank.move(false);
				
				tank.decMoves();
			}
			
			moves.unPress(false);
			setUIValues(tank);
			setUIEnabled(true);
		}
	}
	
}
