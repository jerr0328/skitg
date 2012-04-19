package edu.ucf.cop4331.skitg.weapons;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Map;
import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;


public class SingleShot extends Weapon
{

	
	private TextureRegion tex;
	

	
	public SingleShot(Tank shooter, TextureRegion tex) {
		super(shooter);
		
		this.tex = tex;
		
	}

	
	
	
	
	public void update(float delta) 
	{
		
		// if statement to make sure that the shot is on the screen width wise 
				if(done == false  )
				{
					
					velocity.y += GRAVITY * delta;
					
					position.add(velocity.x *delta * POWER_FACTOR, velocity.y *delta * POWER_FACTOR);
					
					System.out.println("X: "+position.x+" Y: "+position.y);
					System.out.println("Xvel: "+velocity.x+" Yvel: "+velocity.y);
					hitGround = detectGroundCollision();
					if(hitGround){
						
					}
					if(done == false)
					{
						if(position.x > Skitg.WIDTH || position.x < 0 || position.y < 0 || position.y > 1000)
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
		
		
		velocity = new Vector2(shooter.getPower()* MathUtils.cosDeg(shooter.getAngle()) * POWER_FACTOR , shooter.getPower() * MathUtils.sinDeg(shooter.getAngle()) *POWER_FACTOR);
		
		position = new Vector2(shooter.getPosition());
				
		System.out.println("big shot fired");
		
		
	}


	@Override
	public String toString() 
	{
		return "Single Shot";
	}



}
