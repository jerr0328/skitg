package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Engine;
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

	private boolean seeking = false;
	private Vector2 otherTankPosition;
	
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
		if(!done)
		{
			updatePosition(delta);

			if(detectTankCollision()){
				shooter.score(30);
				done = true;
				map.destroyTerrain(30, (int)position.x, (int)position.y);
			}
			else if(detectGroundCollision()){
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
		otherTankPosition = Engine.getInstance().getOtherTank(shooter).getPosition();
	}

	@Override
	public String toString() {
		return "Heat Seeker";
	}
	
	private void updatePosition(float delta){
		if(!seeking){
			super.updatePosition(delta, true);
			if(velocity.y < 0){
				seeking = true;
			}
		}
		else{
			velocity.set(otherTankPosition);
			velocity.sub(position).nor().mul(50);
	        position.add(velocity.x * delta * POWER_FACTOR, velocity.y * delta * POWER_FACTOR);
	        bounds.setX(position.x);
			bounds.setY(position.y);
		}
		boundsCheck();
	}

}
