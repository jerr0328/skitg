package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;

public class BigShot extends Weapon
{
	
	private TextureRegion tex;

	public BigShot(Tank shooter, TextureRegion tex) {
		super(shooter);
		
		this.tex = tex;
		// TODO Auto-generated constructor stub
	}

	public void update(float delta) 
	{
		
		System.out.println(position.x);
		System.out.println(position.y);
		// if statement to make sure that the shot is on the screen width wise 
				if(done == false  )
				{
					position.add(velocity * delta * MathUtils.cosDeg(shooter.getAngle() + 180), (velocity*MathUtils.sinDeg(shooter.getAngle() + 180))- (.5f* Gravity));
					
					System.out.println(position.x);
					System.out.println(position.y);
					
					if(done == false)
					{
						if(position.x > Skitg.WIDTH || position.x < 0 || position.y < 0)
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
		
		
		velocity = shooter.getPower();
		
		position = new Vector2(shooter.getPosition());
		
		position.x = (position.x);// + velocity * MathUtils.cosDeg(shooter.getAngle());
		position.y = (position.y); //+  velocity * 1.8f * MathUtils.sinDeg(shooter.getAngle());
				
		System.out.println("big shot fired");
		
		
	}


	
	public String toString() 
	{
		return "Big Shot";
	}



}
