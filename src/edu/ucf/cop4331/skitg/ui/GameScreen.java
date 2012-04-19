package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import edu.ucf.cop4331.skitg.Engine;


public class GameScreen extends SkitgScreen {
	
	Engine engine;

	public GameScreen(Game game) {
		super(game);
	}
	
	public void show(){
		engine = Engine.getInstance();
		engine.initialize();
	}
	
	public void render(float delta){
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		engine.update(delta);
		engine.render(delta);
	}
	
	public void hide(){
		
	}

}
