package edu.ucf.cop4331.skitg.weapons;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Map;
import edu.ucf.cop4331.skitg.Tank;



public class SingleShot extends Weapon
{

	
	private TextureRegion tex;
	

	
	public SingleShot(Tank shooter, TextureRegion tex) {
		super(shooter);
		
		this.tex = tex;
		
	}

	
	
	
	
	@Override
	public void update(float delta) 
	{
		// if statement to make sure that the shot is on the screen width wise 
		if(shot == true  )
		{
			position.add(velocity * delta, velocity * delta * Gravity);
			if(shot == true)
			{
				if(position.x > 800 || position.x < 800 )
				{
					shot = false;
				}
				
				
			}
				
		
		}

		
	}


	public void render(SpriteBatch batch) {
		// Draw
		batch.begin();
		batch.draw(tex, position.x, position.y);
		batch.end();
	}

	@Override
	public void shoot() {
		// converts the power in to a velocity
		velocity = shooter.getPower() * .6f;
		
		
		// sets the initioal position for the shot
		position = shooter.getPosition().add(velocity * shooter.getAngle(), velocity * shooter.getAngle());
		
		
		
	}

	@Override
	public String toString() 
	{
		return "Single Shot";
	}



}
