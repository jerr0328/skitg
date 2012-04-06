/**
 * 
 */
package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Game;

import edu.ucf.cop4331.skitg.ui.GameScreen;

/**
 * @author Jeremy Mayeres
 *
 */
public class Skitg extends Game {
	
	public static final int WIDTH  = 800;
    public static final int HEIGHT = 480;

	/**
	 * The create method gets run on the first load
	 */
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	
}
