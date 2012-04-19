package edu.ucf.cop4331.skitg.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.Tank;

public class UIFireButton {
	private TextureRegion texture;
	private int x,y;
	private boolean enabled = true;
	private boolean pressed = false;
	private float stateTime = 0;
	
	public UIFireButton(TextureRegion tex, int x, int y){
		this.texture = tex;
		this.x = x;
		this.y = y;
	}
	
	public void update(float delta){
		
		
		if(Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
					
			if(stateTime > 0.2f)
			{
			
				stateTime = 0;
				
				if(x0 > x && x0 < x+64 && y0 > y && y0 < y + 16)
					pressed = true;
			}
			
			stateTime += delta;
		}
		
		
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
	
	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}
	

}
