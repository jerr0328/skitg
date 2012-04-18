package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;

public class Laser extends Weapon {
	
	
	private TextureRegion tex;

	public Laser(Tank shooter, Texture tex) {
		super(shooter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// Draw
		batch.begin();
		batch.draw(tex, position.x, position.y);
		batch.end();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Laser";
	}

}
