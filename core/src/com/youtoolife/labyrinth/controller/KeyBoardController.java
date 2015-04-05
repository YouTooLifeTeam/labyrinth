package com.youtoolife.labyrinth.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyBoardController extends Controller {

	
	
	int[] key_map = null;
	public static final int[] ARROWS = { Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN, Input.Keys.V, Input.Keys.SHIFT_RIGHT};
	public static final int[] WASD = { Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S, Input.Keys.ALT_LEFT, Input.Keys.SHIFT_LEFT};

	float dl = 0, dr = 0, du = 0, dd = 0, dsa = 0, dna = 0;

	final static float delta = 0.04f;
	
	public KeyBoardController(int[] key_map){
		this.key_map = key_map;	}
	
	@Override
	public void update() {
		Input input = Gdx.input;
		dl -= Gdx.graphics.getDeltaTime();
		dr -= Gdx.graphics.getDeltaTime();
		du -= Gdx.graphics.getDeltaTime();
		dd -= Gdx.graphics.getDeltaTime();
		dna -= Gdx.graphics.getDeltaTime();
		dsa -= Gdx.graphics.getDeltaTime();
		if (input.isKeyPressed(key_map[0]))
			if (dl <= 0) {
				queue.add(Action.Left);
				dl = delta;
			}
		if (input.isKeyPressed(key_map[1]))
			if (dr <= 0) {
				queue.add(Action.Right);
				dr = delta;
			}
		if (input.isKeyPressed(key_map[2]))
			if (du <= 0) {
				queue.add(Action.Up);
				du = delta;
			}
		if (input.isKeyPressed(key_map[3]))
			if (dd <= 0) {
				queue.add(Action.Down);
				dd = delta;
			}
		if (input.isKeyPressed(key_map[4]))
			if (dsa <= 0) {
				queue.add(Action.SpecAction);
				dsa = delta;
			}
		if (input.isKeyPressed(key_map[5]))
			if (dna <= 0) {
				queue.add(Action.NormAction);
				dna = delta;
			}
	}

}
