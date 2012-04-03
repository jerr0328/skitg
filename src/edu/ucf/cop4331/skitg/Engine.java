package edu.ucf.cop4331.skitg;

/**
 * Handles game logic and physics
 * @author Jeremy Mayeres
 *
 */
public class Engine {
	
	// Player 1
	private Tank tank1;
	// Player 2
	private Tank tank2;
	
	public Engine(){
		tank1 = new Tank(true);
		tank2 = new Tank(false);
	}
	
	public void update(float delta){
		tank1.update(delta);
		tank2.update(delta);
	}
	
}
