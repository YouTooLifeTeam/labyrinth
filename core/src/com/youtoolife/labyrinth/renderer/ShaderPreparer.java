package com.youtoolife.labyrinth.renderer;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.states.GamePlayState;

public class ShaderPreparer {

	public static void prepare(Vector<Light> lights, GameRenderer renderer) {
		ShaderProgram shader = renderer.LightRender;
		float[] positions = new float[6];
		float[] colors = new float[8];
		
		/*String[] ufs = shader.getUniforms();
		for(String b:ufs)
			System.out.println(b);
		*/
		for (int i = 0; i < lights.size(); i++) {
			Light l = lights.get(i);
			positions[i * 3] = l.position.x / MainGame.w;
			positions[i * 3 + 1] = l.position.y / MainGame.h;
			positions[i * 3 + 2] = l.position.z;

			colors[i * 4] = l.color.r;
			colors[i * 4 + 1] = l.color.g;
			colors[i * 4 + 2] = l.color.b;
			colors[i * 4 + 3] = Light.LIGHT_INTENSITY;
		}

		renderer.batch.setShader(shader);
		shader.begin();
		shader.setUniformi("lights", 2);
		shader.setUniformf("Resolution", Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		shader.setUniform3fv("LightPos", positions, 0, positions.length);
		shader.setUniform4fv("LightColor", colors, 0, colors.length);
		shader.end();
	}

	public static void renderShadows(Vector<Light> lights, GamePlayState game,
			GameRenderer renderer) {
		for (Light l : lights)
			renderShadow(l, game, renderer);

	}

	private static void renderShadow(Light l, GamePlayState game,
			GameRenderer renderer) {
		float mx = l.position.x;
		float my = l.position.y;

		renderer.occludersFBO.begin();

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.cam.setToOrtho(false, renderer.occludersFBO.getWidth(),
				renderer.occludersFBO.getHeight());

		renderer.cam.translate(mx - renderer.shadow_size / 2f, my
				- renderer.shadow_size / 2f);
		renderer.cam.update();

		renderer.batch.setProjectionMatrix(renderer.cam.combined);
		renderer.batch.setShader(null); // use default shader
		renderer.batch.begin();

		GamePlayState.chunks[game.yChunk][game.xChunk].renderWalls(
				renderer.batch, GamePlayState.XOffset, GamePlayState.YOffset);

		GamePlayState.chunks[game.yChunk][game.xChunk].drawMobs(renderer.batch,
				GamePlayState.XOffset, GamePlayState.YOffset);

		if (l.exclude != GamePlayState.player1)
			GamePlayState.player1.draw(renderer.batch, GamePlayState.XOffset * 50 * Chunk.SIZE,
					GamePlayState.YOffset * 50 * Chunk.SIZE);
		if (l.exclude != GamePlayState.player2)
			GamePlayState.player2.draw(renderer.batch, GamePlayState.XOffset * 50 * Chunk.SIZE,
					GamePlayState.YOffset * 50 * Chunk.SIZE);

		renderer.batch.end();

		renderer.occludersFBO.end();

		renderer.shadowMapFBO.begin();

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.batch.setShader(renderer.ShadowMap);
		renderer.batch.begin();
		renderer.ShadowMap.setUniformf("resolution", renderer.shadow_size, renderer.shadow_size);
		renderer.ShadowMap.setUniformf("upScale", renderer.upScale);

		renderer.cam.setToOrtho(false, renderer.shadowMapFBO.getWidth(), renderer.shadowMapFBO.getHeight());
		renderer.batch.setProjectionMatrix(renderer.cam.combined);

		renderer.batch.draw(renderer.occluders.getTexture(), 0, 0, renderer.shadow_size,
				renderer.shadowMapFBO.getHeight());

		renderer.batch.end();

		renderer.shadowMapFBO.end();

		renderer.cam.setToOrtho(false, MainGame.w, MainGame.h);
		renderer.cam.update();
		renderer.batch.setProjectionMatrix(renderer.cam.combined);

		renderer.batch.setShader(renderer.ShadowRender);
		renderer.batch.begin();

		renderer.ShadowRender.setUniformf("resolution", renderer.shadow_size, renderer.shadow_size);
		renderer.ShadowRender.setUniformf("softShadows", renderer.softShadows ? 1f : 0f);
		renderer.batch.setColor(l.color);

		float finalSize = renderer.shadow_size * renderer.upScale;

		renderer.batch.draw(renderer.shadowMap1D.getTexture(), mx - finalSize / 2f, my
				- finalSize / 2f, finalSize, finalSize);

		renderer.batch.end();

		renderer.batch.setColor(1, 1, 1, 1);
	}

}
