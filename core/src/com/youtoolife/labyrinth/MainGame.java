package com.youtoolife.labyrinth;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.states.MainMenuState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainGame extends StateBasedGame implements ApplicationListener {

	public OrthographicCamera camera;
	private SpriteBatch batch;

	public static final int GAMEPLAYSTATE = 1;
	public static final int MAINMENUSTATE = 2;
	public static float w = 800;
	public static float h = 600;

	BitmapFont font;

	public void create() {
		Gdx.graphics.setVSync(true);
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();

		font = new BitmapFont();

		this.addState(new MainMenuState(MAINMENUSTATE, this));
		this.addState(new GamePlayState(GAMEPLAYSTATE, this));
		this.enterState(MAINMENUSTATE);
		this.init();
	}

	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
	}

	public void render() {
		super.update();
		local_update();
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		this.render(batch);
		font.setScale(2f);
		font.draw(batch,
				"FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond()),
				-w / 2 + 5, h / 2 - 5);
		font.setScale(1);
		batch.end();
	}

	private void local_update() {
		if (Gdx.input.isKeyPressed(Input.Keys.F5))
			Gdx.graphics.setDisplayMode(
					Gdx.graphics.getDesktopDisplayMode().width,
					Gdx.graphics.getDesktopDisplayMode().height,
					!Gdx.graphics.isFullscreen());
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
