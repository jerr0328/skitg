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
public class UISpinner extends UIComponent{
	
	// Text describing this element
	private String text;
	// Value of the element
	private int value;
	// Maximum value element can have
	private int max;
	// Font
	private BitmapFont font;
	// Timer for update call
	private float stateTime = 0;
	
	/**
	 * Creates a spinner (to increase or decrease values)
	 * @param arrow Arrow texture
	 * @param font Font
	 * @param text Spinner name
	 * @param value Initial value
	 * @param max Maximum value
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public UISpinner(TextureRegion arrow, BitmapFont font, String text, int value, int max, int x, int y){
		super(arrow,x,y);
		this.font = font;
		this.text = text;
		this.value = value;
		this.max = max;
	}
	
	@Override
	public void render(SpriteBatch batch){
		if(enabled){
			batch.setColor(Color.BLUE);
			font.setColor(1, 1, 1, 1);
		}
		else{
			font.setColor(1, 1, 1, 0.5f);
		}
		batch.draw(texture,x,y,4,8,8,16,1,1,180); // Draw left arrow
		font.draw(batch, text+": "+value, x+16, y+14);
		batch.draw(texture,x+80,y-1); // Draw right arrow
		batch.setColor(Color.WHITE);
	}
	
	/**
	 * Update the component, checking for press.
	 * Values loop around (max - 0 - max)
	 * @param delta Time elapsed
	 */
	@Override
	public void update(float delta){
		
		// Check if button is enabled and user touched/clicked the screen
		if(enabled && Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
					
			if(stateTime > 0.1f)
			{
				stateTime = 0;
				
				if(x0 > x && x0 < x+8 && y0 > y && y0 < y + 16){
					value--;
					if(value < 0){
						value = max;
					}
				}
				else if(x0 > x+80 && x0 < x+88  && y0 > y && y0 < y + 16 && value < max){
					value++;
					if(value > max){
						value = 0;
					}
				}
			}
			
			stateTime += delta;
		}
	}
	
	/**
	 * Get value of the spinner
	 * @return Value of the spinner
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set the value of the spinner
	 * @param value New value of the spinner
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
