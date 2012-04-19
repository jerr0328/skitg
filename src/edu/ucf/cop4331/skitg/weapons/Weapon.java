package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Map;
import edu.ucf.cop4331.skitg.Tank;

public abstract class Weapon {
	
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected boolean done = false;
	protected boolean hitGround = false;
	protected Tank shooter;
	protected Map map;
	protected final float GRAVITY = -9.8f;
	protected final float POWER_FACTOR = 1.1f;
	

	
	public Weapon(Tank shooter, Map map){
		this.shooter = shooter;
		this.map = map;
	}
	
	
	public boolean isDone(){
		return done;
	}
	
	/**
	 * Update is called by the tank often when shot
	 * @param delta Time elapsed during update
	 */
	public abstract void update(float delta);
	
	/**
	 * Render the shot
	 * @param batch Batch handler
	 */
	public abstract void render(SpriteBatch batch);
	
	/**
	 * Shoot the weapon
	 */
	public abstract void shoot();
	
	/**
	 * Get the name of the weapon
	 * @return Name of the weapon
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Detects collisions with the ground and tank
	 * @return True if weapon collided with ground
	 */
	
	public boolean detectGroundCollision(){
		if((position.y < shooter.getPosition().y - 1 || position.y > shooter.getPosition().y + 1) && (int)position.y == map.getHeight((int)position.x)){
			return true;
			// TODO: Alter map to accommodate
		}
		return false;
	}
	
}
