package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class holds information on the map where the tanks and weapons interact
 * @author Jeremy Mayeres
 */
public class Map {

	
	// Array index is the x coordinate, value is the y value of the ground level
	private int[] peaks;
	// Draws lines and shapes to a texture
	private Pixmap pixmap;
	// The texture of the entire map
	private Texture texture;
	// The region for the texture to be displayed in
	private TextureRegion region;
	// These variables store the values for the map's function (Asin(Bx+C) + D)
	private float A, B, C, D;
	// The first minimum point on the map
	private int minA = 0;
	// The last minimum point on the map
	private int minB = 0;
	// Indicates if the map has changed (such as with a crater)
	private boolean hasChanged = false;
	// Indicates if the map needs to display an explosion
	private boolean hasExplosion = false;
	// The radius of the explosion
	private int	explosionRadius = 0;
	// The x Position of the explosion
	private int explosionPosX = 0;
	// The y Position of the explosion
	private int explosionPosY = 0;
	
	/**
	 * Create the map
	 */
	public Map(){
		peaks = new int[Skitg.WIDTH];
		
		generateTerrain();
	}
	
	/**
	 * Update the map
	 * Add newly created craters
	 * Display explosion animations
	 * @param delta Time elapsed
	 */
	public void update(float delta){
		if(hasChanged == true) {
			pixmap = new Pixmap(Skitg.WIDTH, Skitg.HEIGHT, Pixmap.Format.RGB888);
			
			//New Texture must be > Pixmap dimensions, and in powers of 2
			texture = new Texture(1024, 1024, Pixmap.Format.RGB888);
			texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
			
			//Since it is larger than the pixmap, it needs to wrap around the edges
			texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
			
			//Blue Sky
			pixmap.setColor(0.0f, 1.0f, 1.0f, 1.0f);
			pixmap.fillRectangle(0, 0, Skitg.WIDTH, Skitg.HEIGHT);
			
			//Green Land
			pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
			//Loops through the array, drawing line by line 
			for(int i=0; i<peaks.length; i++)
				pixmap.drawLine(i, Skitg.HEIGHT, i, Skitg.HEIGHT - peaks[i]);
			
			//If an explosion occurs, draws the explosion and loops again to draw map after explosion
			if(hasExplosion){
				pixmap.setColor(Color.RED);
				pixmap.fillCircle(explosionPosX, explosionPosY, explosionRadius);
				pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
				hasExplosion = false;
			}
			else
				hasChanged = false;
			
			//Draws the pixmap to the texture
			texture.draw(pixmap, 0, 0);
			region = new TextureRegion(texture, 0, 0, 800, 480);
			
			//Disposes of the pixmap
		    pixmap.dispose();	
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
	 * @param x
	 * @return the y coordinate for the original function at x
	 */
	public int getHeight(int x){
		return (int)(A*MathUtils.sinDeg(B*x + C) + D);
	}
	
	/**
	 * @param x
	 * @return the y coordinate at given x
	 */
	public int getPeaksY(int x){
		if(!(x < 0 || x > Skitg.WIDTH))
			return peaks[x];
		else
			return 0;
	}
	
	/**
	 * @param x - the x position
	 * @return - the slope at that x position (as an angle)
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
	
	/**
	 * Find the x-value of a valley
	 * @param first True to get the first valley, False to get the second valley 
	 * @return X coordinate of the valley
	 */
	public int getMinimum(boolean first){
		if (first)
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
		
		//Loops through the peaks array, populating it with the values (Asin(Bx+C)+D)
		for(int i = 0; i < peaks.length; i++)
			peaks[i] = getHeight(i);
		
		//New Pixmap with the width and height of the screen.
		pixmap = new Pixmap(Skitg.WIDTH, Skitg.HEIGHT, Pixmap.Format.RGB888);
		
		//New Texture must be > Pixmap dimensions, and in powers of 2
		texture = new Texture(1024, 1024, Pixmap.Format.RGB888);
		texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
		
		//Since it is larger than the pixmap, it needs to wrap around the edges
		texture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
		
		//Blue Sky
		pixmap.setColor(0.0f, 1.0f, 1.0f, 1.0f);
		pixmap.fillRectangle(0, 0, Skitg.WIDTH, Skitg.HEIGHT);
		
		//Green Land
		pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
		//Loops through the array, drawing line by line 
		for(int i=0; i<peaks.length; i++){ 
			pixmap.drawLine(i, Skitg.HEIGHT, i, Skitg.HEIGHT - peaks[i]);
			
			//Finds the minimum points so the tanks can start there
			if(i > 16 && i < Skitg.WIDTH - 16 && peaks[i] == (int)((-1*A) + D)){
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
	 * @param radius - the radius of the circle of destruction
	 * @param x - the x coordinate of the center of the circle of destruction
	 * @param y - the y coordinate of the center of the circle of destruction
	 */
	public void destroyTerrain(int radius, int x, int y){		
		for(int i = x-radius; i<x+radius && i<Skitg.WIDTH; i++){
			if(i <0)
				i = 0;
			int math = (int)(-1*Math.abs(Math.sqrt(Math.pow(radius,2)-Math.pow(i-x, 2))) + y);
			if(peaks[i] > math)
				peaks[i] = math;
		}	
		
		hasExplosion = true;
		explosionRadius = radius;
		explosionPosX = x;
		explosionPosY = Skitg.HEIGHT-y;
		
		hasChanged = true;
	}
	
	/**
	 * Has the terrain been changed recently?
	 * @return True if the terrain has been changed, otherwise false.
	 */
	public boolean isChanged(){
		return hasChanged;
	}
}
