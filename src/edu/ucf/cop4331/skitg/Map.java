package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class holds information on the map where the tanks and weapons interact
 * @author Jeremy Mayeres
 *
 */
public class Map {
	
	// Array index is the x coordinate, value is the y value of the ground level
	private int[] peaks;
	
	/**
	 * Create the map
	 */
	public Map(){
		peaks = new int[Skitg.WIDTH];
		
		generateTerrain();
	}
	
	/**
	 * Update the map
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		
	}
	
	/**
	 * Renders the map
	 * @param batch Batch handler
	 */
	public void render(SpriteBatch batch){
		
	}
	
	/**
	 * Randomly generates the terrain.
	 * Values should not be too low (shouldn't have points on the ground)
	 * nor should they be too high (where it reaches the top of the screen).
	 * Additionally, values should be smoothed so the terrain isn't too "rough"
	 */
	private void generateTerrain(){
		for(int i = 0; i < peaks.length; i++){
			peaks[i] = 40; // TODO: Generate random terrain
		}
	}
}
