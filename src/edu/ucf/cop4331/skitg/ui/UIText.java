package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIText {
	int x,y;
	BitmapFont font;
	String text;
	Color color;
	
	public UIText(BitmapFont font, String text, Color color, int x, int y){
		this.font = font;
		this.text = text;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void update(float delta){
		
	}
	
	public void render(SpriteBatch batch){
		font.setColor(color);
		font.draw(batch, text, x, y);
	}

}
