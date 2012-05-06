package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.backends.jogl.JoglApplication;

/**
 * @author Jeremy Mayeres
 * Starts the game for the desktop version
 */
public class DesktopStarter {

	/**
	 * Main method ran on the desktop version
	 * @param args
	 */
	public static void main(String[] args) {
		new JoglApplication(new Skitg(), "Swift Kick in the Grass", 800, 480, false);

	}

}
