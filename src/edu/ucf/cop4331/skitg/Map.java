package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * This class holds information on the map where the tanks and weapons interact
 * @author Jeremy Mayeres
 *
 */
public class Map {
	
	// Array index is the x coordinate, value is the y value of the ground level
	private int[] peaks;
	Pixmap pixmap;
	Texture texture;
	TextureRegion region;
	float A, B, C, D;
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
		batch.draw(region, 0, 0);
	}
	
	/**
	 * 
	 * @param x
	 * @return the y coordinate at given x
	 */
	public int getHeight(int x){
		return (int)(A*MathUtils.sinDeg(B*x + C) + D);
	}
	
	/**
	 * 
	 * @param x - the x position that we are looking for an angle for
	 * @return - The angle 
	 */
	public float getAngle(int x){
		
		
		float angle = MathUtils.atan2(1, (peaks[x-1] - peaks[x+1]))*MathUtils.radiansToDegrees;
		//float angle = MathUtils.atan2(A*B*MathUtils.cosDeg(B*x + C), 1)*MathUtils.radiansToDegrees;

		System.out.println(angle);

		//All angles are 0 <= angle <= 360
		while(angle > 360)
			angle -= 360;
		while(angle < 0)
			angle += 180;
		
		System.out.println(angle);
		/*
		if(angle == 90) { //If slope is ---
			System.out.println("----");
			return (angle); }
		else if(angle > 90){ //If slope is negative
			System.out.println("'-.");
			return (angle+45); }
		else {//If slope is positive /
			System.out.println("///////");
			return(angle+135); }
		*/
		return angle;
	}
	
	/**
	 * Randomly generates the terrain.
	 * Values should not be too low (shouldn't have points on the ground)
	 * nor should they be too high (where it reaches the top of the screen).
	 * Additionally, values should be smoothed so the terrain isn't too "rough"
	 */	
	private void generateTerrain(){
		
		//The total terrain will occupy 50% of the screen (vertically and after applying D)
		A = (float).25*Skitg.HEIGHT;
		
		//Generates a random float between .5 and .9 which I found to be good ranges
		B = MathUtils.random((float)0.5, (float)0.9);
		//B=1;
		
		//Generates a random float between 0 and the width of the screen to shift terrain horizontally (if it's > width, it is redundant) 
		C = MathUtils.random((float)Skitg.WIDTH);
		//C=0;
		
		//Generates a vertical shift of the graph of the terrain
		D = (float).4*Skitg.HEIGHT;
		
		//Loops through the peaks array, populating it with the values from the following equation:
		for(int i = 0; i < peaks.length; i++){
			peaks[i] = getHeight(i);
		}
		
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
		for(int i=0; i<peaks.length; i++) {
			if(i == 200 || i == 700) {
				pixmap.setColor(Color.BLACK);
				pixmap.drawLine(i, Skitg.HEIGHT, i, 0);
				pixmap.drawLine(0, Skitg.HEIGHT-peaks[i], Skitg.WIDTH, Skitg.HEIGHT-peaks[i]);
			}
			else
			{
				pixmap.setColor(0.0f, 1.0f, 0.0f, 1.0f);
				pixmap.drawLine(i, Skitg.HEIGHT, i, Skitg.HEIGHT - peaks[i]);
			}
		}

		//Draws the pixmap to the texture
		texture.draw(pixmap, 0, 0);
		region = new TextureRegion(texture, 0, 0, 800, 480);
		
		//Disposes of the pixmap
	    pixmap.dispose();		
		
	}
}
