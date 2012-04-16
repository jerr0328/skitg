package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
		batch.draw(arrow,x,y); // Draw left arrow
		// TODO: Write out the text
		batch.draw(arrow,x+180,y,0,0,8,16,1,1,180); // Draw right arrow
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
