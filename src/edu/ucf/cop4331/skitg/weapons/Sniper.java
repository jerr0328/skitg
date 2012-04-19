package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;

public class Sniper extends Weapon{

	private TextureRegion tex;
	
	public Sniper(Tank shooter, TextureRegion tex) {
		super(shooter);
		this.tex = tex;
		
		// TODO Auto-generated constructor stub
	}

	public void update(float delta) 
	{
		
		// if statement to make sure that the shot is on the screen width wise 
				if(done == false  )
				{
					
					velocity.y += GRAVITY * delta;
					
<<<<<<< HEAD
					position.add(velocity.x *delta * POWER_FACTOR* 2.5f, velocity.y *delta* POWER_FACTOR);
=======
					position.add(velocity.x *delta * POWER_FACTOR, velocity.y *delta* POWER_FACTOR);
					bounds.setX(position.x);
					bounds.setY(position.y);
>>>>>>> refs/remotes/origin/master
					
					System.out.println("X: "+position.x+" Y: "+position.y);
					System.out.println("Xvel: "+velocity.x+" Yvel: "+velocity.y);
					
					if(detectTankCollision()){
						System.out.println("Direct hit!");
						// TODO: Explosions!
						shooter.score(100);
						done = true;
					}
					else{
						done = detectGroundCollision();
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
		
		
		velocity = new Vector2(shooter.getPower()* MathUtils.cosDeg(shooter.getAngle()) * POWER_FACTOR, shooter.getPower() * MathUtils.sinDeg(shooter.getAngle()) * POWER_FACTOR );
		
		position = new Vector2(shooter.getPosition());
		bounds = new Rectangle(position.x, position.y, 4,4);
				
		System.out.println("big shot fired");
		
		
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sniper";
	}

}
