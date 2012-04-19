package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;

/**
 * Spinner for a data value (arrows to increase/decrease value)
 * @author Jeremy Mayeres
 *
 */
public class UISpinner {
	
	// Texture for the arrows
	private TextureRegion arrow;
	// Text describing this element
	private String text;
	// Value of the element
	private int value;
	// Maximum value element can have
	private int max;
	// Coordinates of UI element on the screen
	private int x,y;
	// True if enabled, false if disabled
	private boolean enabled = true;
	// Font
	private BitmapFont font;
	//Timer for update call
	private float stateTime = 0;
	
	public UISpinner(TextureRegion arrow, BitmapFont font, String text, int value, int max, int x, int y){
		this.arrow = arrow;
		this.font = font;
		this.text = text;
		this.value = value;
		this.max = max;
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch batch){
		if(enabled){
			batch.setColor(Color.BLUE);
			font.setColor(1, 1, 1, 1);
		}
		else{
			font.setColor(1, 1, 1, 0.5f);
		}
		batch.draw(arrow,x,y,4,8,8,16,1,1,180); // Draw left arrow
		font.draw(batch, text+": "+value, x+16, y+14);
		batch.draw(arrow,x+80,y-1); // Draw right arrow
		batch.setColor(Color.WHITE);
	}
	
	public void update(float delta){
		// TODO: Handle the input, update values (do bounds checking), and set/reset timer
		// Timer is set to like 0.3s, without timer it a single press by the user may increase/decrease value
		// by many points.
				
		if(Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
					
			if(stateTime > 0.1f)
			{
			
				stateTime = 0;
				
				if(x0 > x && x0 < x+8 && y0 > y && y0 < y + 16 && value > 0)
					value = value - 1;
				else if(x0 > x+80 && x0 < x+88  && y0 > y && y0 < y + 16 && value < max)
					value = value + 1;
			}
			
			stateTime += delta;
		}
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setEnabled(boolean flag){
		this.enabled = flag;
	}

}
