package com.youtoolife.labyrinth.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.states.GamePlayState;

public class LightRenderer {

	ShaderProgram shader;

	public static final float DEFAULT_LIGHT_Z = 0.1f;
	public static final float AMBIENT_INTENSITY = 0.15f;
	public static final float LIGHT_INTENSITY = 1.5f;

	public static final Vector3 LIGHT_POS = new Vector3(0f, 0f, DEFAULT_LIGHT_Z);

	// Light RGB and intensity (alpha)
	public static final Vector3 LIGHT_COLOR = new Vector3(0.8f, 0.7f, 0);

	// Ambient RGB and intensity (alpha)
	public static final Vector3 AMBIENT_COLOR = new Vector3(0.8f, 0.7f, 0);

	// Attenuation coefficients for light falloff
	public static final Vector3 FALLOFF = new Vector3(.4f, 3f, 20f);

	public LightRenderer() {
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(
				Gdx.files.local("bin/shader/testShader/test.vsh"),
				Gdx.files.local("bin/shader/testShader/test.fsh"));
		if (!shader.isCompiled())
			throw new GdxRuntimeException("Could not compile shader: "
					+ shader.getLog());
		if (shader.getLog().length() != 0)
			System.out.println(shader.getLog());

		shader.begin();

		shader.setUniformi("u_normals", 1); // GL_TEXTURE1

		shader.setUniformf("AmbientColor", AMBIENT_COLOR.x, AMBIENT_COLOR.y,
				AMBIENT_COLOR.z, AMBIENT_INTENSITY);
		shader.setUniformf("Falloff", FALLOFF);
		shader.setUniformf("Resolution", MainGame.w, MainGame.h);

		shader.end();
	}

	public void render(SpriteBatch batch, GamePlayState game) {

		batch.setShader(shader);

		Light l = GamePlayState.player1.getLight(
				game.XOffset * 50 * Chunk.SIZE, game.YOffset * 50 * Chunk.SIZE);
		Light l2 = GamePlayState.player2.getLight(game.XOffset * 50
				* Chunk.SIZE, game.YOffset * 50 * Chunk.SIZE);

		LIGHT_POS.x = l.x / MainGame.w;
		LIGHT_POS.y = l.y / MainGame.h;
		Vector3 LIGHT_POS2 = new Vector3(l2.x / MainGame.w, l2.y / MainGame.h,
				LIGHT_POS.z);
		float[] poses = { LIGHT_POS.x, LIGHT_POS.y, LIGHT_POS.z, LIGHT_POS2.x,
				LIGHT_POS2.y, LIGHT_POS2.z };

		float[] colors = { l.color.r, l.color.g, l.color.b, LIGHT_INTENSITY,
				l2.color.r, l2.color.g, l2.color.b, LIGHT_INTENSITY };

		shader.setUniform3fv("LightPos", poses, 0, 6);
		shader.setUniform4fv("LightColor", colors, 0, 8);
		shader.setUniformf("Resolution", Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		shader.setUniformi("lights", 2);

		GamePlayState.chunks[game.yChunk][game.xChunk].renderAll(batch,
				game.XOffset, game.YOffset);

	}

}
