package edu.ucf.cop4331.skitg.ui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.weapons.Weapon;

public class UIWeaponSelector {
	private int x,y;
	private boolean enabled = true;
	private BitmapFont font;
	private List<Weapon> weapons;
	private int activeWeapon = 0;
	private String activeWeaponName;
	private TextureRegion arrow;
	private float stateTime = 0;
	
	public UIWeaponSelector(TextureRegion arrow, BitmapFont font, int x, int y, List<Weapon> weapons){
		this.arrow = arrow;
		this.font = font;
		this.x = x;
		this.y = y;
		this.weapons = weapons;
		this.activeWeaponName = weapons.get(activeWeapon).toString();
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
		font.draw(batch, "Weapon: "+activeWeaponName, x+16, y+14);
		batch.draw(arrow,x+140,y-1); // Draw right arrow

		batch.setColor(Color.WHITE);
	}
	
	public void update(float delta){
		
		if(Gdx.input.isTouched())
		{						
			float x0 = Gdx.input.getX(0); // (float)Gdx.graphics.getWidth()) * Skitg.HEIGHT;
			float y0 = Skitg.HEIGHT - Gdx.input.getY(0); // (float)Gdx.graphics.getHeight()) * Skitg.WIDTH;
					
			if(stateTime > 0.1f)
			{
			
				stateTime = 0;
				
				if(x0 > x && x0 < x+8 && y0 > y && y0 < y + 16 && activeWeapon > 0)
					setActiveWeapon(activeWeapon - 1);
				else if(x0 > x + 140 && x0 < x + 148  && y0 > y && y0 < y + 16 && activeWeapon < weapons.size() - 1)
					setActiveWeapon(activeWeapon + 1);
			}
			
			stateTime += delta;
		}
	}

	public int getActiveWeapon() {
		return activeWeapon;
	}

	public void setActiveWeapon(int activeWeapon) {
		this.activeWeapon = activeWeapon;
		this.activeWeaponName = weapons.get(activeWeapon).toString();
	}

	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	
	public void setEnabled(boolean flag){
		this.enabled = flag;
	}
	
	
}
