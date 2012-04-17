package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
