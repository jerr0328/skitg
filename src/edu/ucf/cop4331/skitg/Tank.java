package edu.ucf.cop4331.skitg;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.weapons.*;

public class Tank {
	
	/**
	 * Maximum times tank can move
	 */
	static final int MAX_MOVES = 4;
	
	/**
	 * Tank state: Other tank's turn
	 */
	static final int RECEIVING = 0;
	/**
	 * Tank state: Waiting on user input
	 */
	static final int WAITING = 1;
	/**
	 * Tank state: Fired weapon
	 */
	static final int SHOOTING = 2;
	
	// X,Y position
	private Vector2 position;
	// Angle in degrees from origin (0 degrees faces right side of screen, 90 degrees is straight up)
	private int angle = 60;
	// Power of shot from 0-100
	private int power;
	// Number of moves tank can make
	private int moves = MAX_MOVES;
	// Current score
	private int score;
	// Available weapons
	private List<Weapon> weapons;
	// Hit box for tank (x,y) at bottom-left corner, plus width and height (right and up, respectively)
	private Rectangle bounds;
	// Texture of tank
	private TextureRegion tex;
	// Tank state
	private int state = WAITING;
	// Tank color
	private Color color = Color.BLUE;
	// Index of active weapon
	private int activeWeapon;
	//The rotation of the tank
	private float slope;
	
	/**
	 * Initialize a tank with a flag for if this is the first tank.
	 * If this the first tank, the tank is in the waiting state, angle is 60, color is blue.
	 * If this is the second tank, the tank is in the receiving state, angle is 120, color is red.
	 * @param first True if this the first tank. False if this the second tank.
	 */
	public Tank(TextureRegion tex, TextureRegion[] weaponsTex, boolean first, int x, int y, float slope){
		this.tex = tex;
		this.position = new Vector2(x,y);
		this.slope = slope;
		if(!first){
			state = RECEIVING;
			angle = 120;
			color = Color.RED;
		}
		
		// TODO: Load ALL the weapons!
		weapons = new ArrayList<Weapon>(5);
		weapons.add(new BigShot(this, weaponsTex[0]));
		weapons.add(new SingleShot(this, weaponsTex[1]));
		weapons.add(new Sniper(this, weaponsTex[2]));
		weapons.add(new HeatSeeker(this, weaponsTex[3]));
		weapons.add(new Laser(this, weaponsTex[4]));
	}
	
	/**
	 * Register a hit by the weapon
	 * @param weapon Weapon hitting the tank
	 */
	public void hit(Weapon weapon){
		
	}
	
	/**
	 * Update the tank
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		if(state == SHOOTING){
			if(weapons.get(activeWeapon).isDone()){
				weapons.remove(activeWeapon);
				activeWeapon = 0;
				state = RECEIVING;
			} else{
				weapons.get(activeWeapon).update(delta);
			}
		}
	}
	
	/**
	 * Render the tank.
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		batch.setColor(color);
		//batch.draw(tex, position.x, position.y);
		//System.out.println("Slope: " + slope);
		//0 degrees - Vertical
		//90 degrees- Horizontal
		batch.draw(tex, position.x, position.y, 8, 8, 16, 32, 1, 1, slope, true); //To rotate tank depending on its position on the map
		batch.setColor(Color.WHITE);
		if(state == SHOOTING){
			weapons.get(activeWeapon).render(batch);
		}
	}
	
	/**
	 * Moves tank if possible.
	 * @param left True to move left, false to move right.
	 * @return True if move was successful.
	 */
	public boolean move(boolean left){
		if(moves > 0){
			if(left){
				// TODO: Move left
			}
			else {
				// TODO: Move right
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Fire the active weapon
	 */
	public void fire(){
		// TODO: Fire weapon
		weapons.get(activeWeapon).shoot();
		state = SHOOTING;
	}
	
	/**
	 * Get the position of the tank
	 * @return
	 */
	public Vector2 getPosition(){
		return position;
	}
	
	public int getPower(){
		return power;
	}
	
	public void setPower(int power){
		this.power = power;
	}
	
	public List<Weapon> getWeapons(){
		return weapons;
	}
	
	public int getActiveWeapon(){
		return activeWeapon;
	}
	
	public void setActiveWeapon(int activeWeapon){
		this.activeWeapon = activeWeapon;
	}
	
	public int getState(){
		return state;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public void setAngle(int angle){
		this.angle = angle;
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void setState(int state){
		this.state = state;
	}

}
