package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import edu.ucf.cop4331.skitg.Engine;

/**
 * Main game screen
 * @author Jeremy Mayeres
 *
 */
public class GameScreen extends SkitgScreen {
	
	// Game engine
	Engine engine;

	/**
	 * Create the main game screen
	 * @param game Game instance
	 */
	public GameScreen(Game game) {
		super(game);
	}
	
	/**
	 * Show the screen
	 */
	public void show(){
		engine = Engine.getInstance();
		engine.initialize();
	}
	
	/**
	 * Render the screen
	 */
	public void render(float delta){
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		engine.update(delta);
		engine.render(delta);
	}
	
	/**
	 * Hide the screen
	 */
	public void hide(){
		
	}

}
