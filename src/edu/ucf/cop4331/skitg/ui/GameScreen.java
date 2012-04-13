package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

import edu.ucf.cop4331.skitg.Engine;


public class GameScreen extends SkitgScreen {
	
	Engine engine;

	public GameScreen(Game game) {
		super(game);
	}
	
	public void show(){
		engine = new Engine();
	}
	
	public void render(float delta){
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		engine.update(delta);
		engine.render(delta);
	}
	
	public void hide(){
		
	}

}
