package edu.ucf.cop4331.skitg.weapons;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

/**
 * Single shot weapon
 * Scores 20 points on direct hit
 * 10 points for shots within 10 px
 * 5 points for shots within 20 px
 * 3 points for shots within 30 px
 * @author Jeremy Mayeres
 *
 */
public class SingleShot extends Weapon
{

	/**
	 * Create a Single Shot weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public SingleShot(Tank shooter, TextureRegion tex) {
		super(shooter, tex);
	}	

	@Override
	public void update(float delta) 
	{
		// As long as weapon hasn't finished
		if(!done)
		{
			updatePosition(delta,true);

			if(detectTankCollision()){
				shooter.score(20);
				done = true;
				map.destroyTerrain(30, (int)position.x, (int)position.y);
			}
			else if(detectGroundCollision()){
				if(detectExplosionRadius(10)){
					shooter.score(10);
				}
				else if(detectExplosionRadius(20)){
					shooter.score(5);
				}
				else if(detectExplosionRadius(30)){
					shooter.score(3);
				}
				map.destroyTerrain(30, (int)position.x, (int)position.y);

				done = true;
			}
		}
	}

	@Override
	public void shoot() {
		super.shoot();
		bounds.setHeight(8);
		bounds.setWidth(8);
	}

	@Override
	public String toString() 
	{
		return "Single Shot";
	}

}
