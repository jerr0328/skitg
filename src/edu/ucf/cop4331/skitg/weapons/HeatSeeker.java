package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

/**
 * Heatseeking weapon
 * When enemy tank is within range, missile will attempt to go to enemy tank.
 * Scores 30 points on a direct hit.
 * 10 points for landing within 20 px.
 * 5 points for landing within 30 px.
 * @author Jeremy Mayeres
 *
 */
public class HeatSeeker extends Weapon {

	/**
	 * Create a HeatSeeker weapon
	 * @param shooter Shooter tank
	 * @param tex Texture of weapon
	 */
	public HeatSeeker(Tank shooter, TextureRegion tex) {
		super(shooter,tex);
	}

	@Override
	public void update(float delta) 
	{

		// As long as weapon hasn't finished
		if(done == false  )
		{
			updatePosition(delta,true);

			// TODO: Actually heatseek

			if(detectTankCollision()){
				shooter.score(30);
				done = true;
			}
			else if(detectGroundCollision()){
				System.out.println("Hit ground!");
				if(detectExplosionRadius(20)){
					shooter.score(10);
				}
				else if(detectExplosionRadius(30)){
					shooter.score(5);
				}
				done = true;
				map.destroyTerrain(30, (int)position.x, (int)position.y);
			}
		}
	}

	@Override
	public void shoot() {
		super.shoot();
		bounds.setWidth(8);
		bounds.setHeight(16);
	}


	@Override
	public String toString() {
		return "Heat Seeker";
	}

}
