package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;

/**
 * Similar to the UISpinner, but handles moves
 * @author Jeremy Mayeres
 *
 */
public class UIMove extends UIComponent{
	
	/**
	 * Button state: Not pressed
	 */
	public static final int NONE = 0;
	/**
	 * Button state: Pressed left
	 */
	public static final int LEFT = 1;
	/**
	 * Button state: Pressed right
	 */
	public static final int RIGHT = 2;
	
	// Value of the element
	private int value;
	// Font
	private BitmapFont font;
	// State of button
	private int pressed;
	// Time since button press
	private float stateTime;

	/**
	 * Create the move button
	 * @param arrow Arrow texture
	 * @param font Text font
	 * @param value Initial value
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public UIMove(TextureRegion arrow, BitmapFont font, int value, int x, int y){
		super(arrow, x, y);
		this.font = font;
		this.value = value;
		
		pressed = NONE;
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
		font.draw(batch, "Moves: "+value, x+16, y+14);
		batch.draw(texture,x+80,y-1); // Draw right arrow
		batch.setColor(Color.WHITE);
	}
	
	/**
	 * Update the component, checking for press
	 * @param delta Time elapsed
	 */
	@Override
	public void update(float delta){
		
		// Check if button is enabled and user touched/clicked the screen
		if(enabled && Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
					
			if(stateTime > 0.2f)
			{
				stateTime = 0;
				
				if(x0 > x && x0 < x+8 && y0 > y && y0 < y + 16 )
					pressed = LEFT;
				else if(x0 > x + 80 && x0 < x+88 && y0 > y && y0 < y + 16)
					pressed = RIGHT;
			}
			
			stateTime += delta;
		}
	}

	/**
	 * Sets the remaining moves
	 * @param value Remaining moves
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Get the state of the button
	 * @return Button pressed state
	 */
	public int getPressed(){
		return pressed;
	}
	
	/**
	 * Reset state to NONE
	 */
	public void unPress(){
		this.pressed = NONE;
	}

}
