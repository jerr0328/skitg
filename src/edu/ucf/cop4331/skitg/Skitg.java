/**
 * 
 */
package edu.ucf.cop4331.skitg;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

/**
 * @author Jeremy Mayeres
 *
 */
public class Skitg implements ApplicationListener {

	/**
	 * The create method gets run on the first load
	 */
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	/**
	 * The dispose method gets run when the game is quitting
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/**
	 * Pause is called when the game gets suspended (i.e. phone call, etc.).
	 * This also gets called before dispose() on quitting the game.
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * This is essentially where the core code goes.
	 * This gets called as often as possible
	 */
	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	/**
	 * Resize is called any time the window size is changed. 
	 * This can get called several times on loading.
	 */
	@Override
	public void resize(int arg0, int arg1) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	/**
	 * This is what gets called when resuming the game after a pause().
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}
