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
		// if statement that looks to see that either tank fires a shot 
		//if(Tank.  )
		{
			
			position.add(velocity * delta, velocity * delta * Gravity);
		
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
		
		velocity = shooter.getPower() * .2f;
		
		position = shooter.getPosition().add(velocity * shooter.getAngle(), velocity * shooter.getAngle());
		
	}

	@Override
	public String toString() 
	{
		return "Single Shot";
	}



}
