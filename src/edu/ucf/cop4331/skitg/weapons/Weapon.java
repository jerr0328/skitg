package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import edu.ucf.cop4331.skitg.Tank;

public abstract class Weapon {
	
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected boolean done = false;
	protected Tank shooter;
	protected float Gravity = -9.8f;
	

	
	public Weapon(Tank shooter){
		this.shooter = shooter;
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

}
