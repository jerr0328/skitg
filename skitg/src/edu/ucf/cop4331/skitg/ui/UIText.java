package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Generic text component
 * @author Jeremy Mayeres
 *
 */
public class UIText {
	// X/Y Coordinates
	int x,y;
	// Font
	BitmapFont font;
	// Text to display
	String text;
	// Font color
	Color color;
	
	/**
	 * Create a text component
	 * @param font Font
	 * @param text Initial value
	 * @param color Font color
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 */
	public UIText(BitmapFont font, String text, Color color, int x, int y){
		this.font = font;
		this.text = text;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set the text to display
	 * @param text Text to display
	 */
	public void setText(String text){
		this.text = text;
	}
	
	/**
	 * Render the text
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		font.setColor(color);
		font.draw(batch, text, x, y);
	}

}
