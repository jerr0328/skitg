package edu.ucf.cop4331.skitg.ui;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import edu.ucf.cop4331.skitg.Skitg;
import edu.ucf.cop4331.skitg.weapons.Weapon;

/**
 * Weapon selector
 * @author Jeremy Mayeres
 *
 */
public class UIWeaponSelector extends UIComponent{
	// Font
	private BitmapFont font;
	// Weapons list
	private List<Weapon> weapons;
	// Index of active weapon in weapons list
	private int activeWeapon;
	// Name of active weapon (as a cache to prevent crashing)
	private String activeWeaponName;
	// Time since button press
	private float stateTime = 0;
	
	/**
	 * Create weapon selector component
	 * @param arrow Arrow texture
	 * @param font Font
	 * @param x X Coordinate
	 * @param y Y Coordinate
	 * @param weapons Weapons list
	 */
	public UIWeaponSelector(TextureRegion arrow, BitmapFont font, int x, int y, List<Weapon> weapons){
		super(arrow,x,y);
		this.font = font;
		this.weapons = weapons;
		setActiveWeapon(0);
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
		font.draw(batch, "Weapon: "+activeWeaponName, x+16, y+14);
		batch.draw(texture,x+140,y-1); // Draw right arrow

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

	/**
	 * Get index of active weapon
	 * @return Index of active weapon
	 */
	public int getActiveWeapon() {
		return activeWeapon;
	}

	/**
	 * Set the active weapon
	 * @param activeWeapon Index of weapon
	 */
	public void setActiveWeapon(int activeWeapon) {
		this.activeWeapon = activeWeapon;
		this.activeWeaponName = weapons.get(activeWeapon).toString();
	}

	/**
	 * Set the list of weapons
	 * @param weapons Weapons list
	 */
	public void setWeapons(List<Weapon> weapons) {
		this.weapons = weapons;
	}
	
}
