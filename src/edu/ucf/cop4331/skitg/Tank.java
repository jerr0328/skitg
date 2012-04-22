package edu.ucf.cop4331.skitg;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.weapons.BigShot;
import edu.ucf.cop4331.skitg.weapons.HeatSeeker;
import edu.ucf.cop4331.skitg.weapons.Laser;
import edu.ucf.cop4331.skitg.weapons.SingleShot;
import edu.ucf.cop4331.skitg.weapons.Sniper;
import edu.ucf.cop4331.skitg.weapons.Weapon;

/**
 * Handles the tanks in game, represents a player
 * @author Jeremy Mayeres
 *
 */
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
	/**
	 * Tank state: Moving
	 */
	static final int MOVING = 3;
	
	// X,Y position
	private Vector2 position;
	// Angle in degrees from origin (0 degrees faces right side of screen, 90 degrees is straight up)
	private int angle = 60;
	// Power of shot from 0-100
	private int power = 50;
	// Number of moves tank can make
	private int moves = MAX_MOVES;
	// Number of pixels per move
	private int pixelsPerMove = 50;
	// Desired Position when moving
	private int desiredPosition = 100;
	// Current score
	private int score;
	// Available weapons
	private List<Weapon> weapons;
	// Tank hitbox
	private Rectangle bounds;
	// Texture of tank
	private TextureRegion tex;
	// Texture of cannon
	private TextureRegion cannon;
	// Tank state
	private int state = WAITING;
	// Tank color
	private Color color = Color.BLUE;
	// Index of active weapon
	private int activeWeapon;
	// The rotation of the tank
	private float slope;
	// The map
	private Map map;
	
	/**
	 * Initialize a tank with a flag for if this is the first tank.
	 * If this the first tank, the tank is in the waiting state, angle is 60, color is blue.
	 * If this is the second tank, the tank is in the receiving state, angle is 120, color is red.
	 * @param first True if this the first tank. False if this the second tank.
	 */
	public Tank(TextureRegion tex, TextureRegion cannon, TextureRegion[] weaponsTex, boolean first, int x, int y, Map map){
		this.tex = tex;
		this.cannon = cannon;
		this.position = new Vector2(x,y);
		this.bounds = new Rectangle(x,y,32,16);
		this.slope = map.getAngle(x);
		this.map = map;
		if(!first){
			state = RECEIVING;
			angle = 120;
			color = Color.RED;
		}
		
		// Load the weapons
		weapons = new ArrayList<Weapon>(5);
		weapons.add(new BigShot(this, weaponsTex[0]));
		weapons.add(new SingleShot(this, weaponsTex[1]));
		weapons.add(new Sniper(this, weaponsTex[2]));
		weapons.add(new HeatSeeker(this, weaponsTex[3]));
		weapons.add(new Laser(this, weaponsTex[4]));
	}
	
	/**
	 * Add to score by amount
	 * @param points Points to add (use negative to remove)
	 */
	public void score(int points){
		this.score += points;
	}
	
	/**
	 * Update the tank
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		if(map.isChanged()){
			position.y = map.getPeaksY((int)position.x);
			slope = map.getAngle((int)position.x);
		}
		
		if(state == SHOOTING){
			if(weapons.get(activeWeapon).isDone()){
				weapons.remove(activeWeapon);
				activeWeapon = 0;
				state = RECEIVING;
			} else{
				weapons.get(activeWeapon).update(delta);
			}
		}
		else if(state == MOVING) {
			if((int)position.x == desiredPosition){
				state = WAITING;
			}
			else {
				// DEBUG: System.out.println("Position: "+position.x + "  Desired: " + desiredPosition);
				if(position.x > desiredPosition)
					position.x -= 4*delta;
				else
					position.x += 4*delta;
				
				position.y = map.getPeaksY((int)position.x);
				slope = map.getAngle((int)position.x);
			}			
		}
	}
	
	/**
	 * Render the tank.
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		batch.setColor(color);
		batch.draw(cannon, position.x-4, position.y + 12, 4, 0, 8, 16, 1, 1, angle - 90, true);
		batch.draw(tex, position.x, position.y-16, 0, 16, 16, 32, 1, 1, slope, true); //To rotate tank depending on its position on the map
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
		desiredPosition = (int)position.x;
		if(moves > 0){
			if(left){
				desiredPosition -= pixelsPerMove;
			}
			else {
				desiredPosition += pixelsPerMove;
			}
			
			if(desiredPosition < 32)
				desiredPosition = 32;
			if(desiredPosition > Skitg.WIDTH - 32)
				desiredPosition = Skitg.WIDTH - 32;
			
			if(desiredPosition == (int)position.x)
				return false;
			
			state = MOVING;
			return true;
		}
		return false;
	}
	
	/**
	 * Fire the active weapon
	 */
	public void fire(){
		weapons.get(activeWeapon).shoot();
		state = SHOOTING;
	}
	
	/**
	 * Get the position of the tank
	 * @return Vector representing the position
	 */
	public Vector2 getPosition(){
		return position;
	}
	
	/**
	 * Get the firing power
	 * @return Firing power (0-100)
	 */
	public int getPower(){
		return power;
	}
	
	/**
	 * Set the firing power
	 * @param power Firing power (0-100)
	 */
	public void setPower(int power){
		this.power = power;
	}
	
	/**
	 * Get the weapons list
	 * @return List of weapons
	 */
	public List<Weapon> getWeapons(){
		return weapons;
	}
	
	/**
	 * Get the index of the active weapon
	 * @return Index of active weapon in list
	 */
	public int getActiveWeapon(){
		return activeWeapon;
	}
	
	/**
	 * Set the active weapon
	 * @param activeWeapon Index of active weapon in list
	 */
	public void setActiveWeapon(int activeWeapon){
		this.activeWeapon = activeWeapon;
	}
	
	/**
	 * Get the tank's state
	 * @return State of tank
	 */
	public int getState(){
		return state;
	}
	
	/**
	 * Set the tank's state
	 * @param state State of the tank
	 */
	public void setState(int state){
		this.state = state;
	}
	
	/**
	 * Get the tank's firing angle
	 * @return Firing angle (0-359)
	 */
	public int getAngle(){
		return angle;
	}
	
	/**
	 * Set the tank's firing angle
	 * @param angle Firing angle (0-359)
	 */
	public void setAngle(int angle){
		this.angle = angle;
	}
	
	/**
	 * Get the remaining moves
	 * @return Moves remaining
	 */
	public int getMoves(){
		return moves;
	}
	
	/**
	 * Get the player's score
	 * @return Score for this tank/player
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Decrease the amount of remaining moves
	 */
	public void decMoves(){
		moves -= 1;
	}
	
	/**
	 * Get the tank's bounds (hitbox)
	 * @return Rectangle representing the tank's hitbox
	 */
	public Rectangle getBounds(){
		return bounds;
	}

}
