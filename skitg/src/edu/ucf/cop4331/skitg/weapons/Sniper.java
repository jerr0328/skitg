package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

/**
 * Sniper weapon
 * Scores 100 points on direct hit.
 * No explosions.
 * @author Jeremy Mayeres
 *
 */
public class Sniper extends Weapon{

	/**
	 * Create a Sniper weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public Sniper(Tank shooter, TextureRegion tex) {
		super(shooter,tex);
	}

	@Override
	public void update(float delta) 
	{
		// As long as weapon hasn't finished
		if(!done)
		{
			updatePosition(delta,true);

			if(detectTankCollision()){
				shooter.score(100);
				done = true;
			}
			else if(!done){
				done = detectGroundCollision();
			}	
		}
	}

	@Override
	public void shoot() {
		super.shoot();
		bounds.setHeight(4);
		bounds.setWidth(4);
	}

	@Override
	public String toString() {
		return "Sniper";
	}

}
