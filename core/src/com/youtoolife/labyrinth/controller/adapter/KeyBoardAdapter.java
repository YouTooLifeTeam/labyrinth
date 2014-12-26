package com.youtoolife.labyrinth.controller.adapter;

import java.util.Vector;

import com.badlogic.gdx.InputProcessor;

public class KeyBoardAdapter implements InputProcessor{

	Vector<InputProcessor> procs = new Vector<InputProcessor>();
	
	public void addProc(InputProcessor proc){
		procs.addElement(proc);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		for(InputProcessor p: procs)
			p.keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for(InputProcessor p: procs)
			p.keyUp(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		for(InputProcessor p: procs)
			p.keyTyped(character);
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
