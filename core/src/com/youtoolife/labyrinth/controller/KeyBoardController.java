package com.youtoolife.labyrinth.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class KeyBoardController extends Controller implements InputProcessor {

	int[] key_map = {Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN};
	
	@Override
	public void update() {
		//Input input = Gdx.input;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode==key_map[0])
			queue.add(Action.Left);
		if(keycode==key_map[1])
			queue.add(Action.Right);
		if(keycode==key_map[2])
			queue.add(Action.Up);
		if(keycode==key_map[3])
			queue.add(Action.Down);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	
	
}
