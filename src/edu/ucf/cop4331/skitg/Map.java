package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class holds information on the map where the tanks and weapons interact
 * @author Jeremy Mayeres
 *
 */
public class Map {

	/**
	 * Map state: Terrain has been changed
	 */
	static final int HASCHANGED = 1;
	
	/**
	 * Map state: Terrain has been changed
	 */
	static final int HASNOTCHANGED = 0;
	
	// Array index is the x coordinate, value is the y value of the ground level
	private int[] peaks;
	Pixmap pixmap;
	Texture texture;
	TextureRegion region;
	float A, B, C, D;
	private int minA = 0;
	private int minB = 0;
	private int state = HASNOTCHANGED;
	
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
		if(state == HASCHANGED) {
			pixmap = new Pixmap(Skitg.WIDTH, Skitg.HEIGHT, Pixmap.Format.RGB888);
			
			//New Texture must be > Pixmap dimensions, and in powers of 2
			texture = new Texture(1024, 1024, Pixmap.Format.RGB888);
			texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
			
			//Since it is larger than the pixmap, it needs to wrap around the edges
			texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
			
			//Blue
			pixmap.setColor(0.0f, 1.0f, 1.0f, 1.0f);
			pixmap.fillRectangle(0, 0, Skitg.WIDTH, Skitg.HEIGHT);
			
			//Green
			pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			//Loops through the array, drawing line by line 
			for(int i=0; i<peaks.length; i++)
				pixmap.drawLine(i, Skitg.HEIGHT, i, Skitg.HEIGHT - peaks[i]);
	
			//Draws the pixmap to the texture
			texture.draw(pixmap, 0, 0);
			region = new TextureRegion(texture, 0, 0, 800, 480);
			
			//Disposes of the pixmap
		    pixmap.dispose();	
		    
		    state = HASNOTCHANGED;
		}
	}
	
	/**
	 * Renders the map
	 * @param batch Batch handler
	 */
	
	public void render(SpriteBatch batch){
		batch.draw(region, 0, 0);
	}
	
	/**
	 * 
	 * @param x
	 * @return the y coordinate for the sin function at x
	 */
	public int getHeight(int x){
		return (int)(A*MathUtils.sinDeg(B*x + C) + D);
	}
	
	/**
	 * @param x
	 * @return the y coordinate at given x
	 */
	public int getPeaksY(int x){
		return peaks[x];
	}
	
	/**
	 * 
	 * @param x - the x position that we are looking for an angle for
	 * @return - The angle 
	 */
	public float getAngle(int x){
		
		
		float angle = MathUtils.atan2(1, (peaks[x-1] - peaks[x+1]))*MathUtils.radiansToDegrees;
		//All angles are 0 <= angle <= 360
		while(angle > 360)
			angle -= 360;
		while(angle < 0)
			angle += 180;
		
		return angle;
	}
	
	public int getMinimum(int tank){
		if (tank==1)
			return minA+1;
		else 
			return minB-1;		
	}
	
	/**
	 * Randomly generates the terrain.
	 * Values should not be too low (shouldn't have points on the ground)
	 * nor should they be too high (where it reaches the top of the screen).
	 * Additionally, values should be smoothed so the terrain isn't too "rough"
	 */	
	private void generateTerrain(){
		
		//The total terrain will occupy 50% of the screen (vertically and after applying D)
		A = MathUtils.random(50, (float).25*Skitg.HEIGHT);
		
		//Generates a random float between .5 and .9 which I found to be good ranges
		B = MathUtils.random((float)0.9, (float)1.2);
		//B=(float) 0.9;
		
		//Generates a random float between 0 and the width of the screen to shift terrain horizontally (if it's > width, it is redundant) 
		C = MathUtils.random((float)Skitg.WIDTH);
		//C=0;
		
		//Generates a vertical shift of the graph of the terrain
		D = (float).4*Skitg.HEIGHT;
		
		//Loops through the peaks array, populating it with the values from the following equation:
		for(int i = 0; i < peaks.length; i++)
			peaks[i] = getHeight(i);
		
		//New Pixmap with the width and height of the screen.
		pixmap = new Pixmap(Skitg.WIDTH, Skitg.HEIGHT, Pixmap.Format.RGB888);
		
		//New Texture must be > Pixmap dimensions, and in powers of 2
		texture = new Texture(1024, 1024, Pixmap.Format.RGB888);
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
		
		//Since it is larger than the pixmap, it needs to wrap around the edges
		texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		
		//Blue
		pixmap.setColor(0.0f, 1.0f, 1.0f, 1.0f);
		pixmap.fillRectangle(0, 0, Skitg.WIDTH, Skitg.HEIGHT);
		
		//Green
		pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
		//Loops through the array, drawing line by line 
		for(int i=0; i<peaks.length; i++){ 
			pixmap.drawLine(i, Skitg.HEIGHT, i, Skitg.HEIGHT - peaks[i]);
			
			//Finds the minimum points so the tanks can start there
			if(i > 32 && i < Skitg.WIDTH - 32 && peaks[i] == (int)((-1*A) + D)){
				if(minA == 0)
					minA = i;
				else
					minB = i;
			}
		}

		//Draws the pixmap to the texture
		texture.draw(pixmap, 0, 0);
		region = new TextureRegion(texture, 0, 0, 800, 480);
		
		//Disposes of the pixmap
	    pixmap.dispose();		
		
	}
	
	/**
	 * When a collision with the terrain is made, it should be destroyed
	 */
	public void destroyTerrain(int radius, int x, int y){
		//We have a circle x^2 + y^2 = radius
		//We need to remove all pixels from x-radius to x+radius 
		System.out.println(radius + " " +x);
		for(int i = x-radius; i<x+radius; i++){
			peaks[i] = (int)Math.abs(Math.sqrt(Math.pow(radius,2)-Math.pow(i-x, 2)) + y) + peaks[i];
		}	
		state = HASCHANGED;
	}
}
