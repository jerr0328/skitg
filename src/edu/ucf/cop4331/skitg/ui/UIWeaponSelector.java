package edu.ucf.cop4331.skitg.ui;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.weapons.Weapon;

public class UIWeaponSelector {
	private int x,y;
	private boolean enabled = true;
	private BitmapFont font;
	private List<Weapon> weapons;
	private int activeWeapon = 0;
	private TextureRegion arrow;
	
	public UIWeaponSelector(TextureRegion arrow, BitmapFont font, int x, int y, List<Weapon> weapons){
		this.arrow = arrow;
		this.font = font;
		this.x = x;
		this.y = y;
		this.weapons = weapons;
	}
	
	public void render(SpriteBatch batch){
		if(enabled ){
			batch.setColor(Color.BLUE);
			font.setColor(1, 1, 1, 1);
		}
		else{
			font.setColor(1, 1, 1, 0.5f);
		}
		batch.draw(arrow,x,y,4,8,8,16,1,1,180); // Draw left arrow
		font.draw(batch, "Weapon: "+weapons.get(activeWeapon).toString(), x+16, y+14);
		batch.draw(arrow,x+80,y-1); // Draw right arrow

		batch.setColor(Color.WHITE);
	}
	
	public void update(float delta){
		
	}

	public int getActiveWeapon() {
		return activeWeapon;
	}

	public void setActiveWeapon(int activeWeapon) {
		this.activeWeapon = activeWeapon;
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	
	
}
