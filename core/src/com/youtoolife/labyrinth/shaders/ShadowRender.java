package com.youtoolife.labyrinth.shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.youtoolife.labyrinth.MainGame;
import com.youtoolife.labyrinth.chunk.Chunk;
import com.youtoolife.labyrinth.states.GamePlayState;
import com.youtoolife.labyrinth.units.Player;

public class ShadowRender {

	public static ShaderProgram createShader(String vert, String frag) {
		ShaderProgram prog = new ShaderProgram(vert, frag);
		if (!prog.isCompiled())
			throw new GdxRuntimeException("could not compile shader: "
					+ prog.getLog());
		if (prog.getLog().length() != 0)
			System.out.println("GpuShadows " + prog.getLog());
		return prog;
	}

	private int lightSize = 400;

	private float upScale = 1f;

	SpriteBatch batch;
	OrthographicCamera cam;

	BitmapFont font;

	TextureRegion shadowMap1D; // 1 dimensional shadow map
	TextureRegion occluders; // occluder map

	FrameBuffer shadowMapFBO;
	FrameBuffer occludersFBO;

	ShaderProgram shadowMapShader, shadowRenderShader;

	boolean additive = true;
	boolean softShadows = true;

	LightRenderer lightRender;

	public ShadowRender() {
		batch = new SpriteBatch();
		ShaderProgram.pedantic = false;

		lightRender = new LightRenderer();

		shadowMapShader = createShader(Gdx.files.local("bin/shader/pass.vert")
				.readString(), Gdx.files.local("bin/shader/shadowMap.frag")
				.readString());
		shadowRenderShader = createShader(
				Gdx.files.local("bin/shader/pass.vert").readString(), Gdx.files
						.local("bin/shader/shadowRender.frag").readString());

		occludersFBO = new FrameBuffer(Format.RGBA8888, lightSize, lightSize,
				false);
		occluders = new TextureRegion(occludersFBO.getColorBufferTexture());
		occluders.flip(false, true);

		shadowMapFBO = new FrameBuffer(Format.RGBA8888, lightSize, 1, false);
		Texture shadowMapTex = shadowMapFBO.getColorBufferTexture();

		shadowMapTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shadowMapTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		shadowMap1D = new TextureRegion(shadowMapTex);
		shadowMap1D.flip(false, true);

		font = new BitmapFont();

		cam = new OrthographicCamera(MainGame.w, MainGame.h);
		cam.setToOrtho(false);
	}

	public void render(GamePlayState game) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (additive)
			batch.setBlendFunction(GL20.GL_SRC_ALPHA,
					GL20.GL_ONE_MINUS_SRC_ALPHA);

		batch.begin();
		batch.setColor(1, 1, 1, 1);
		lightRender.render(batch, game);
		batch.end();

		if (additive)
			batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);

		renderLight(GamePlayState.player1.getLight(game.XOffset * 50
				* Chunk.SIZE, game.YOffset * 50 * Chunk.SIZE), game,
				GamePlayState.player1);

		renderLight(GamePlayState.player2.getLight(game.XOffset * 50
				* Chunk.SIZE, game.YOffset * 50 * Chunk.SIZE), game,
				GamePlayState.player2);

		if (additive)
			batch.setBlendFunction(GL20.GL_SRC_ALPHA,
					GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.begin();
		batch.setColor(0.99f, 0.99f, 0.99f, 1f);
		batch.setShader(null);
		for (int j = -2; j <= 2; j++)
			for (int i = -2; i <= 2; i++)
				if (i + game.yChunk >= 0
						&& +game.yChunk + i < GamePlayState.SIZE
						&& game.xChunk + j >= 0
						&& +game.xChunk + j < GamePlayState.SIZE)
					GamePlayState.chunks[i + game.yChunk][j + game.xChunk]
							.renderWalls(batch, j + game.XOffset, i
									+ game.YOffset);

		GamePlayState.player1.draw(batch, game.XOffset * 50 * Chunk.SIZE,
				game.YOffset * 50 * Chunk.SIZE);
		GamePlayState.player2.draw(batch, game.XOffset * 50 * Chunk.SIZE,
				game.YOffset * 50 * Chunk.SIZE);
		batch.setColor(1, 1, 1, 1f);
		game.gui.draw(batch);
		batch.end();

	}

	void renderLight(Light o, GamePlayState game, Player exclude) {
		float mx = o.x;
		float my = o.y;

		occludersFBO.begin();

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.setToOrtho(false, occludersFBO.getWidth(), occludersFBO.getHeight());

		cam.translate(mx - lightSize / 2f, my - lightSize / 2f);
		cam.update();

		batch.setProjectionMatrix(cam.combined);
		batch.setShader(null); // use default shader
		batch.begin();

		for (int i = -2; i <= 2; i++)
			for (int j = -2; j <= 2; j++)
				if (i + game.yChunk >= 0
						&& +game.yChunk + i < GamePlayState.SIZE
						&& game.xChunk + j >= 0
						&& +game.xChunk + j < GamePlayState.SIZE)
					GamePlayState.chunks[i + game.yChunk][j + game.xChunk]
							.renderWalls(batch, j + game.XOffset, i
									+ game.YOffset);

		GamePlayState.chunks[game.yChunk][game.xChunk].drawMobs(batch,
				game.XOffset, game.YOffset);

		if (exclude != GamePlayState.player1)
			GamePlayState.player1.draw(batch, game.XOffset * 50 * Chunk.SIZE,
					game.YOffset * 50 * Chunk.SIZE);
		if (exclude != GamePlayState.player2)
			GamePlayState.player2.draw(batch, game.XOffset * 50 * Chunk.SIZE,
					game.YOffset * 50 * Chunk.SIZE);

		batch.end();

		occludersFBO.end();

		shadowMapFBO.begin();

		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setShader(shadowMapShader);
		batch.begin();
		shadowMapShader.setUniformf("resolution", lightSize, lightSize);
		shadowMapShader.setUniformf("upScale", upScale);

		cam.setToOrtho(false, shadowMapFBO.getWidth(), shadowMapFBO.getHeight());
		batch.setProjectionMatrix(cam.combined);

		batch.draw(occluders.getTexture(), 0, 0, lightSize,
				shadowMapFBO.getHeight());

		batch.end();

		shadowMapFBO.end();

		cam.setToOrtho(false, MainGame.w, MainGame.h);
		cam.update();
		batch.setProjectionMatrix(cam.combined);

		batch.setShader(shadowRenderShader);
		batch.begin();

		shadowRenderShader.setUniformf("resolution", lightSize, lightSize);
		shadowRenderShader.setUniformf("softShadows", softShadows ? 1f : 0f);
		shadowRenderShader.setUniformf("u_noise", MathUtils.random(.3f) + 1);
		batch.setColor(o.color);

		float finalSize = lightSize * upScale;

		batch.draw(shadowMap1D.getTexture(), mx - finalSize / 2f, my
				- finalSize / 2f, finalSize, finalSize);

		batch.end();

		batch.setColor(1, 1, 1, 1);
	}

}