package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Tank;


public class SingleShot extends Weapon
{

	public SingleShot(Tank shooter) {
		super(shooter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float delta) {
		
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// Draw
		Texture pic;
		pic = new Texture(Gdx.files.internal("image.png"));
		batch.begin();
		batch.draw(pic, 10, 10);
		batch.end();
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() 
	{
		return "Single Shot";
	}

	/*public TextureRegion getIcon(SpriteBatch batch) {
		TextureRegion region;
		Texture pic;

		pic = new Texture(Gdx.files.internal("image.png"));
		region = new TextureRegion(pic, 20, 20, 50, 50);

		batch.begin();
		batch.draw(region, 10, 10);
		batch.end();
		return null;
	}
	*/

	@Override
	public TextureRegion getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
