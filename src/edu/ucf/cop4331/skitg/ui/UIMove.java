package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;

/**
 * Similar to the UISpinner, but handles moves
 * @author jeremy
 *
 */
public class UIMove {
	
	// Texture for the arrows
	private TextureRegion arrow;
	// Value of the element
	private int value;
	// Coordinates of UI element on the screen
	private int x,y;
	// True if enabled, false if disabled
	private boolean enabled = true;
	// Font
	private BitmapFont font;
	
	// Used for pressed  button
	private boolean pressedLeft = false;
	private boolean pressedRight = false;
	private float stateTime;

	public UIMove(TextureRegion arrow, BitmapFont font, int value, int x, int y){
		this.arrow = arrow;
		this.font = font;
		this.value = value;
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
		font.draw(batch, "Moves: "+value, x+16, y+14);
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
					
			if(stateTime > 0.2f)
			{
			
				stateTime = 0;
				
				if(x0 > x && x0 < x+8 && y0 > y && y0 < y + 16 )
					pressedLeft = true;
				else if(x0 > x + 80 && x0 < x+88 && y0 > y && y0 < y + 16)
					pressedRight = true;
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
	
	public int isPressed(){
		if(pressedLeft)
			return 1;
		if(pressedRight)
			return 2;
		else
			return 0;
	}
	
	public void unPress(boolean pressed){
		this.pressedLeft = pressed;
		this.pressedRight = pressed;
	}

}
