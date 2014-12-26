package com.youtoolife.labyrinth.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyBoardController extends Controller {

	int[] key_map = { Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP,
			Input.Keys.DOWN };

	float dl = 0, dr = 0, du = 0, dd = 0;

	@Override
	public void update() {
		Input input = Gdx.input;
		dl -= Gdx.graphics.getDeltaTime();
		dr -= Gdx.graphics.getDeltaTime();
		du -= Gdx.graphics.getDeltaTime();
		dd -= Gdx.graphics.getDeltaTime();
		if (input.isKeyPressed(key_map[0]))
			if (dl <= 0) {
				queue.add(Action.Left);
				dl = 0.3f;
			}
		if (input.isKeyPressed(key_map[1]))
			if (dr <= 0) {
				queue.add(Action.Right);
				dr = 0.3f;
			}
		if (input.isKeyPressed(key_map[2]))
			if (du <= 0) {
				queue.add(Action.Up);
				du = 0.3f;
			}
		if (input.isKeyPressed(key_map[3]))
			if (dd <= 0) {
				queue.add(Action.Down);
				dd = 0.3f;
			}
	}

}
