package edu.ucf.cop4331.skitg;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.weapons.Weapon;

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
	
	public Tank(){
		
	}
	
	/**
	 * Initialize a tank with a flag for if this is the first tank.
	 * If this the first tank, the tank is in the waiting state, angle is 60, color is blue.
	 * If this is the second tank, the tank is in the receiving state, angle is 120, color is red.
	 * @param first True if this the first tank. False if this the second tank.
	 */
	public Tank(boolean first){
		if(!first){
			state = RECEIVING;
			angle = 120;
			color = Color.RED;
		}
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
		
	}
	
	/**
	 * Render the tank.
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		batch.setColor(color);
		batch.draw(tex, position.x, position.y);
		batch.setColor(Color.WHITE);
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
	 * Get the position of the tank
	 * @return
	 */
	public Vector2 getPosition(){
		return position;
	}
	
	public int getPower(){
		return power;
	}

}
