package edu.ucf.cop4331.skitg.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;

/**
 * UI component for the fire button
 * @author Jeremy Mayeres
 *
 */
public class UIFireButton extends UIComponent{
	
	// Button pressed state
	private boolean pressed = false;
	// Time since press
	private float stateTime = 0;
	
	/**
	 * Initialize fire button with texture and X/Y coordinates
	 * @param tex Fire button texture
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public UIFireButton(TextureRegion tex, int x, int y){
		super(tex, x, y);
	}
	
	/**
	 * Update the button, checking for press
	 * @param delta Time elapsed
	 */
	@Override
	public void update(float delta){
		
		// Check if button is enabled and user touched/clicked the screen
		if(enabled && Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
			
			// Check so we don't have rapid-fire pressing
			if(stateTime > 0.2f)
			{
				stateTime = 0;
				
				// Check bounds
				if(x0 > x && x0 < x+64 && y0 > y && y0 < y + 16)
					pressed = true;
			}
			
			stateTime += delta;
		}	
	}

	/**
	 * Get state of button
	 * @return True if button is pressed, false otherwise
	 */
	public boolean isPressed() {
		return pressed;
	}
	
	/**
	 * Set the status of the button press
	 * @param pressed True if pressed, false otherwise
	 */
	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}
	

}
