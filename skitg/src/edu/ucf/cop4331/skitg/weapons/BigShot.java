package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

/**
 * Big shot weapon
 * Scores 40 points on direct hit
 * 20 points for shots within 20 px
 * 10 points for shots within 40 px
 * 6 points for shots within 60 px
 * @author Jeremy Mayeres
 *
 */
public class BigShot extends Weapon
{
	
	/**
	 * Create the weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public BigShot(Tank shooter, TextureRegion tex) {
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
				shooter.score(40);
				done = true;
				map.destroyTerrain(60, (int)position.x, (int)position.y);
			}
			else if(detectGroundCollision()){
				if(detectExplosionRadius(20)){
					shooter.score(20);
				}
				else if(detectExplosionRadius(40)){
					shooter.score(10);
				}
				else if(detectExplosionRadius(60)){
					shooter.score(6);
				}				
				done = true;
				map.destroyTerrain(60, (int)position.x, (int)position.y);
			}
		}
	}

	@Override
	public void shoot() {
		super.shoot();
		bounds.setHeight(16);
		bounds.setWidth(16);		
	}
	
	@Override
	public String toString() 
	{
		return "Big Shot";
	}



}
