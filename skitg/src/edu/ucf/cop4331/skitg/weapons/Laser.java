package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

/**
 * Laser weapon
 * Scores 20 points on direct hit.
 * Goes through the ground, not affected by gravity
 * @author Jeremy Mayeres
 *
 */
public class Laser extends Weapon {

	/**
	 * Create a Laser weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public Laser(Tank shooter, TextureRegion tex) {
		super(shooter,tex);
		color = shooter.getColor();
	}

	public void update(float delta) 
	{
		// As long as weapon hasn't finished
		if(!done)
		{
			updatePosition(delta,false);

			if(detectTankCollision()){
				shooter.score(20);
				done = true;
			}
		}
	}

	public void shoot() {
		super.shoot();
		bounds.setHeight(8);
		bounds.setWidth(2);
	}

	
	public String toString() {
		return "Laser";
	}

}
