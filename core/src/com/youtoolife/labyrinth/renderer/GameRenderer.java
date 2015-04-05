package com.youtoolife.labyrinth.renderer;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.states.GamePlayState;

public class GameRenderer {

	int shadow_size = 400;
	float upScale = 1f;

	SpriteBatch batch;
	OrthographicCamera cam;

	TextureRegion shadowMap1D; // 1 dimensional shadow map
	TextureRegion occluders; // occluder map

	FrameBuffer shadowMapFBO;
	FrameBuffer occludersFBO;
	boolean softShadows = true;

	public static final Vector3 AMBIENT_COLOR = new Vector3(0.8f, 0.7f, 0);
	public static final float AMBIENT_INTENSITY = 0.15f;

	ShaderProgram ShadowMap, ShadowRender, LightRender;

	public GameRenderer() {

		batch = MainGame.batch;
		cam = MainGame.camera;
		cam.setToOrtho(false);

		initShadows();
		initLights();

	}

	public void render(GamePlayState game) {

		Vector<Light> lights = GamePlayState.chunks[game.yChunk][game.xChunk].lights;

		ShaderPreparer.prepare(lights, this);

		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (int j = -2; j <= 2; j++)
			for (int i = -2; i <= 2; i++)
				if (i + game.yChunk >= 0
						&& +game.yChunk + i < GamePlayState.SIZE
						&& game.xChunk + j >= 0
						&& +game.xChunk + j < GamePlayState.SIZE)
					GamePlayState.chunks[game.yChunk + i][game.xChunk + j]
							.renderAll(batch, GamePlayState.XOffset + j, GamePlayState.YOffset + i);
		batch.end();
		
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		ShaderPreparer.renderShadows(lights, game, this);
		
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.begin();
		batch.setColor(0.3f, 0.3f, 0.3f, 1f);
		batch.setShader(null);
		for (int j = -2; j <= 2; j++)
			for (int i = -2; i <= 2; i++)
				if (i + game.yChunk >= 0
						&& +game.yChunk + i < GamePlayState.SIZE
						&& game.xChunk + j >= 0
						&& +game.xChunk + j < GamePlayState.SIZE)
					GamePlayState.chunks[i + game.yChunk][j + game.xChunk]
							.renderWalls(batch, j + GamePlayState.XOffset, i
									+ GamePlayState.YOffset);

		GamePlayState.player1.draw(batch, GamePlayState.XOffset * 50 * Chunk.SIZE,
				GamePlayState.YOffset * 50 * Chunk.SIZE);
		GamePlayState.player2.draw(batch, GamePlayState.XOffset * 50 * Chunk.SIZE,
				GamePlayState.YOffset * 50 * Chunk.SIZE);
		batch.setColor(1, 1, 1, 1f);
		game.gui.draw(batch);
		batch.end();
	}

	private void initShadows() {
		ShadowMap = new ShaderProgram(    
				Gdx.files.local("bin/shader/pass.vert"),
				Gdx.files.local("bin/shader/shadowMap.frag"));
		ShadowRender = new ShaderProgram(
				Gdx.files.local("bin/shader/pass.vert"),
				Gdx.files.local("bin/shader/shadowRender.frag"));

		occludersFBO = new FrameBuffer(Format.RGBA8888, shadow_size,
				shadow_size, false);
		occluders = new TextureRegion(occludersFBO.getColorBufferTexture());
		occluders.flip(false, true);

		shadowMapFBO = new FrameBuffer(Format.RGBA8888, shadow_size, 1, false);
		Texture shadowMapTex = shadowMapFBO.getColorBufferTexture();

		shadowMapTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shadowMapTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		shadowMap1D = new TextureRegion(shadowMapTex);
		shadowMap1D.flip(false, true);
	}

	private void initLights() {
		LightRender = new ShaderProgram(
				Gdx.files.local("bin/shader/lightShade.vsh"),
				Gdx.files.local("bin/shader/lightShade.fsh"));

		LightRender.begin();

		LightRender.setUniformi("u_normals", 1); // GL_TEXTURE1

		LightRender.setUniformf("AmbientColor", AMBIENT_COLOR.x,
				AMBIENT_COLOR.y, AMBIENT_COLOR.z, AMBIENT_INTENSITY);
		LightRender.setUniformf("Falloff", new Vector3(.4f, 3f, 20f));
		LightRender.setUniformf("Resolution", MainGame.w, MainGame.h);

		LightRender.end();
	}
}
