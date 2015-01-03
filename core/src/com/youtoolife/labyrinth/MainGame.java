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
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.ChunkGenerator;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class MainGame extends StateBasedGame implements ApplicationListener {

	public OrthographicCamera camera;
	private SpriteBatch batch;

	//ShaderProgram shader;
	
	public static final int GAMEPLAYSTATE = 1;
	public static final int MAINMENUSTATE = 2;
	public static float w = 800;
	public static float h = 600;

	BitmapFont font;

	public void create() {
		Assets.load();
		ChunkGenerator.init();
		Gdx.graphics.setVSync(true);
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		//camera.zoom = 4f;
		//camera.update();
		font = new BitmapFont();

		this.addState(new MainMenuState(MAINMENUSTATE, this));
		this.addState(new GamePlayState(GAMEPLAYSTATE, this));
		this.enterState(MAINMENUSTATE);
		this.init();
		
		//ShaderProgram.pedantic = false;
		//shader = new ShaderProgram(Gdx.files.internal("shader/red.vsh"),Gdx.files.internal("shader/emboss.fsh"));
		//System.out.println(shader.isCompiled()?"Shader compiled":shader.getLog());
		//batch.setShader(shader);
	}

	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
	}

	public void render() {
		super.update();
		camera.update();
		local_update();
		
		/*shader.begin();
		//shader.setUniformf("u_distort", MathUtils.random(4),MathUtils.random(4),0);
		shader.setUniformf("u_resolution", Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		shader.end();*/
		
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		this.render(batch);
		font.draw(batch, "FPS "+Gdx.graphics.getFramesPerSecond(), -400, 300);
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
