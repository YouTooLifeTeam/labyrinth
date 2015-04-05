package com.youtoolife.labyrinth.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.utils.Assets;
import com.youtoolife.labyrinth.utils.GameState;
import com.youtoolife.labyrinth.utils.StateBasedGame;

public class TestState extends GameState {

	ShaderProgram shader;

	Texture tex, normal;

	public static final float DEFAULT_LIGHT_Z = 0.075f;
	public static final float AMBIENT_INTENSITY = 0.2f;
	public static final float LIGHT_INTENSITY = 1f;

	public static final Vector3 LIGHT_POS = new Vector3(0f, 0f, DEFAULT_LIGHT_Z);

	// Light RGB and intensity (alpha)
	public static final Vector3 LIGHT_COLOR = new Vector3(1f, 0.8f, 0.6f);

	// Ambient RGB and intensity (alpha)
	public static final Vector3 AMBIENT_COLOR = new Vector3(0.6f, 0.6f, 1f);

	// Attenuation coefficients for light falloff
	public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);

	public TestState(int StateId, MainGame game) {
		super(StateId, game);

	}

	@Override
	public void draw(SpriteBatch batch) {
		float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), dw = w / 800, dh = h / 600, cX = Gdx.input
				.getX(), cY = Gdx.input.getY(), x = cX / dw, y = 600 - cY / dh;
		Vector2 LIGHT_POS = new Vector2(x, y);

		shader.setUniformf("LightPos", LIGHT_POS);

		// bind normal map to texture unit 1
		normal.bind(1);

		// bind diffuse color to texture unit 0
		// important that we specify 0 otherwise we'll still be bound to
		// glActiveTexture(GL_TEXTURE1)
		tex.bind(0);

		// draw the texture unit 0 with our shader effect applied
		batch.draw(tex, 0, 0);

	}

	@Override
	public void update(StateBasedGame game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(StateBasedGame game) {
		MainGame.camera.setToOrtho(false, 50, 50);
		MainGame.camera.update();
		tex = Assets.getTexture("floor");
		normal = Assets.getTexture("floor_normal");
		shader = new ShaderProgram(Shade.VERT, Shade.FRAG);
		System.out.println(shader.getLog());
		shader.begin();

		// our normal map
		shader.setUniformi("u_normals", 1); // GL_TEXTURE1

		// light/ambient colors
		// LibGDX doesn't have Vector4 class at the moment, so we pass them
		// individually...
		shader.setUniformf("LightColor", LIGHT_COLOR.x, LIGHT_COLOR.y,
				LIGHT_COLOR.z, LIGHT_INTENSITY);
		shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y,
				AMBIENT_COLOR.z, AMBIENT_INTENSITY);
		shader.setUniformf("Falloff", FALLOFF);

		// LibGDX likes us to end the shader program
		shader.end();

		MainGame.batch.setShader(shader);
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
