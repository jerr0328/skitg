package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Tank;

public abstract class Weapon {
	
	protected Vector2 position;
	protected float velocity;
	protected boolean shot = false;
	protected Tank shooter;
	
	public Weapon(Tank shooter){
		this.shooter = shooter;
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
	 * Get the icon of the weapon for the menu
	 * @return Icon texture
	 */
	public abstract TextureRegion getIcon();

	

}
