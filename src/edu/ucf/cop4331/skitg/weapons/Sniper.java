package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;

public class Sniper extends Weapon{

	private TextureRegion tex;
	
	public Sniper(Tank shooter, TextureRegion weaponsTex) {
		super(shooter);
		// TODO Auto-generated constructor stub
	}

	public void update(float delta) 
	{
		// if statement to make sure that the shot is on the screen width wise 
				if(done == false  )
				{
					position.add(velocity * delta, velocity * delta * Gravity);
					if(done == false)
					{
						if(position.x > Skitg.WIDTH || position.x < 0)
						{
							done = true;
						}
						
						
					}
						
				
				}

		
		
	}


	public void render(SpriteBatch batch) {
		// Draw
		batch.draw(tex, position.x, position.y);
		
	}

	@Override
	public void shoot() {
		
		velocity = shooter.getPower() * .2f;
		
		position = shooter.getPosition().add(velocity * shooter.getAngle(), velocity * shooter.getAngle());
		
		System.out.println("sniper fired");
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sniper";
	}

}
