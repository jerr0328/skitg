package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Abstract class for all game screens
 * @author Jeremy Mayeres
 *
 */
public abstract class SkitgScreen implements Screen {
	
	// Game instance
	Game game;
	
	/**
	 * Construct the screen
	 * @param game Game instance
	 */
	public SkitgScreen(Game game){
		this.game = game;
	}

	/**
	 * Dispose (close) the screen
	 */
	@Override
	public void dispose() {
	}

	/**
	 * Hide the screen
	 */
	@Override
	public void hide() {
	}

	/**
	 * Pause the screen (i.e. phone call)
	 */
	@Override
	public void pause() {
	}

	/**
	 * Render the screen
	 */
	@Override
	public void render(float arg0) {
	}

	/**
	 * Resize the screen
	 */
	@Override
	public void resize(int arg0, int arg1) {
	}

	/**
	 * Resume after a pause
	 */
	@Override
	public void resume() {
	}

	/**
	 * Show the screen
	 */
	@Override
	public void show() {
	}

}
