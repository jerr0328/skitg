package edu.ucf.cop4331.skitg.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Weapon {
	
	
	public abstract void update(float delta);
	public abstract void render(SpriteBatch batch);
	public abstract String toString();
	

}
