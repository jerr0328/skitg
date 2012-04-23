package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Abstract class for all UI components (buttons, etc.)
 * @author Jeremy Mayeres
 *
 */
public abstract class UIComponent {
	
	/**
	 * Component enabled status (True if buttons can be pressed, false to disable)
	 */
	protected boolean enabled;
	/**
	 * Texture (for button)
	 */
	protected TextureRegion texture;
	/**
	 * X/Y coordinates
	 */
	protected int x,y;
	
	/**
	 * Initialize component with a texture and X/Y coordinates
	 * @param texture Texture for component
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	public UIComponent(TextureRegion texture, int x, int y){
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Update component
	 * @param delta Time elapsed
	 */
	public abstract void update(float delta);
	
	/**
	 * Render the component
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		if(!enabled){
			batch.setColor(1,1,1,0.5f);
		}
		batch.draw(texture,x,y);
		batch.setColor(Color.WHITE);
	}

	/**
	 * Check if component is enabled
	 * @return True if enabled, false if disabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Enable or disable the component
	 * @param enabled True to enable, false to disable
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
