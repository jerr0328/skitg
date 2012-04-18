package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

public class BigShot extends Weapon
{
	
	private TextureRegion tex;

	public BigShot(Tank shooter, TextureRegion tex) {
		super(shooter);
		
		this.tex = tex;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
	
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	
	public String toString() 
	{
		return "Big Shot";
	}



}
