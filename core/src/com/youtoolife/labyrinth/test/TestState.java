package com.youtoolife.labyrinth.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class TestState extends GameState {

	ShaderProgram shader;
	
	Texture texture;
	
	public TestState(int StateId, MainGame game) {
		super(StateId, game);
	}

	@Override
	public void draw(SpriteBatch batch) {

		batch.setShader(shader);
		
		batch.draw(texture, -400, -300);
		
		
	}

	@Override
	public void update(StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(StateBasedGame game) {
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.local("bin/shader/test/testShade.vsh"),
				Gdx.files.local("bin/shader/test/testShade.fsh"));
		System.out.println(shader.getLog());
		texture = Assets.getTexture("menu/MainMenu");
	}

	@Override
	public void dispose() {
		shader.dispose();
	}

	@Override
	public void enter(StateBasedGame game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
