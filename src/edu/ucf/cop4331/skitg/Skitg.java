/**
 * 
 */
package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Game;

import edu.ucf.cop4331.skitg.ui.GameScreen;

/**
 * Handles the game (called by DesktopStarter or AndroidGame)
 * @author Jeremy Mayeres
 * 
 */
public class Skitg extends Game {
	
	/**
	 * Width of screen
	 */
	public static final int WIDTH  = 800;
	/**
	 * Height of screen
	 */
    public static final int HEIGHT = 480;

	/**
	 * The create method gets run on the first load
	 */
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	
}
