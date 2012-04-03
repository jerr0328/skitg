package edu.ucf.cop4331.skitg.ui;

import com.badlogic.gdx.Game;

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
		engine.update(delta);
	}
	
	public void hide(){
		
	}

}
