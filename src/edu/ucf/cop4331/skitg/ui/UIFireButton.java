package edu.ucf.cop4331.skitg.ui;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UIFireButton {
	private TextureRegion texture;
	private int x,y;
	private boolean enabled = true;
	private boolean pressed = false;
	
	public UIFireButton(TextureRegion tex, int x, int y){
		this.texture = tex;
		this.x = x;
		this.y = y;
	}
	
	public void update(float delta){
		if(enabled){
			// TODO: Check if pressed
		}
	}
	
	public void render(SpriteBatch batch){
		if(!enabled){
			batch.setColor(1,1,1,0.5f);
		}
		batch.draw(texture,x,y);
		batch.setColor(Color.WHITE);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isPressed() {
		return pressed;
	}
	

}
