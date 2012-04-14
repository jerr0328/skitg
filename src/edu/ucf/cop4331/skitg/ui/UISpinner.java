package edu.ucf.cop4331.skitg.ui;

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
	
	public UISpinner(TextureRegion tex, String text, int value, int max, int x, int y){
		this.arrow = tex;
		this.text = text;
		this.value = value;
		this.max = max;
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void update(float delta){
		
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
